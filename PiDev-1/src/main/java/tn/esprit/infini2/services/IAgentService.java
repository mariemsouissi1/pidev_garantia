package tn.pidev.services;

import java.util.List;

import tn.pidev.entities.Agent;

public interface IAgentService {
	
	void addAgent(Agent agent,long idBank);
		
	Agent updateAgent(long idAgent, Agent ag);
	
	List<Agent> getAllAgents();
	
	Agent getAgentById(long id);

	Agent getAgentByName(String lastname);
	
	void deleteAgentById(long idAgent);
}
