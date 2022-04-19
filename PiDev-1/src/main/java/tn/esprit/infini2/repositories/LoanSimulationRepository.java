package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.infini2.entities.LoanSimulation;


@Repository
public interface LoanSimulationRepository extends JpaRepository<LoanSimulation, Long> {

    @Query(value = "SELECT * FROM loan_simulation l "
    				+ "join customer_account ca on l.customer_account_loan_id_customer_account=ca.id_customer_account"
    				+ " WHERE ca.cin=?1",nativeQuery=true)
    public List<LoanSimulation> getAllSimulationsByCin(long cin);
    
    @Query(value="SELECT * FROM loan_simulation l "
    			+ "join Bank b on l.bank_loan_id_bank=b.id_bank "
    			+ "WHERE b.name_bank=?1",nativeQuery=true)
    public List<LoanSimulation> getAllSimulationsByNameBank(String nameBank);
    
    @Query(value="SELECT * FROM loan_simulation WHERE salaire=?1",nativeQuery=true)
    public List<LoanSimulation> getAllSimulationsBySalary(double salaire);
    
    @Query(value="SELECT * FROM loan_simulation WHERE customer_account_loan_id_customer_account=?1",nativeQuery=true)
    public LoanSimulation getSimulationByidCustomer(long idCustomerAccount);
    
    @Query(value="SELECT * FROM loan_simulation WHERE customer_account_loan_id_customer_account=?1 AND status_loan_simulation='inprogress'",nativeQuery=true)
    public List<LoanSimulation> countAllSimulationsByidCustomer(long idCustomerAccount);
    
    @Query(value="SELECT COUNT(*) FROM loan_simulation WHERE status_loan_simulation='inprogress' ",nativeQuery=true)
    public int countAllSimulationsInProgress();
    
    @Query(value="SELECT * FROM loan_simulation WHERE status_loan_simulation='denied' ",nativeQuery=true)
    public List<LoanSimulation> getAllSimulationsDenied();
    
    @Query(value="SELECT * FROM loan_simulation WHERE status_loan_simulation='inprogress' ",nativeQuery=true)
    public List<LoanSimulation> getAllSimulationsInProgress();
    
    @Modifying
    @Transactional
    @Query(value="DELETE FROM loan_simulation WHERE id_loan=?1",nativeQuery=true)
    public void delete(long idLoan);
    
 
}
