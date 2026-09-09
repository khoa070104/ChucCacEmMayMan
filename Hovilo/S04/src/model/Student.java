package model;

/**
 * Stores one student's marks and classification.
 *
 * @author Ho Vi Lo
 * @since 09/09/2026
 */
public class Student {
    private final String name;
    private final String className;
    private final double maths;
    private final double chemistry;
    private final double physics;
    private double average;
    private String type;

    /**
     * Creates a student before classification.
     *
     * @param name student name
     * @param className class name
     * @param maths mathematics mark
     * @param chemistry chemistry mark
     * @param physics physics mark
     */
    public Student(String name, String className, double maths, double chemistry, double physics) {
        this.name = name;
        this.className = className;
        this.maths = maths;
        this.chemistry = chemistry;
        this.physics = physics;
    }

    /** @return student name */
    public String getName() {
        return name;
    }

    /** @return class name */
    public String getClassName() {
        return className;
    }

    /** @return arithmetic mean */
    public double getAverage() {
        return average;
    }

    /** @param average calculated arithmetic mean */
    public void setAverage(double average) {
        this.average = average;
    }

    /** @return classification A, B, C, or D */
    public String getType() {
        return type;
    }

    /** @param type calculated classification */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Calculates the arithmetic mean of the three marks.
     *
     * @return average mark
     */
    public double calculateAverage() {
        return (maths + chemistry + physics) / 3;
    }
}
