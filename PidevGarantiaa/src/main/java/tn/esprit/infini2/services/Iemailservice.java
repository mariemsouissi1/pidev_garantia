package tn.esprit.infini2.services;

public interface Iemailservice {
	public String sendEmail(String to,String subject,String text);
	public String sendEmailwithAttachment();
}
