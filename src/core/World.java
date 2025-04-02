package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.RandomUtils;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
2d board filled with floor tiles surrounded by walls (hallways and rooms)
 */
public class World {
    List<Room> rooms = new ArrayList<>();
    List<Hallway> hallways = new ArrayList<>();
    int height;
    int width;
    private int maxRoomSize;
    private int minRoomSize;
    double worldDensity;
    Random randomGenerator;

    public World (int height, int width, Random randomGenerator, double worldDensity, int minRoomSize, int maxRoomSize) {
        this.height = height;
        this.width = width;
        this.randomGenerator = randomGenerator;
        this.worldDensity = worldDensity;
        this.maxRoomSize = maxRoomSize;
        this.minRoomSize = minRoomSize;
        generateWorld();
    }

    private void generateWorld() {
        RoomGenerator RG = new RoomGenerator(randomGenerator, height, width, minRoomSize, maxRoomSize);
        HallwaysGenerator HG;
        rooms = RG.generateRooms(worldDensity);
        HG = new HallwaysGenerator(rooms, randomGenerator);
        hallways = HG.generateHallways();
    }

    public List<Coordinate> getAllWalls() {
        List<Coordinate> wallsCordinates = new ArrayList<>();

        for (Room room : rooms) {
            wallsCordinates.addAll(room.getWalls());
        }

        for (Hallway hallway : hallways) {
            wallsCordinates.addAll(hallway.getWall());
        }

        return wallsCordinates;
    }

    public List<Coordinate> getAllFloors() {
        List<Coordinate> floorCordinates = new ArrayList<>();

        for (Room room : rooms) {
            floorCordinates.addAll(room.getFloor());
        }

        for (Hallway hallway : hallways) {
            floorCordinates.addAll(hallway.getFloor());
        }
        return floorCordinates;
    }
}
