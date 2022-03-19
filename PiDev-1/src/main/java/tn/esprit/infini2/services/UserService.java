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

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private EmployeeRepository  employeeRepository;
    private CustomerRepository customerRepository;

    private PasswordEncoder passwordEncoder;

    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public Boolean verifAuthentifaction(AuthenticationRequest authenticationRequest) {
        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(authenticationRequest.getUsername());
        Optional<Customer> customer = customerRepository.findCustomerByEmail(authenticationRequest.getUsername());
        if (employee.isPresent()){
            this.passwordEncoder = this.PasswordEncoder();
            Boolean verif = employee.get().getPassword() != null && !employee.get().getPassword().isEmpty()
                    && this.passwordEncoder.matches(employee.get().getPassword(), authenticationRequest.getPassword());
            return verif;
        }
        else{
if(customer.isPresent()){
    this.passwordEncoder = this.PasswordEncoder();
    Boolean verif = customer.get().getPassword() != null && !customer.get().getPassword().isEmpty()
            && this.passwordEncoder.matches(customer.get().getPassword(), authenticationRequest.getPassword());
    return verif;
} else return  false;
        }
//        if (authenticationRequest.getUsername().equals("foo") && authenticationRequest.getPassword().equals("foo"))
//            return true;
//        if (authenticationRequest.getUsername().equals("foo2") && authenticationRequest.getPassword().equals("foo2"))
//            return true;
//        return false;
    }

    public String getAuthentifaction(String usename) {
        if (usename.equals("foo") )
            return Employee();
        if (usename.equals("foo2") )
            return Customer();
        return "";
    }


    public String Employee(){
        return Employee.class.getSimpleName();
    }
    public String Customer(){
        return Customer.class.getSimpleName();
    }
}
