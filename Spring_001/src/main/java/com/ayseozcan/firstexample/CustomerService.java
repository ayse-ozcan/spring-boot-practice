package com.ayseozcan.firstexample;

public class CustomerService {

    private MessageRepository messageRepository;
    private MailRepository mailRepository;
    public CustomerService(MessageRepository messageRepository,MailRepository mailRepository){
        this.messageRepository = messageRepository;
        this.mailRepository = mailRepository;
    }

    public CustomerService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public CustomerService(MailRepository mailRepository){
        this.mailRepository = mailRepository;
    }
    public CustomerService(){
        this.messageRepository = new MessageRepository();
        this.mailRepository = new MailRepository();
    }
    public void sendMessage(){
        messageRepository.sendMessage();
    }

    public void sendMail(){
        mailRepository.sendMail();
    }
}
