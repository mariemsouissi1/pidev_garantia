package tn.esprit.infini2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findCustomerByEmail(String email);

    @Query("FROM Customer c WHERE c.idCustomer = :id")
    List<Customer> findCustomerByID(@Param(value = "id") Long id);

    @Query("FROM Customer c WHERE c.email = :email")
    Customer findCustomerByemail(@Param(value = "email") String email);

}
