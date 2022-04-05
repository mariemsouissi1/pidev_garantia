package tn.esprit.infini2.entities;

public enum Claim_contrat_type {
	automobile,vie,Habitation,epargne,Retraite,Chefdefamille,Santé,avenirdemesenfants,materielinformatique;
	
	
	 public String toString(){
	        switch(this){
	        case automobile :
	            return "automobile";
	        case vie :
	            return "vie";
	        case Habitation :
	            return "Habitation";
	        case epargne :
	            return "epargne";
	        case Retraite :
	            return "Retraite";
	        case Chefdefamille :
	            return "Chefdefamille";
	        case Santé :
	            return "epargne";
	        case avenirdemesenfants :
	            return "avenirdemesenfants";
	        case materielinformatique :
	            return "materielinformatique";
	        }
	        return null;
	 }
}