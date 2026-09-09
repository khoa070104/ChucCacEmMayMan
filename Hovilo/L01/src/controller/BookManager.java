package controller;

import model.Author;
import model.Book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Manages books, authors, searching, and persistence. @author Ho Vi Lo @since 09/09/2026 */
public class BookManager {
    private final Map<String, Author> authors = new LinkedHashMap<>();
    private final Map<String, Book> books = new LinkedHashMap<>();
    private final Path authorFile;
    private final Path bookFile;

    /** Uses author.dat and book.dat in the current working directory. */
    public BookManager() {
        this(Paths.get("author.dat"), Paths.get("book.dat"));
    }

    /** @param authorFile author data path @param bookFile book data path */
    public BookManager(Path authorFile, Path bookFile) {
        this.authorFile = authorFile;
        this.bookFile = bookFile;
    }

    /**
     * Loads both data files. Malformed lines are skipped so the application can continue.
     * @throws IOException when an existing file cannot be read
     */
    public void loadData() throws IOException {
        authors.clear();
        books.clear();
        if (Files.exists(authorFile))
            try (BufferedReader reader =
                    Files.newBufferedReader(authorFile, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] p = line.split("\\|", -1);
                    if (p.length == 2 && !p[0].trim().isEmpty())
                        authors.put(p[0], new Author(p[0], p[1]));
                }
            }
        if (Files.exists(bookFile))
            try (BufferedReader reader =
                    Files.newBufferedReader(bookFile, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] p = line.split("\\|", -1);
                    if (p.length == 4 && authors.containsKey(p[3]))
                        try {
                            books.put(p[0], new Book(p[0], p[1], Double.parseDouble(p[2]), p[3]));
                        } catch (NumberFormatException ignored) {
                            /* Skip malformed price. */
                        }
                }
            }
    }

    /** @return all books in insertion order as a read-only list */
    public List<Book> getBooks() {
        return Collections.unmodifiableList(new ArrayList<>(books.values()));
    }

    /** @param authorId ID to find @return author or null */
    public Author findAuthor(String authorId) {
        return authors.get(authorId);
    }

    /** @param book new book @throws IllegalArgumentException for duplicate ISBN, missing author, or invalid data */
    public void addBook(Book book) {
        validateBook(book);
        if (books.containsKey(book.getId()))
            throw new IllegalArgumentException("ISBN already exists.");
        books.put(book.getId(), book);
    }

    /** @param isbn ISBN to find @return book or null */
    public Book findBook(String isbn) {
        return books.get(isbn);
    }

    /**
     * Updates only supplied values.
     * @param isbn existing ISBN @param title new title or blank @param price new price or null
     * @param authorId new author ID or blank @throws IllegalArgumentException for missing book/author
     */
    public void updateBook(String isbn, String title, Double price, String authorId) {
        Book book = books.get(isbn);
        if (book == null) throw new IllegalArgumentException("Book does not exist.");
        if (!title.isEmpty()) book.setTitle(title);
        if (price != null) book.setPrice(price);
        if (!authorId.isEmpty()) {
            if (!authors.containsKey(authorId))
                throw new IllegalArgumentException("Author does not exist.");
            book.setAuthorId(authorId);
        }
    }

    /** @param isbn ISBN to delete @return true when deleted */
    public boolean deleteBook(String isbn) {
        return books.remove(isbn) != null;
    }

    /** @param text title fragment @return all case-insensitive title matches */
    public List<Book> searchByTitle(String text) {
        List<Book> result = new ArrayList<>();
        String key = text.toLowerCase();
        for (Book book : books.values())
            if (book.getTitle().toLowerCase().contains(key)) result.add(book);
        return result;
    }

    /** @throws IOException when book.dat cannot be written */
    public void saveBooks() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(bookFile, StandardCharsets.UTF_8)) {
            for (Book book : books.values()) {
                writer.write(book.toDataLine());
                writer.newLine();
            }
        }
    }

    /** @param book book to validate @throws IllegalArgumentException when required data is invalid */
    private void validateBook(Book book) {
        if (book == null || book.getId().trim().isEmpty() || book.getTitle().trim().isEmpty())
            throw new IllegalArgumentException("Book data must not be empty.");
        if (book.getPrice() <= 0) throw new IllegalArgumentException("Price must be positive.");
        if (!authors.containsKey(book.getAuthorId()))
            throw new IllegalArgumentException("Author does not exist.");
    }
}
