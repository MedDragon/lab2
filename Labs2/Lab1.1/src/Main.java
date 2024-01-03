import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Singleton Pattern
class Bookshelf {
    private static Bookshelf instance;
    private Map<String, Book> books;

    private Bookshelf() {
        this.books = new HashMap<>();
    }

    public static synchronized Bookshelf getInstance() {
        if (instance == null) {
            instance = new Bookshelf();
        }
        return instance;
    }

    public void addBook(String ISBN, Book book) {
        books.put(ISBN, book);
    }

    public void removeBook(String ISBN) {
        books.remove(ISBN);
    }

    public void displayBooks() {
        System.out.println("Books on the bookshelf:");
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}

// Prototype Pattern
abstract class Book implements Cloneable {
    private String ISBN;
    private int pageCount;
    private String genre;
    private String author;
    private String title;

    public Book(String ISBN, int pageCount, String genre, String author, String title) {
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.genre = genre;
        this.author = author;
        this.title = title;
    }

    // Clone method
    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }

    // Getters and Setters

    public String getISBN() {
        return ISBN;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", pageCount=" + pageCount +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

// Concrete book classes
class FictionBook extends Book {
    public FictionBook(String ISBN, int pageCount, String author, String title) {
        super(ISBN, pageCount, "Fiction", author, title);
    }
}

class ComicsBook extends Book {
    public ComicsBook(String ISBN, int pageCount, String author, String title) {
        super(ISBN, pageCount, "Comics", author, title);
    }
}

// Book generator class
class BookGenerator {
    private static final Random random = new Random();

    public static Book generateBook(String genre, String author, String title) {
        String ISBN = generateRandomISBN();
        int pageCount = generateRandomPageCount();
        switch (genre.toLowerCase()) {
            case "fiction":
                return new FictionBook(ISBN, pageCount, author, title);
            case "comics":
                return new ComicsBook(ISBN, pageCount, author, title);
            default:
                throw new IllegalArgumentException("Unsupported genre");
        }
    }

    private static String generateRandomISBN() {
        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            isbn.append(random.nextInt(10));
        }
        return isbn.toString();
    }

    private static int generateRandomPageCount() {
        return random.nextInt(500) + 100; // Random page count between 100 and 599
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        // Singleton pattern, get the bookshelf instance
        Bookshelf bookshelf = Bookshelf.getInstance();

        // Display books on the bookshelf
        bookshelf.displayBooks();
        System.out.println("Adding books to the bookshelf...");
        // Generate and add books to the bookshelf
        Book book1 = BookGenerator.generateBook("Fiction", "John Doe", "The Mystery");
        Book book2 = BookGenerator.generateBook("Comics", "Jane Smith", "Superhero Adventures");

        bookshelf.addBook(book1.getISBN(), book1);
        bookshelf.addBook(book2.getISBN(), book2);

        // Display books on the bookshelf
        bookshelf.displayBooks();

        System.out.println("Removing a book from the bookshelf...");

        // Remove a book from the bookshelf
        bookshelf.removeBook(book1.getISBN());

        // Display books on the bookshelf after removal
        bookshelf.displayBooks();
    }
}