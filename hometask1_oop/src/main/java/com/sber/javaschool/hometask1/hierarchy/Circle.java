package com.sber.javaschool.hometask1.hierarchy;

public class Circle extends Shape {

    private double radius;

    public Circle(int x, int y, int radius) {
        super("Circle", x, y);
        this.radius = radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.round(Math.PI * Math.sqrt(radius) * 100) / 100d;
    }

    @Override
    public double getPerimeter() {
        return Math.round(Math.PI * 2 * radius * 100) / 100d;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle at: (" + getX() + ", " + getY() +
                "), radius " + getRadius());
    }

    /**
     * @return длину окружности
     */
    public double getCircumference() {
        return getPerimeter();
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void resize(double coefficient) {
        this.radius *= coefficient;
    }

    @Override
    public String toString() {
        return super.toString() + ", radius=" + radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}