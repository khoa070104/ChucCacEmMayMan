/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import static java.lang.Math.PI;

/**
 *
 * @author Admin
 */
public class Circle extends Shape {
    
    private double radius;


    public Circle(double radius) {
        // Gọi setter để thiết lập giá trị và kiểm tra điều kiện
        this.setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if(radius <= 0){
            throw new IllegalArgumentException("radius must be a positve number");
        }
        this.radius = radius;
    }
    
    
    
    @Override
    public double getPerimeter() {
        return 2* PI * radius  ;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }

    @Override
    public void printResult() {
        System.out.println("-----Circle-----");
        System.out.println("Radius:" + this.radius);
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }

}
