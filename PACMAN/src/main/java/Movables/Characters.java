package Movables;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Coordinate;
import model.Data;
import model.Map.Block;

public class Characters extends Circle {
    String direction;
    public Characters() {
        super(Data.lengthImage*0.6);
        direction = "UP";
    }
    public boolean isHit() {
        for(int i=0 ; i<Data.length ; i++) {
            for(int j=0 ; j<Data.length ; j++) {
                Block block = Data.grid[i][j];
                if(block.getType().equals("wall") && block.getBoundsInParent().intersects(this.getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isHitCenter(Block block) {
        double x = block.getX()+block.getWidth()/2.0;
        double y = block.getY()+block.getHeight()/2.0;
        if(this.contains(x, y))
            return true;
        return false;
    }
    public String getRandomDirection() {
        int rand = (int) (4*Math.random());
        if(rand%4 == 0)
            return "UP";
        else if(rand%4 == 1)
            return "DOWN";
        else if(rand%4 == 2)
            return "LEFT";
        else
            return "RIGHT";
    }
}
