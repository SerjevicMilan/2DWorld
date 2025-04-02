package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Creates random world and renders it's content
public class RendererWorld {
    //2d array used to represent world state
    TETile[][] worldTiles;
    TERenderer rendererWorld = new TERenderer();
    //render engine used to display world state;

    //world-class and Random number generator
    World world;
    Random randomGenerator;

    //All coordinates representing wall and floor tiles
    List<Coordinate> wallCordinates = new ArrayList<>();
    List<Coordinate> floorCordinates = new ArrayList<>();

    //World dimensions
    public int height = 0;
    public int width = 0;

    //Generate world and retrieves and initialise Coordinates lists
    public void initialiseWorld(int height, int width, int seed, double worldDensity, int minRoomSize, int maxRoomSize) {
        this.height = height;
        this.width = width;

        this.randomGenerator = new Random(seed);//initialise random generator

        rendererWorld.initialize(width, height);//initialise render engine
        worldTiles = new TETile[width][height];//initialise 2d array of tiles

        world = new World(height, width, randomGenerator, worldDensity, minRoomSize, maxRoomSize);//create world

        wallCordinates = world.getAllWalls();//retrieves all Wall coordinates
        floorCordinates = world.getAllFloors();//retrieves all Floor coordinates

        fillWorldTiles();//add wall and floor tiles to worldTiles
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
