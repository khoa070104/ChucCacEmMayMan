package model;

public class Circle extends Shape {

    private double radius;

    //constrctor khong tham so
    public Circle() {
    }

    //constructor co tham so
    public Circle(double radius) {
        this.radius = radius;
    }
    //Getter and Setter

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    //Tinh chu vi
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    //Tra ve chuoi
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Radius: ").append(radius);
        sb.append("\nArea: ").append(getArea());
        sb.append("\nPerimeter: ").append(getPerimeter());
        return sb.toString();
    }

}
