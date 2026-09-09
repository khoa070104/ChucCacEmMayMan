package model;

/**
 * Common display behavior for store data, used to demonstrate polymorphism.
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public abstract class StoreItem {
    private final String id;

    /** @param id immutable item identifier */
    protected StoreItem(String id) {
        this.id = id;
    }

    /** @return immutable identifier */
    public String getId() {
        return id;
    }

    /** @return one line suitable for saving to a data file */
    public abstract String toDataLine();
}
