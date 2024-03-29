package com.treloiii;

public class Message {
    String text;
    String name;
    String textAdmin;
    String image;

    Message(String text,String name,String textAdmin,String image){
        this.text=text;
        this.name=name;
        this.textAdmin=textAdmin;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getTextAdmin() {
        return textAdmin;
    }

    @Override
    public String toString() {
        return "{text:"+this.text+",name:"+this.name+",image:"+this.image+",admin:"+this.textAdmin+"}";
    }
}
