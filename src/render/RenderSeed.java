package render;

import core.Coordinate;
import edu.princeton.cs.algs4.StdDraw;
import game.InputHandler;
import menu.Menu;
import seed.Seed;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

public class RenderSeed implements GRender {
    //2d array for render engine
    TETile[][] seedTiles;
    TERenderer renderSD;

    InputHandler inputHandler = new InputHandler();

    Seed seed;

    public RenderSeed(Seed seed, TERenderer renderSD) {
        //initilise for rendering
        this.renderSD = renderSD;
        this.seed = seed;
        initilise();
    }

    //renders background and draws menu
    public void render() {
        renderSD.renderFrame(seedTiles);//render tiles
        seed.drawSeed();//draw text
        StdDraw.show();//show text
    }

    //initilse 2d array of tiles for rendering and fill it tiles
    private void initilise() {
        seedTiles = new TETile[seed.getWidth()][seed.getHeight()];
        fillMenuTiles();
    }


    //fill menu background
    private void fillMenuTiles() {
        for (Coordinate pos : seed.getBlackScreenCoordinates()) {
            seedTiles[pos.x][pos.y] = Tileset.NOTHING;
        }
    }
}
