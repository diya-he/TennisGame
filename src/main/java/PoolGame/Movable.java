package PoolGame;

public interface Movable {
    double getXPos();
    double getYPos();
    double getXVel(); // Vel stands for velocity
    double getYVel();
    void setXPos(double xPos);
    void setYPos(double yPos);
    void setXVel(double xVel);
    void setYVel(double yVel);
    void move();
}
