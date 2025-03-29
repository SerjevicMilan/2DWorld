package core;

import tileengine.TETile;

import java.util.List;

//Create hallway path based on start and end position and state of worldTiles
public class Hallway {
    private Coordinate from;
    private Coordinate to;
    private List<Coordinate> hallwayPath;

    //during construction add from and to coordinates
    public Hallway(Coordinate start, Coordinate end) {
        from = start;
        to = end;
    }

    //Takes from and to coordinates in worldTiles
    // and generates list of coordinates representing path.
    public List<Coordinate> generatePath(TETile[][] worldTiles) {
        return null;
    }

    //returns array of coordinates representing path
    public List<Coordinate> getHallwayPath() {
        return hallwayPath;
    }
}
