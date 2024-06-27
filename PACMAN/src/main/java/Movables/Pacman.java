package Movables;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.ImageView;
import model.Coordinate;
import model.Data;

import java.time.LocalTime;
import java.util.Timer;

public class Pacman extends Characters {
    private int heart;
    private int score;

    private int lastTimeHit = -1;
    public Pacman(Coordinate coordinate) {
        super();
        score = 0;
        heart = 3;
        try {
        Image image = new Image("file:src/main/resources/Images/pacman"+direction+".png");
        ImagePattern imagePattern = new ImagePattern(image);
        setFill(imagePattern);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
        Image image = new Image("file:src/main/resources/Images/pacman"+direction+".png");
        ImagePattern imagePattern = new ImagePattern(image);
        setFill(imagePattern);
    }
    public String getDirection() {
        return this.direction;
    }

    public int getHeart() {
        return heart;
    }
    public void setHeart(int heart) {
        this.heart = heart;

        this.lastTimeHit = LocalTime.now().getSecond();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        ///// upate score
    }
    public int getLastTimeHit() {
        return lastTimeHit;
    }

    public void setLastTimeHit(int lastTimeHit) {
        this.lastTimeHit = lastTimeHit;
    }
    public void setDeadPicture() {
        Image image = new Image("file:src/main/resources/Images/deadpacman"+direction+".png");
        ImagePattern imagePattern = new ImagePattern(image);
        setFill(imagePattern);
    }
}
