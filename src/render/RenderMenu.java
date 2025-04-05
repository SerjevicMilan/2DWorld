package render;

import core.Coordinate;
import edu.princeton.cs.algs4.StdDraw;
import menu.Menu;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

public class RenderMenu implements GRender {
    //2d array for render engine
    TETile[][] menuTiles;
    TERenderer renderMT;

    Menu menu;

    public RenderMenu(Menu menu, TERenderer renderMT) {
        //initilise for rendering
        this.renderMT = renderMT;
        this.menu = menu;
        initilise();
    }

    //renders background and draws menu
    public void render() {
        renderMT.renderFrame(menuTiles);//render tiles
        menu.drawMenu();//draw text
        StdDraw.show();//show text
    }

    //initilse 2d array of tiles for rendering and fill it tiles
    private void initilise() {
        menuTiles = new TETile[menu.getWidth()][menu.getHeight()];
        fillMenuTiles();
    }

    //fill menu background
    private void fillMenuTiles() {
        for (Coordinate pos : menu.getBlackScreenCoordinates()) {
            menuTiles[pos.x][pos.y] = Tileset.NOTHING;
        }
    }
}
