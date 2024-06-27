package model.Map;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Coordinate;
import model.Data;


public class Block extends Rectangle {
    private String type;
    private Coordinate coordinate;
    private boolean hasDot = true;

    public Block(String type, int rowNum, int colNum){
        super(Data.lengthImage, Data.lengthImage);

        this.type = type;
        this.coordinate = new Coordinate(rowNum, colNum, Data.lengthImage);

        try {
            Image image = new Image("file:src/main/resources/Images/"+type+".png");
            ImagePattern imagePattern = new ImagePattern(image);
            setFill(imagePattern);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public boolean isHasDot() {
        return hasDot;
    }

    public void setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
        Image image = new Image("file:src/main/resources/Images/"+"empty"+".png");
        ImagePattern imagePattern = new ImagePattern(image);
        setFill(imagePattern);
    }
}
