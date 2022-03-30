package tn.esprit.infini2.services;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
