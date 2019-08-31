package br.com.adesozasilva.acalmase.model;

public class Message {

    private String text;
    private Integer id;

    public Message(int id, String text) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Integer getId(){
        return id;
    }
}
