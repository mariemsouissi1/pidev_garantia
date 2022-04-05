package tn.pidev.entities;

public enum StatutLoanSimulation {
	inprogress, denied, confirmed;
	
	public String toStringStatutSimul(){
	    switch(this){
	    case inprogress :
	        return "inprogress";
	    case denied :
	        return "denied";
	    case confirmed :
	    	return "confirmed";
	    }
	    return null;
	 }

}
