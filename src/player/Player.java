package player;

import utils.Coordinate;

import java.util.List;
import java.util.Random;

//Pseudo random generate coordinates representing player
//by generating coordinates until you find one that are in part of floor coordinates
public class Player {
    //generates number
    private Random randomGenerator;
    //list of coordinates representing valid coordinates
    private List<Coordinate> floorTiles;

    //numbers representing boundaries of world in which player can be rendered
    private int worldWidth;
    private int worldHeight;

    //position of player in world
    private Coordinate position;

    //initialise values need for player generation
    public Player(int worldWidth, int worldHeight,Random randomGenerator, List<Coordinate> floorTiles) {
        this.randomGenerator = randomGenerator;
        this.floorTiles = floorTiles;
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
    }

    //Pseudo random generate coordinates representing player
    public Coordinate spawnPlayer() {
        generatePlayer();
        return position;
    }

    //return player position
    public Coordinate getPosition() {
        return position;
    }

    //generating coordinates until you find one that are in part of floor coordinates
    private void generatePlayer() {
        int x;
        int y;
        Coordinate potentialPosition;

        while (true) {
            x = randomGenerator.nextInt(0, worldWidth);
            y = randomGenerator.nextInt(0, worldHeight);
            potentialPosition = new Coordinate(x, y);
            if (floorTiles.contains(potentialPosition)) {
                position = potentialPosition;
                break;
            }
        }
    }

}
