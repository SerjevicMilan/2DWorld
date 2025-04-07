package seed;

import utils.Coordinate;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

/*game starting menu, it offers 3 options new game, load and quit
fills coordinate list representing Black tiles and Draw a menu
using princeton StdDraw library
 */
public class Seed {
    //seed menu dimensions
    private int width;
    private int height;

    //coordinates for black tiles
    List<Coordinate> BlackScreen = new ArrayList<>();

    //seed used for random generation
    int seed = 0;
    String seedString = "";
    //used for drawing number on screen

    //initilase seed and generate coordinates for screen rendering
    public Seed (int width, int height) {
        this.width = width;
        this.height = height;
        generateBlackScreen();
    }

    //update seed int by multiplying current seed by base 10 and adding number
    //extend seed string
    public void changeNumber(char number) {
        seed = seed * 10 + (number - '0');
        seedString += number;
    }

    //get int version of seed
    public int getSeedInt () {
        return seed;
    }

    //get string version of seed
    public String getSeedStr () {
        return seedString;
    }

    //returns coordinates for rendering Black tiles
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

    //use StdDraw to add text to screen
    public void drawSeed() {
        //set white color
        StdDraw.setPenColor(255, 255, 255);
        //center text and draw text
        StdDraw.text((int)(width / 2), (int)(height / 2)  + 5, "Enter Number");
        StdDraw.text((int)(width / 2), (int)(height / 2), seedString);
        StdDraw.text((int)(width / 2), (int)(height/ 2) - 5, "(S) Start Game");
    }

}
