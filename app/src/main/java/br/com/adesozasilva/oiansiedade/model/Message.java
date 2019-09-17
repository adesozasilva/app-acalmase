package br.com.adesozasilva.oiansiedade.model;

public class Message {

    private Integer id;
    private String content;
    private MessageType type;
    private User sender;
    private User recipient;

    public static enum MessageType { CHAT, JOIN, LEAVE }

    public Message(int id, String content) {
        this.content = content;
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }
    public User getSender() {
        return sender;
    }
    public String getContent() {
        return content;
    }

    public Integer getId(){
        return id;
    }
}
