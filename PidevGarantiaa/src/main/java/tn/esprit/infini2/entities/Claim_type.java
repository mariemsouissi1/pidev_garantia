package tn.esprit.infini2.entities;

public enum Claim_type {
Reclamation,Complaint;
	 public String toString(){
		    switch(this){
		    case Reclamation :
		        return "Reclamation";
		    case Complaint :
		        return "Complaint";
		    
		    }
		    return null;
		 }
}
