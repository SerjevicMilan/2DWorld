package game;

import core.Coordinate;
import core.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Manages player and coin positions, detects collisions, and checks level completion.
Triggers a flag to generate a new level when all coins are collected.
 */
public class WorldState {
    //world-class and Random number generator
    World world = null;

    //All coordinates representing wall and floor tiles
    List<Coordinate> wallCordinates = new ArrayList<>();
    List<Coordinate> floorCordinates = new ArrayList<>();

    //World dimensions
    public int height = 0;
    public int width = 0;
    public double density;
    public int seed;

    //coordinates of player in world
    Coordinate playerPosition;

    //coordinates of all coins
    List<Coordinate> coins;

    /*generateStartingWorld

     */
    public void generateWorld(int worldHeight, int worldWidth, int seed, double worldDensity) {
        height = worldHeight;
        width = worldWidth;
        density = worldDensity;
        this.seed = seed;
        world = new World(worldHeight, worldWidth, seed, worldDensity);
        getAllCordinates();
    }

    public void updateState() {
        if(coins.isEmpty()) {
            generateWorld(height, width, seed + 1, density);
        }
    }

    private void getAllCordinates() {
        floorCordinates = world.getAllFloors();
        wallCordinates = world.getAllWalls();
        coins = world.getCoins();
        playerPosition = world.getPlayerPosition();
    }

    public List<Coordinate> getWalls() {
        return wallCordinates;
    }

    public List<Coordinate> getFloor() {
        return floorCordinates;
    }

    public List<Coordinate> getCoins() {
        return coins;
    }

    public Coordinate getPlayer() {
        return playerPosition;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getSeed() {
        return seed;
    }

    public double getDensity() {
        return density;
    }

    /*if W,S,A or D update player position by moving in one of four directions

     */
    public void updatePlayerPosition(char direction) {
        direction = Character.toUpperCase(direction);
        int x = 0;
        int y = 0;

        if (direction == 'W') {//up
            y += 1;
        }
        if (direction == 'S') {//down
            y -= 1;
        }
        if (direction == 'A') {//left
            x -= 1;
        }
        if (direction == 'D') {//right
            x += 1;
        }
        updatePositin(playerPosition.x + x , playerPosition.y + y);
    }

    /*update player current coordinates

     */
    public void updatePositin(int x, int y) {
        Coordinate potentialPosition  = new Coordinate(x, y);

        if (floorCordinates.contains(potentialPosition)) {//if new position is valid update
            playerPosition = potentialPosition;
            checkPlayerCoinCollision();
        }

    }

    private void checkPlayerCoinCollision() {
        coins.remove(playerPosition);
    }

}
