
package view;

import Movables.Ghost;
import Movables.Pacman;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Coordinate;
import model.Data;
import model.Map.Block;

import java.time.LocalTime;
import java.util.ArrayList;


public class GameMenuView extends Application {
    Pane root;
    private static ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    private static Pacman pacman;

    @Override
    public void start(Stage stage) throws Exception {

        ////// create grid and maze
        root = new Pane();

        generateMaze();

        stage.setScene(new Scene(root, Data.V, Data.V));
        stage.show();

        ///// move characters
        root.setFocusTraversable(true);
        root.requestFocus();

        // Add key pressed event handler to the pane
        root.setOnKeyPressed(event -> changeDirectionOfPacman(event));

        // Use AnimationTimer for continuous movement
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveCharacters();
            }
        };
        timer.start();
    }

    private void changeDirectionOfPacman(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                pacman.setDirection("UP");
                break;
            case S:
            case DOWN:
                pacman.setDirection("DOWN");
                break;
            case A:
            case LEFT:
                pacman.setDirection("LEFT");
                break;
            case D:
            case RIGHT:
                pacman.setDirection("RIGHT");
                break;
        }
    }

    private void moveCharacters() {
        ///// move ghost
        for(int i=0 ; i<ghosts.size() ; i++) {
            Ghost ghost = ghosts.get(i);
            ghost.setCenterX(ghost.getCenterX()+Coordinate.getXDirection(ghost.getDirection()));
            ghost.setCenterY(ghost.getCenterY()+Coordinate.getYDirection(ghost.getDirection()));
            if(ghost.isHit()) {
                ghost.setCenterX(ghost.getCenterX()-Coordinate.getXDirection(ghost.getDirection())*0.8);
                ghost.setCenterY(ghost.getCenterY()-Coordinate.getYDirection(ghost.getDirection())*0.8);
                ghost.setDirection(ghost.getRandomDirection());
            }
            ghosts.set(i, ghost);
        }
        //// move pacman
        pacman.setCenterX(pacman.getCenterX()+Coordinate.getXDirection(pacman.getDirection()));
        pacman.setCenterY(pacman.getCenterY()+Coordinate.getYDirection(pacman.getDirection()));
        if(pacman.isHit()) {
            pacman.setCenterX(pacman.getCenterX()-Coordinate.getXDirection(pacman.getDirection()));
            pacman.setCenterY(pacman.getCenterY()-Coordinate.getYDirection(pacman.getDirection()));
        }

        checkEatDot();
        checkHitByGhost();
        //System.out.println(pacman.getX() + " -- " + pacman.getY() + " -- " + pacman.isHit());
    }
    public void checkEatDot() {
        for(int i=0 ; i<Data.length ; i++) {
            for(int j=0 ; j<Data.length ; j++) {
                if(Data.grid[i][j].isHasDot() && pacman.isHitCenter(Data.grid[i][j])) {
                    Data.grid[i][j].setHasDot(false);
                    pacman.setScore(pacman.getScore()+1);
                }
            }
        }
    }
    public void checkHitByGhost() {
        if(LocalTime.now().getSecond() < pacman.getLastTimeHit()+2) {
            pacman.setDeadPicture();
            return;
        }
        for (Ghost ghost : ghosts) {
            if (ghost.getBoundsInParent().intersects(pacman.getBoundsInParent())) {
                if (pacman.getHeart() != 0)
                    pacman.setHeart(pacman.getHeart() - 1);
//                else
//                    /////// game over page
            }
        }
    }
    private void generateMaze() {
        model.Map.MazeBuilder maze = new model.Map.MazeBuilder((Data.length-1)/4);
        maze.draw();
        char[][] grid = maze.getGrid();
        Data.grid = new Block[Data.length][Data.length];

        for(int i=0 ; i<Data.length ; i++) {
            for(int j=0 ; j<Data.length ; j++) {
                if(grid[i][j] == '.') {
                    Data.grid[i][j] = new Block("dot", i, j);
                    Data.numberOfDots++;
                } else {
                    Data.grid[i][j] = new Block("wall", i, j);
                }

                Data.grid[i][j].setX(Data.grid[i][j].getCoordinate().getX());
                Data.grid[i][j].setY(Data.grid[i][j].getCoordinate().getY());

                root.getChildren().add(Data.grid[i][j]);

            }
        }

        ///// adddddd pacman and ghost
        int numberOfGhost = (Data.length-1)/4;
        for(int i=1 ; i<Data.length ; i++) {
            if(numberOfGhost == 0)
                break;
            if(!Data.grid[i][Data.length-2].getType().equals("wall")) {
                addGhost(Data.grid[i][Data.length-2].getCoordinate(), numberOfGhost);
                numberOfGhost--;
            }
        }
        addPacman(Data.grid[1][1].getCoordinate());
    }
    void addGhost(Coordinate coordinate, int numberOfGhost) {
        Ghost newGhost = new Ghost(coordinate, numberOfGhost);
        ghosts.add(newGhost);
        newGhost.setCenterX(coordinate.getX()+5);
        newGhost.setCenterY(coordinate.getY()+5);
        root.getChildren().add(newGhost);
    }
    void addPacman(Coordinate coordinate) {

        pacman = new Pacman(coordinate);
        pacman.setCenterX(coordinate.getX()+5);
        pacman.setCenterY(coordinate.getY()+5);
        root.getChildren().add(pacman);
    }
}