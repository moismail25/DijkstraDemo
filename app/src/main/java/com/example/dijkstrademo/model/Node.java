package com.example.dijkstrademo.model;

import android.graphics.Color;

public class Node {
    private int x;
    private int y;

    private char val;

    private int color;

    public Node(int x, int y, char val, int color) {
        this.x = x;
        this.y = y;
        this.val = val;
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getVal() {
        return val;
    }

    public void setVal(char val) {
        this.val = val;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
