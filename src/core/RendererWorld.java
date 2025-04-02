package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RendererWorld {
    TETile[][] worldTiles;
    TERenderer rendererWorld = new TERenderer();
    World world;
    Random randomGenerator;
    List<Coordinate> wallCordinates = new ArrayList<>();
    List<Coordinate> floorCordinates = new ArrayList<>();
    public int height = 0;
    public int width = 0;

    public void initialiseWorld(int height, int width, int seed, double worldDensity, int minRoomSize, int maxRoomSize) {
        this.height = height;
        this.width = width;
        this.randomGenerator = new Random(seed);
        rendererWorld.initialize(width, height);
        worldTiles = new TETile[width][height];
        world = new World(height, width, randomGenerator, worldDensity, minRoomSize, maxRoomSize);
        wallCordinates = world.getAllWalls();
        floorCordinates = world.getAllFloors();
        fillWorldTiles();
    }

    //Fills worldTiles with Nothing, Wall and Floor tiles.
    //Prepares worldTiles for rendering
    public void fillWorldTiles() {
       fillNothing();
       fillWall();
       fillFloor();
    }

    //Fills whole worldTiles array with Nothing tiles(it would throw null exception otherwise
    public void fillNothing() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                worldTiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    //Fills wall tiles on specific coordinates
    public void fillWall() {
        for (Coordinate wallPos: wallCordinates) {
            worldTiles[wallPos.x][wallPos.y] = Tileset.WALL;
        }
    }

    //Fills floor tiles on specific coordinates
    public void fillFloor() {
        for (Coordinate floorPos: floorCordinates) {
            worldTiles[floorPos.x][floorPos.y] = Tileset.FLOOR;
        }
    }

    //renders world
    public void renderFrame() {
        rendererWorld.renderFrame(worldTiles);
    }
}
