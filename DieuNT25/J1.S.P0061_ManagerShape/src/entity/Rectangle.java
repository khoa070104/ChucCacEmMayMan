/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Rectangle extends Shape {

    private double width;
    private double lenght;

    public Rectangle(double width, double lenght) {
        //gọi setter để thiết lập giá trị và kiểm tra điều kiện
        if (width <= lenght) {
            setLenght(width);
            setWidth(lenght);
        } else {
            throw new IllegalArgumentException("lenght must be >= width");
        }
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be positive number");
        }
        this.width = width;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        if(lenght <= 0){
            throw new IllegalArgumentException("lenght must be a positive number");
        }
        this.lenght = lenght;
    }

    @Override
    public double getPerimeter() {
        return (lenght + width) * 2;
    }

    @Override
    public double getArea() {
        return lenght * width;
    }

    @Override
    public void printResult() {
        System.out.println("-----Rectangle-----");
        System.out.println("Width: " + this.width);
        System.out.println("Lenght: " + this.lenght);
        System.out.println("Area: " + this.getArea());
        System.out.println("Perimeter: " + this.getPerimeter());
    }
    

}
