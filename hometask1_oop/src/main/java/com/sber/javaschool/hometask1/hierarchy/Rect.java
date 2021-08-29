package com.sber.javaschool.hometask1.hierarchy;

public class Rect extends Shape {
    private int height;
    private int width;

    public Rect(int x, int y, int width, int height) {
        super("Rect", x, y);
        this.height = height;
        this.width = width;
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void resize(double coefficient) {
        this.height *= coefficient;
        this.width *= coefficient;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return (height + width) * 2;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle at: (" + getX() + ", " + getY() +
                "), width " + getWidth() + ", height " + getHeight());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "height=" + height +
                ", width=" + width;
    }

    protected void setType(String type) {
        this.type = type;
    }
}
