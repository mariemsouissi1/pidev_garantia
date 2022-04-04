package tn.esprit.infini2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.infini2.entities.AuthenticationRequest;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.repositories.CustomerRepository;
import tn.esprit.infini2.repositories.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Boolean verifAuthentifaction(AuthenticationRequest authenticationRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(authenticationRequest.getUsername());
        if (employee.isPresent()) {
            Boolean verif = employee.get().getPassword() != null && !employee.get().getPassword().isEmpty()
                    && passwordEncoder.matches(authenticationRequest.getPassword(), employee.get().getPassword());
            return verif;
        } else {
            Optional<Customer> customer = customerRepository.findCustomerByEmail(authenticationRequest.getUsername());
            if (customer.isPresent() && customer.get().getActive()) {
                Customer c = customer.get();
                Boolean verif = c.getPassword() != null && !c.getPassword().isEmpty()
                        && passwordEncoder.matches(authenticationRequest.getPassword(), c.getPassword());
                if (!verif) {
                    int i = c.getTries();
                    c.setTries(++i);
                    if (c.getTries() >= 3) {
                        c.setActive(false);
                    }
                    c.setLastFailedAuthentication(LocalDateTime.now());
                    c = customerRepository.save(c);
                }
                return verif;
            } else
                return false;
        }
    }

    public String getAuthentifaction(String username) {

        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(username);
        if (employee.isPresent()) {
            return Employee();
        } else {
            Optional<Customer> customer = customerRepository.findCustomerByEmail(username);
            if (customer.isPresent()) {
                return Customer();
            } else
                return null;
        }

    }

    public String Employee() {
        return Employee.class.getSimpleName();
    }

    public String Customer() {
        return Customer.class.getSimpleName();
    }


}
