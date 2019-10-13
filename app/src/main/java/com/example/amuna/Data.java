package com.example.amuna;

public class Data {
    String member_id;
    String member_name;
    String member_rating;

    public String getMember_rating() {
        return member_rating;
    }

    public void setMember_rating(String member_rating) {
        this.member_rating = member_rating;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public Data(String member_id, String member_name, String member_rating){
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_rating = member_rating;
    }
}