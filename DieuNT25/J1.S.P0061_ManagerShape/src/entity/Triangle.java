/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import static java.lang.Math.sqrt;

/**
 *
 * @author Admin
 */
public class Triangle extends Shape{
    private double sideA;
    private double sideB;
    private double sideC;


    public Triangle(double sideA, double sideB, double sideC) {
        setSide(sideA,sideB,sideC);
    }
    
    private void setSide(double a, double b, double c){
        //kiểm tra tất cả điều kiện cho tam giác hợp lệ
        if(a<=0 || b <=0 || c<=0){
            throw new IllegalArgumentException("Side a,b,c must be > 0") ;
        }
        if(a+b<=c || a+c<= b || b+c <= a){
            throw new IllegalArgumentException("sum of any two side must be greater than the remaining side");
        }
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
        
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }
    
    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC ; 
    }

    @Override
    public double getArea() {
        double p = getPerimeter()/2 ;
        return sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
    }

    @Override
    public void printResult() {
        System.out.println("-----Triangle-----");
        System.out.println("Side A: "+ this.sideA);
        System.out.println("Side B: "+ this.sideB);
        System.out.println("Side C: "+ this.sideC);
        System.out.println("Area: "+ getArea());
        System.out.println("Perimeter: "+ getPerimeter());
        
    }

}
