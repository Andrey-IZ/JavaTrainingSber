package com.sber.javaschool.hometask1.hierarchy;

public class Triangle extends Shape {

    private int side1;
    private int side2;
    private int side3;

    public Triangle(int x, int y, int side1, int side2, int side3) {
        super("Triangle", x, y);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void resize(double coefficient) {
        this.side1 *= coefficient;
        this.side2 *= coefficient;
        this.side3 *= coefficient;
    }

    @Override
    public double getArea() {
        var s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Triangle at:(" + getX() + ", " + getY() +
                "), side1:" + side1 + ", side2: " + side2 + ", side3: " + side3);
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "side1=" + side1 +
                ", side2=" + side2 +
                ", side3=" + side3;
    }

    public int getSide1() {
        return side1;
    }

    public void setSide1(int side1) {
        this.side1 = side1;
    }

    public int getSide2() {
        return side2;
    }

    public void setSide2(int side2) {
        this.side2 = side2;
    }

    public int getSide3() {
        return side3;
    }

    public void setSide3(int side3) {
        this.side3 = side3;
    }

}
