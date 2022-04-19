package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.infini2.entities.Agent;



public interface AgentRepository extends JpaRepository<Agent, Long>{

    @Query(value = "SELECT * FROM bank b join agent a on a.id_agent=b.agent_bank_id_agent WHERE b.name_bank=?1",nativeQuery=true)
    public Agent getAgentByNameBank(String nameBank);
    
    @Query(value = "SELECT * FROM agent ",nativeQuery=true) 
    public List<Agent> getAllAgents();
    
    @Query(value = "SELECT * FROM agent WHERE last_name=?1",nativeQuery=true)
    public Agent getAgentByName(String lastname);  
    
    @Query(value = "SELECT * FROM agent WHERE id_agent=?1",nativeQuery=true) 
    public Agent getAgentById(long id);
}
