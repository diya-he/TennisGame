package PoolGame.items;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private double length = 600;
    private double height = 300;

    private double friction = 1.0;

    private String colour = "green";

    private double radius = 30;

    private List<List<Double>> holes = new ArrayList<>();
    public Table(double length, double height, double friction, String colour) {
        this.length = length;
        this.height = height;
        this.friction = friction;
        this.colour = colour;
        this.setHoles(length, height);
    }

    public void setHoles(double length, double height){
        List hole1 = new ArrayList();
        hole1.add(0.0);
        hole1.add(0.0);
        holes.add(hole1);
        List hole2 = new ArrayList();
        hole2.add(length/2);
        hole2.add(0.0);
        List hole3 = new ArrayList();
        hole3.add(length);
        hole3.add(0.0);
        List hole4 = new ArrayList();
        hole4.add(0.0);
        hole4.add(height);
        List hole5 = new ArrayList();
        hole5.add(length/2);
        hole5.add(height);
        List hole6 = new ArrayList();
        hole6.add(length-0);
        hole6.add(height);
        holes.add(hole1);
        holes.add(hole2);
        holes.add(hole3);
        holes.add(hole4);
        holes.add(hole5);
        holes.add(hole6);
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public double getFriction() {
        return friction;
    }

    public String getColour() {
        return colour;
    }

    public List<List<Double>> getHoles() {
        return holes;
    }

    public double getRadius() {
        return radius;
    }
}
