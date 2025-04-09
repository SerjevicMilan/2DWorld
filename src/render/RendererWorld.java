package render;

import utils.Coordinate;
import game.WorldState;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.List;

//Creates random world and renders it's content
public class RendererWorld implements GRender {
    //2d array used to represent world state
    TETile[][] worldTiles;
    TERenderer rendererWorld = new TERenderer();
    //render engine used to display world state;

    //world-class and Random number generator
    WorldState world;

    //All coordinates representing wall and floor tiles
    List<Coordinate> wallCordinates = new ArrayList<>();
    List<Coordinate> floorCordinates = new ArrayList<>();

    //World dimensions
    public int height = 0;
    public int width = 0;

    //coordinates of player in world
    Coordinate playerPosition;

    //coordinates of enemy in world
    Coordinate enemyPosition;

    //coordinates of all coins
    List<Coordinate> coins;

    //initialise world
    public RendererWorld (WorldState world, TERenderer rendererWorld) {
        this.rendererWorld = rendererWorld;
        this.world = world;
    }

    public void render () {
        updateState();//fillDynamicTiles
        renderFrame();
    }

    //Generate world and retrieves and initialise Coordinates lists
    public void updateState() {
        this.height = world.getHeight();
        this.width = world.getWidth();

        floorCordinates = world.getFloor();//retrieves all Wall coordinates
        wallCordinates = world.getWalls();//retrieves all Floor coordinates

        coins = world.getCoins();

        worldTiles = new TETile[width][height];//initialise 2d array of tiles

        playerPosition = world.getPlayer();
        enemyPosition = world.getEnemy();

        fillWorldTiles();//add wall and floor tiles to worldTiles
        world.updateState();
    }

    //Fills worldTiles with Nothing, Wall and Floor tiles.
    //Prepares worldTiles for rendering
    public void fillWorldTiles() {
        fillNothing();
        fillWall();
        fillFloor();
        fillCoins();
        fillPlayer();
        fillEnemy();
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

    //Fill one tile with player cordinates
    private void fillPlayer() {
        worldTiles[playerPosition.x][playerPosition.y] = Tileset.AVATAR;
    }

    //Fill one tile with enemy cordinates
    private void fillEnemy() {
        worldTiles[enemyPosition.x][enemyPosition.y] = Tileset.SAND;
    }

    private void fillCoins() {
        for(Coordinate coin: coins) {
            worldTiles[coin.x][coin.y] = Tileset.FLOWER;
        }
    }

    //renders world
    public void renderFrame() {
        rendererWorld.renderFrame(worldTiles);
    }

}
