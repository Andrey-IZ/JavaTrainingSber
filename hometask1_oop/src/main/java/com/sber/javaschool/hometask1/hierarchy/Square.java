package com.sber.javaschool.hometask1.hierarchy;

public class Square extends Rect {
    public Square(int x, int y, int sideLength) {
        super(x, y, sideLength, sideLength);
        this.setType("Square");
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "side=" + getWidth();
    }
}
