package seed;

import core.Coordinate;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

/*game starting menu, it offers 3 options new game, load and quit
fills coordinate list representing Black tiles and Draw a menu
using princeton StdDraw library
 */
public class Seed {
    private int width;
    private int height;
    List<Coordinate> BlackScreen = new ArrayList<>();

    int seed = 0;
    String seedString = "";

    public Seed (int width, int height) {
        this.width = width;
        this.height = height;
        generateBlackScreen();
    }

    public void changeNumber(char number) {
        seed = seed * 10 + (number - '0');
        seedString += number;
    }

    public int getSeedInt () {
        return seed;
    }

    public String getSeedStr () {
        return seedString;
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
        //set white color
        StdDraw.setPenColor(255, 255, 255);
        //center text and draw text
        StdDraw.text((int)(width / 2), (int)(height  / 2) + 5, "Enter Number");
        StdDraw.text((int)(width / 2), (int)(height  / 2), seedString);
        StdDraw.text((int)(width / 2), (int)(height  / 2) - 5 , "(S) Start Game");
        StdDraw.show();
        //wait for 1 frame per second
        StdDraw.pause(1000);
    }

}
