package com.nicolo.exampleservlet.dto;

public class ClientDto {

    private String name;
    private String surname;
    private String email;
    private String tel;
    private String imageUrl;

    public ClientDto(String name, String surname, String email, String tel, String imageUrl){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tel = tel;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
