package tn.pidev.entities;

public enum VerificationCredit {
	Accepted, Rejected;
	public String toStringVerif(){
	    switch(this){
	    case Accepted :
	        return "Accepted";
	    case Rejected :
	        return "Rejected";
	    }
	    return null;
	 }
}
