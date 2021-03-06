package tn.pidev.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.pidev.entities.Agent;
import tn.pidev.entities.Bank;
import tn.pidev.repositories.AgentRepository;
import tn.pidev.repositories.BankRepository;

@Service
@Slf4j
public class AgentServiceImpl implements IAgentService {
	
	private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);
	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	BankRepository bankRepository;
	
	
/////////////////////////ADD/////////////////////
	
	@Override
	public void addAgent(Agent agent,long idBank) {
		 log.info("Inside Add Agent");
		 Bank bank =bankRepository.findById(idBank).get();
		 agent.setBankAgent(bank);
		 agentRepository.save(agent);
		 bank.setAgentBank(agent);
		 bankRepository.save(bank);
	}
	
/////////////////////////UPDATE/////////////////////

	@Override
	public Agent updateAgent(long idAgent, Agent ag) {
		if (agentRepository.findById(idAgent).isPresent()) {
		log.info("Inside Update Agent");
		Agent agent= agentRepository.findById(idAgent).get();
		ag.setFirstName(agent.getFirstName());
		ag.setLastName(agent.getLastName());
		ag.setPhoneAgent(agent.getPhoneAgent());
		ag.setEmail(agent.getEmail());
		ag.setBankAgent(agent.getBankAgent());
		return agentRepository.save(ag);}
		else
		{
			log.error("Agent not found");
			return null;
		}
	}

	
/////////////////////////GET/////////////////////
	
	@Override
	public List<Agent> getAllAgents() {
		log.info("Inside Get All Agents");
		return agentRepository.getAllAgents();
		
	}

	@Override
	public Agent getAgentById(long id) {	
		log.info("Inside Get Agent By Id");
		return agentRepository.getAgentById(id);
	}
	
	@Override
	public Agent getAgentByName(String lastname) {
		log.info("Inside Get Agent By LastName");
		return  agentRepository.getAgentByName(lastname);
	}
	
////////////////////DELETE////////////////////////////
	
	@Override
	public void deleteAgentById(long idAgent) {
		log.info("Inside Delete Agent By Id");
		Agent agent = agentRepository.findById(idAgent).get();
		agentRepository.delete(agent);
		
	}

}
