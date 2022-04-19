package tn.esprit.entities;

public enum StatutCredit {
	Ongoing, Paid, Denied, Null;
	 public String toStringSc(){
		    switch(this){
		    case Ongoing :
		        return "Ongoing";
		    case Paid :
		        return "Paid";
		    case Denied :
		    	return "Denied";
		    case Null:
		    	return "Null";
		    }
		    return null;
		 }
	

}
