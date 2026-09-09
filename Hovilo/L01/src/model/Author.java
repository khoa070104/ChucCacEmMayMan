package model;

/** Stores an author. @author Ho Vi Lo @since 09/09/2026 */
public class Author extends StoreItem {
    private final String name;
    /** @param authorId immutable author ID @param name author name */
    public Author(String authorId, String name) { super(authorId); this.name = name; }
    /** @return author name */ public String getName() { return name; }
    /** @return pipe-separated author record */ @Override public String toDataLine() { return getId() + "|" + name; }
}
