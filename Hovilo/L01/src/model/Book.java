package model;

/** Stores a book and its author relationship. @author Ho Vi Lo @since 09/09/2026 */
public class Book extends StoreItem {
    private String title;
    private double price;
    private String authorId;

    /** @param isbn immutable ISBN @param title title @param price positive price @param authorId existing author ID */
    public Book(String isbn, String title, double price, String authorId) {
        super(isbn);
        this.title = title;
        this.price = price;
        this.authorId = authorId;
    }

    /** @return title */
    public String getTitle() {
        return title;
    }

    /** @param title new title */
    public void setTitle(String title) {
        this.title = title;
    }

    /** @return price */
    public double getPrice() {
        return price;
    }

    /** @param price new price */
    public void setPrice(double price) {
        this.price = price;
    }

    /** @return author ID */
    public String getAuthorId() {
        return authorId;
    }

    /** @param authorId new author ID */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /** @return pipe-separated book record */
    @Override
    public String toDataLine() {
        return getId() + "|" + title + "|" + price + "|" + authorId;
    }
}
