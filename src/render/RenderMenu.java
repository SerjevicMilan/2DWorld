package render;

import core.Coordinate;
import menu.Menu;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

public class RenderMenu implements GRender<Menu> {
    //2d array for render engine
    TETile[][] menuTiles;
    TERenderer renderMT;

    Menu menu;

    //renders background and draws menu
    public void render(Menu menu ) {
        this.menu = menu;
        renderMT = new TERenderer();
        renderMT.initialize(menu.getWidth(), menu.getHeight());
        menuTiles = new TETile[menu.getWidth()][menu.getHeight()];
        fillMenuTiles();
        renderMT.renderFrame(menuTiles);
        menu.drawMenu();
    }

    //fill menu background
    private void fillMenuTiles() {
        for (Coordinate pos : menu.getBlackScreenCoordinates()) {
            menuTiles[pos.x][pos.y] = Tileset.NOTHING;
        }
    }
}
