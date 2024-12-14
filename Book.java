import java.io.*;

public class Book implements Serializable {
    
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author;
    }

    public static void main(String[] args) {
        Book book = new Book("1984", "George Orwell");
        System.out.println(book);
    }
}
