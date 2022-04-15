package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.CustomerAccount;
import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.entities.ScoreType;
import tn.esprit.infini2.repositories.CustomerAccountRepository;
import tn.esprit.infini2.repositories.CustomerRepository;
import tn.esprit.infini2.repositories.EmployeeRepository;
import tn.esprit.infini2.services.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    private EmployeeRepository employeRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private EmailService senderService;

    private PasswordEncoder passwordEncoder;

    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Autowired
    ICustomerService customerService;

    @Autowired
    IEmployeeService employeeService;


    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    ICustomerAccountService customerAccountService;

    @PostMapping("/process_register_employee")
    public Employee processRegisterEmployee(@RequestBody Employee employee) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        return  employeRepo.save(employee);

    }

    //@EventListener(ApplicationReadyEvent.class)
    @PostMapping("/process_register_customer")
    public Customer processRegisterCustomer(@RequestBody  Customer customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        CustomerAccount customerAccount= new CustomerAccount();
        customerAccount.setScore(customerAccountService.calculScore(customer));
        if ((customerAccount.getScore()>0) && (customerAccount.getScore() <= 2.5))
        customerAccount.setScoreType(ScoreType.Mediocre);
        else if ((customerAccount.getScore()>2.5) && (customerAccount.getScore() <= 5))
            customerAccount.setScoreType(ScoreType.Average);
        else if ((customerAccount.getScore()>5) && (customerAccount.getScore() <= 7.5))
            customerAccount.setScoreType(ScoreType.Good);
        else if ((customerAccount.getScore()>7.5) && (customerAccount.getScore() <= 10))
            customerAccount.setScoreType(ScoreType.Excellent);


        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
        customerAccount.setDateCreationCompte(date);
        customerAccountRepository.save(customerAccount);
        //senderService.sendSimpleMessage(customer.getEmail(),"subject","body");
        return  customerRepo.save(customer);
    }

    @PreAuthorize("hasAuthority(@userService.Employee())")
    @GetMapping("/customersList")
    public List<Customer> customersList() {
        List<Customer> list = customerRepo.findAll();
        return list;
    }

    @PreAuthorize("hasAuthority(@userService.Customer())")
    @PutMapping("/updateCustomer")
    @ResponseBody
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @PreAuthorize("hasAuthority(@userService.Employee())")
    @PutMapping("/updateEmployee")
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

}
