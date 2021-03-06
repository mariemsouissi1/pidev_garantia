package tn.pidev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.pidev.entities.Bank;



public interface BankRepository extends JpaRepository<Bank, Long> {
  
    @Query(value = "SELECT * FROM bank",nativeQuery=true) 
    public List<Bank> getAllBanks();
    
    @Query(value = "SELECT * FROM bank WHERE name_bank=?1",nativeQuery=true)
    public Bank getBankByName(String name);  
    
    @Query(value = "SELECT * FROM bank WHERE id_bank=?1",nativeQuery=true) 
    public Bank getBankById(long id);
}
