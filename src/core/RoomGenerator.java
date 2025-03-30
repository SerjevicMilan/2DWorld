package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.FileUtils;

import static utils.RandomUtils.uniform;


//Generate rooms with random placements and sizes
public class RoomGenerator {
    private int width;
    private int height;
    List<Room> rooms = new ArrayList<>();
    Random random;
    private static final int AVERAGE_ROOM_AREA = 60;

    //take pseudo random number generator and 2d world dimensions
    public RoomGenerator(Random random, int height, int width) {
        this.width = width;
        this.height = height;
        this.random = random;
    }

    //generate random rooms(number of rooms depends on density)
    public List<Room> generateRooms(double density) {
        int numberOfRooms = calcNumberOfRooms(density);

        for (int i = 0; i < numberOfRooms; i++) {
            rooms.add(generateRoom());
        }
        return rooms;
    }

    //calc number of rooms to generate
    private int calcNumberOfRooms(double density) {
        if (density <= 0) { throw new IllegalArgumentException("density needs to be bigger then 0"); }
        return (int) Math.round(width * height * density / AVERAGE_ROOM_AREA);
    }

    //generate random position and room size and create new Room
    private Room generateRoom() {
        Coordinate pos = new Coordinate(uniform(random, width), uniform(random, height));
        int roomWidth = uniform(random, 2, 8);
        int roomHeight = uniform(random, 2, 8);
        Room room;

        if (!canPlace(pos, roomWidth, roomHeight)) {
            return generateRoom();
        }

        room = new Room(pos, roomHeight, roomWidth);

        if (occupied(room)) {
            return generateRoom();
        }

        return room;
    }

    //check if there is already room in that area
    private boolean occupied(Room room) {
        for(Coordinate pos : room.getAllCoordinates()) {
            for(Room r : rooms) {
                if (r.containsCoordinate(pos)) {
                    return true;
                }
            }
        }
        return false;
    }

    //check if room is outside 2d world bounds
    private boolean canPlace(Coordinate pos, int roomWidth, int roomHeight) {
        if (pos.x - roomWidth < 0 || pos.y - roomHeight < 0) { return false; }
        if (pos.x + roomWidth >= width || pos.y + roomHeight >= height) { return false; }

        return true;
    }

}
