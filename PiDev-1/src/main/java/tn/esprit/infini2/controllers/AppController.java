package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.repositories.CustomerRepository;
import tn.esprit.infini2.repositories.EmployeeRepository;

@RestController
public class AppController {

    @Autowired
    private EmployeeRepository employeRepo;

    @Autowired
    private CustomerRepository customerRepo;

    private PasswordEncoder passwordEncoder;

    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @GetMapping("")
//    public String viewHomePage() {
//        return "index";
//    }
//
//
//    @GetMapping("/registerEmployee")
//    public String showEmployeeRegistrationForm(Model model) {
//        model.addAttribute("employee", new Employee());
//
//        return "signup_form_employee";
//    }
//
//    @GetMapping("/registerCustomer")
//    public String showCustomerRegistrationForm(Model model) {
//        model.addAttribute("customer", new Customer());
//
//        return "signup_form_customer";
//    }

    @PostMapping("/process_register_employee")
    public Employee processRegisterEmployee(Employee employee) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);

        return  employeRepo.save(employee);

    }

    @PostMapping("/process_register_customer")
    public Customer processRegisterCustomer(@RequestBody  Customer customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        return  customerRepo.save(customer);
    }

}
