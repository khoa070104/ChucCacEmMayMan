package view;

import controller.BookManager;
import controller.Inputter;

import model.Author;
import model.Book;

import java.io.IOException;
import java.util.List;

/** Displays and controls the HKT Book Store program. @author Ho Vi Lo @since 09/09/2026 */
public class MainView {
    private final Inputter inputter = new Inputter();
    private final BookManager manager = new BookManager();

    /** Loads data and handles menu choices until Quit is selected. */
    public void run() {
        try {
            manager.loadData();
        } catch (IOException exception) {
            System.out.println("Could not read data: " + exception.getMessage());
        }
        while (true) {
            System.out.println(
                    "\n1. Show book list\n2. Add new book\n3. Update book\n4. Delete book\n5. Search book\n6. Store data to file\nOthers. Quit");
            int choice = inputter.readInt("Your choice: ");
            try {
                if (choice == 1) display(manager.getBooks());
                else if (choice == 2) add();
                else if (choice == 3) update();
                else if (choice == 4) delete();
                else if (choice == 5) search();
                else if (choice == 6) {
                    manager.saveBooks();
                    System.out.println("Data stored successfully.");
                } else return;
            } catch (IllegalArgumentException | IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /** Adds one or more books until the user chooses to return. */
    private void add() {
        do {
            String isbn = inputter.readRequired("ISBN: ");
            String title = inputter.readRequired("Title: ");
            double price = inputter.readPrice("Price: ", false);
            String authorId = inputter.readRequired("Author ID: ");
            manager.addBook(new Book(isbn, title, price, authorId));
            System.out.println("Book added successfully.");
        } while (inputter.readYesNo("Add another book? (Y/N): "));
    }

    /** Updates an existing book while blank values keep the old information. */
    private void update() {
        String isbn = inputter.readRequired("ISBN: ");
        if (manager.findBook(isbn) == null) {
            System.out.println("Book does not exist.");
            return;
        }
        String title = inputter.readLine("New title (blank to keep): ");
        Double price = inputter.readPrice("New price (blank to keep): ", true);
        String authorId = inputter.readLine("New author ID (blank to keep): ");
        manager.updateBook(isbn, title, price, authorId);
        System.out.println("Book updated successfully.");
    }

    /** Deletes a book after explicit confirmation. */
    private void delete() {
        String isbn = inputter.readRequired("ISBN: ");
        if (!inputter.readYesNo("Are you sure? (Y/N): ")) {
            System.out.println("Deletion cancelled.");
            return;
        }
        System.out.println(
                manager.deleteBook(isbn) ? "Book deleted successfully." : "Book does not exist.");
    }

    /** Searches books by title text and displays all matches. */
    private void search() {
        display(manager.searchByTitle(inputter.readLine("Search text: ")));
    }

    /** @param books books to display as a formatted table */
    private void display(List<Book> books) {
        System.out.printf(
                "%-15s %-30s %10s %-12s %-20s%n",
                "ISBN", "Title", "Price", "Author ID", "Author name");
        for (Book book : books) {
            Author author = manager.findAuthor(book.getAuthorId());
            System.out.printf(
                    "%-15s %-30s %10.2f %-12s %-20s%n",
                    book.getId(),
                    book.getTitle(),
                    book.getPrice(),
                    book.getAuthorId(),
                    author == null ? "Unknown" : author.getName());
        }
        if (books.isEmpty()) System.out.println("No books found.");
    }
}
