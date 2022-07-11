package com.example.interfacebase;

public class Quote {
    private int id;
    private String subject;
    private String teacher;
    private String quote;
    private String date;
    private int user_id;

    public Quote(int id, String subject, String teacher, String quote, String date, int user_id) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.quote = quote;
        this.date = date;
        this.user_id = user_id;
    }

    public Quote() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getQuote() {
        return quote;
    }

    public String getDate() {
        return date;
    }

    public int getUser_id() {
        return user_id;
    }
}
