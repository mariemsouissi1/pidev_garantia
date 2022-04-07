package tn.pidev.exceptions;

public class InvalidAmountException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAmountException(String str){
        super(str);
    }

}
