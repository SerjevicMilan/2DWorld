package menu;

import core.Coordinate;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

/*game starting menu, it offers 3 options new game, load and quit
fills coordinate list representing Black tiles and Draw a menu
using princeton StdDraw library
 */
public class Menu {
    private int width;
    private int height;
    List<Coordinate> BlackScreen = new ArrayList<>();

    public Menu (int width, int height) {
        this.width = width;
        this.height = height;
        generateBlackScreen();
    }

    public List<Coordinate> getBlackScreenCoordinates() {
        return BlackScreen;
    }

    //get Area for testing
    public int getArea() {
        return width * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight () {
        return height;
    }

    //creates coordinate for 2d array of width and height dimensions
    private void generateBlackScreen() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                BlackScreen.add(new Coordinate(x, y));
            }
        }
    }

    public void drawMenu() {
        StdDraw.setPenColor(255, 255, 255);
        StdDraw.text((int)(width / 2) - 10, (int)(height  / 2) - 5, "(N) New Game");
        StdDraw.text((int)(width / 2) - 10, (int)(height  / 2), "(L) Load Game");
        StdDraw.text((int)(width / 2) - 10, (int)(height  / 2) + 5 , "(Q) Quit Game");
    }

}
