package br.com.mirella.myproject;

public class Book {
    private long id;
    private String title;
    private String author;

    public Book(long id, String title, String author){
        this.title = title;
        this.author = author;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
}
