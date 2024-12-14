import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



    public class Library {
    private static ArrayList<Book> books = new ArrayList<>();
    private static final String FILE_NAME = "library.dat";

    public static void main(String[] args) {
        loadBooks();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBook(scanner);
                    break;
                case 4:
                    saveBooks();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void searchBook(Scanner scanner) {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private static void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
            System.out.println("Books saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    private static void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (ArrayList<Book>) ois.readObject();
            System.out.println("Books loaded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No saved books found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}
