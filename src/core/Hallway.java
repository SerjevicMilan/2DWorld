package core;

import tileengine.TETile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.RandomUtils.uniform;

//Create hallway path based on start and end position and state of worldTiles
public class Hallway {
    private Coordinate from;
    private Coordinate to;
    private List<Coordinate> hallwayPath = new ArrayList<>();

    //during construction add from and to coordinates
    public Hallway(Coordinate start, Coordinate end) {
        from = start;
        to = end;
    }

    public Hallway(Coordinate start, Coordinate end, Random random) {
        from = start;
        to = end;
        if (uniform(random, 2) == 1) {
            generateZigZagPath();
        }
        else {
            generateLShapePath();
        }
    }

    //Takes from and to coordinates in worldTiles
    // and generates list of coordinates representing path.
    public List<Coordinate> generateZigZagPath() {
        int x = from.x;//starting x pos
        int y = from.y;//starting y pos
        int turn = 1;//for swapping axis incrementation

        while (x != to.x || y != to.y) {//reached end position
            hallwayPath.add(new Coordinate(x, y));
            if (turn == 1) {//increment either x or y
                x = moveOne(x, to.x);
                turn = 2;
            }
            else {
                y = moveOne(y, to.y);
                turn = 1;
            }
        }
        hallwayPath.add(new Coordinate(x, y));

        return hallwayPath;
    }

    //generate L shape path by traversing one y-axis first and then x-axis
    public List<Coordinate> generateLShapePath() {
        for (int y = from.y; y <= to.y; y++) {//traverse x-axis
            hallwayPath.add(new Coordinate(from.x, y));
        }

        for (int x = from.x + 1; x <= to.x; x++) {//traverse y-axis
            hallwayPath.add(new Coordinate(x, to.y));
        }
        return hallwayPath;
    }

    //returns array of coordinates representing path
    public List<Coordinate> getHallwayPath() {
        return hallwayPath;
    }

    //increment or decrement (depending on start and end positions)
    private int moveOne(int start, int end) {
        int i = 1;
        if (start > end) { i *= -1; }//if end pos less then start pos then decrement
        if ( start != end) { start += i; }
        return start;
    }
}
