package core;

import player.Player;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.CoinGenerator;
import utils.RandomUtils;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
2d board filled with floor tiles surrounded by walls (hallways and rooms)
 */
public class World {
    private List<Room> rooms = new ArrayList<>();
    private List<Hallway> hallways = new ArrayList<>();
    private int worldHeight;
    private int worldWidth;
    private final int maxRoomSize = 10;//both width and height
    private final int minRoomSize = 2;//both width and height
    double worldDensity;
    private Random randomGenerator;

    private Player player;
    Coordinate playerPosition;

    List<Coordinate> coins;

    int seed;

    /*
    Create 2d world of size worldHeight * worldWidth and fill it with number of rooms depending on density.
    Create Rooms of random sizes and position and connect them with hallways.
     */
    public World (int worldHeight, int worldWidth, int seed, double worldDensity) {
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
        this.randomGenerator = new Random(seed);
        this.worldDensity = worldDensity;
        generateWorld();
        this.seed = seed;
    }

    //generate Rooms and Hallways
    private void generateWorld() {
        //Initialise room generator
        RoomGenerator RG = new RoomGenerator(randomGenerator, worldHeight, worldWidth, minRoomSize, maxRoomSize);
        HallwaysGenerator HG;

        //Generate rooms
        rooms = RG.generateRooms(worldDensity);

        //Initialise Hallway Generator
        HG = new HallwaysGenerator(rooms, randomGenerator);

        //Generate hallways
        hallways = HG.generateHallways();

        //Generate player
        player = new Player(worldWidth, worldHeight, randomGenerator, getAllFloors());
        playerPosition = player.spawnPlayer();

        //Generate coins
        coins = new CoinGenerator().generateCoins(10, randomGenerator, getAllFloors());
    }

    //Get all walls Coordinates by going through all rooms and hallways
    public List<Coordinate> getAllWalls() {
        List<Coordinate> wallsCoordinates = new ArrayList<>();

        for (Room room : rooms) {
            wallsCoordinates.addAll(room.getWalls());
        }

        for (Hallway hallway : hallways) {
            wallsCoordinates.addAll(hallway.getWall());
        }

        return wallsCoordinates;
    }

    //Get all floors Coordinates by going through all rooms and hallways
    public List<Coordinate> getAllFloors() {
        List<Coordinate> floorCoordinates = new ArrayList<>();

        for (Room room : rooms) {
            floorCoordinates.addAll(room.getFloor());
        }

        for (Hallway hallway : hallways) {
            floorCoordinates.addAll(hallway.getFloor());
        }
        return floorCoordinates;
    }

    public int getWidth() {
        return worldWidth;
    }

    public int getHeight() {
        return worldHeight;
    }

    public Coordinate getPlayerPosition() {
        return playerPosition;
    }

    //update world state
    public void updateState(char direction) {
        updatePlayerPosition( direction);
    }

    //if W,S,A or D update player position by moving in one of four directions
    private void updatePlayerPosition(char direction) {
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
        playerPosition = player.updatePositin(playerPosition.x + x , playerPosition.y + y);
    }

    //return seed used for world generation
    public int getSeed() {
        return seed;
    }

    public double getDensity() {
        return worldDensity;
    }

    public void setPlayerPosition(int x, int y) {
       playerPosition = player.updatePositin(x, y);
    }

    public List<Coordinate> getCoins() {
        return coins;
    }
}
