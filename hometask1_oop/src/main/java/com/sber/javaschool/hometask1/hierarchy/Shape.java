package com.sber.javaschool.hometask1.hierarchy;

public abstract class Shape implements Movable {
    protected int x;
    protected int y;

    protected String type;

    public Shape(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract void draw();

    public static double getArea(Shape shape) {
        return shape.getArea();
    }

    public static double getPerimeter(Shape shape) {
        return shape.getPerimeter();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return getType() + ": coord=(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }

    public String getType() {
        return type;
    }
}

