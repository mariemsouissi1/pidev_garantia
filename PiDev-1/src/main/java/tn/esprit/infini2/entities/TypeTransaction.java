package tn.esprit.infini2.entities;

public enum TypeTransaction {
	versement, online;
	
	public String toStringTypeTrans(){
	    switch(this){
	    case versement :
	        return "versement";
	    case online :
	        return "online";
	    }
	    return null;
	 }

}
