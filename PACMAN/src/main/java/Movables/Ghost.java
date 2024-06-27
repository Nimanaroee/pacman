package Movables;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Coordinate;

public class Ghost extends Characters {

    public Ghost(Coordinate coordinate, int numberOfGhost) {
        super();
        try {
            Image image = new Image("file:src/main/resources/Images/ghost"+numberOfGhost+".png");        //imageView.setSmooth(true);
            ImagePattern imagePattern = new ImagePattern(image);
            setFill(imagePattern);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return this.direction;
    }

}
