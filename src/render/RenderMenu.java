package render;

import core.Coordinate;
import menu.Menu;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

public class RenderMenu implements GRender {
    //2d array for render engine
    TETile[][] menuTiles;
    TERenderer renderMT;

    Menu menu;

    public RenderMenu(Menu menu) {
        //initilise for rendering
        this.menu = menu;
        initilise();
    }

    //renders background and draws menu
    public void render() {
        renderMT.renderFrame(menuTiles);
        menu.drawMenu();
    }

    private void initilise() {
        renderMT = new TERenderer();
        renderMT.initialize(menu.getWidth(), menu.getHeight());
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
