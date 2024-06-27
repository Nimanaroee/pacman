package model;

public class Coordinate{

    public static final Coordinate UP = new Coordinate(0, -1*Data.speed);
    public static final Coordinate DOWN = new Coordinate(0, Data.speed);
    public static final Coordinate LEFT = new Coordinate(-1*Data.speed, 0);
    public static final Coordinate RIGHT = new Coordinate(Data.speed, 0);


    double x, y;
    public Coordinate(int x, int y, double length) {
        this.x = x*length;
        this.y = y*length;
    }
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public static double getXDirection(String direction) {
        switch (direction) {
            case "RIGHT" :
                return Data.speed;
            case "LEFT" :
                return -1*Data.speed;
            default :
                return 0.0;
        }
    }
    public static double getYDirection(String direction) {
        switch (direction) {
            case "UP" :
                return -1*Data.speed;
            case "DOWN" :
                return Data.speed;
            default :
                return 0.0;
        }
    }
}
