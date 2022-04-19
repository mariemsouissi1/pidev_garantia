package tn.esprit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Agent;
import tn.esprit.services.IAgentService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	IAgentService iAgentService;
	
/////////////////////////ADD/////////////////////
	
	// http://localhost:8087/pidevmariem/agent/addAgent/id
	@PostMapping("/addAgent/{idBank}")
	@ResponseBody
	public Agent addAgent (@RequestBody Agent agent,@PathVariable("idBank") long idBank) {
		iAgentService.addAgent(agent,idBank);
		return agent;
	}
/////////////////////////UPDATE WITH CONTROL/////////////////////

	// http://localhost:8087/pidevmariem/agent/updateAgent/idAgent
	@PutMapping("/updateAgent/{idAgent}")
	@ResponseBody 
	public Agent updateAgent(@RequestBody Agent agent,@PathVariable("idAgent") long idAgent){
		return iAgentService.updateAgent(idAgent, agent);
	}	
	
/////////////////////////GET/////////////////////

	// http://localhost:8087/pidevmariem/agent/getAllAgents
	@GetMapping("/getAllAgents")
    @ResponseBody
	public List<Agent> getAllAgents() {
		
		return iAgentService.getAllAgents();
	}
	
	// http://localhost:8087/pidevmariem/agent/getAgentById/id
	@GetMapping("/getAgentById/{idAgent}")
	@ResponseBody
	public Agent getAgentById(@PathVariable("idAgent") long idAgent){
		return iAgentService.getAgentById(idAgent);
	}
	
	// http://localhost:8087/pidevmariem/agent/getAgentByLastName/LastNameAgent
		@GetMapping("/getAgentByLastName/{LastNameAgent}")
		@ResponseBody
		public Agent getAgentByLastName(@PathVariable("LastNameAgent") String LastNameAgent){
			return iAgentService.getAgentByName(LastNameAgent);
		}
	
/////////////////////////DELETE/////////////////////

	// http://localhost:8087/pidevmariem/agent/deleteAgent/id
	@DeleteMapping("/deleteAgent/{idAgent}")
	@ResponseBody 
	public void deleteAgentById(@PathVariable("idAgent") long idAgent ){
		iAgentService.deleteAgentById(idAgent);
	}

}
