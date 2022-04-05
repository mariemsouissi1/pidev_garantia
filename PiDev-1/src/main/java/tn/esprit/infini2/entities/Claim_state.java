package tn.esprit.infini2.entities;

public enum Claim_state {
Treated,Ongoing,Untreated;
	 public String toString(){
	    switch(this){
	    case Treated :
	        return "Treated";
	    case Ongoing :
	        return "Ongoing";
	    case Untreated :
	        return "Untreated";
	    }
	    return null;
	 }
}
