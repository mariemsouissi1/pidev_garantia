package tn.esprit.infini2.entities;

public enum conversation_type {
Chat,Groupe;
	 public String toString2(){
		    switch(this){
		    case Chat :
		        return "Chat";
		    case Groupe :
		        return "Groupe";
		    
		    }
		    return null;
		 }
}
