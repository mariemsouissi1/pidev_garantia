package tn.esprit.entities;

public enum TraitementCredit {
	inprogress, treated;
	public String toStringTraitement(){
	    switch(this){
	    case inprogress :
	        return "inprogress";
	    case treated :
	        return "treated";
	    }
	    return null;
	 }
}
