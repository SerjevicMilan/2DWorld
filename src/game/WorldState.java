package game;

import core.Coordinate;
import core.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Manages player and coin positions, detects collisions, and checks level completion.
Triggers a flag to generate a new level when all coins are collected.
 */
public class WorldState {
    //world-class and Random number generator
    World world;
    Random randomGenerator;

    //All coordinates representing wall and floor tiles
    List<Coordinate> wallCordinates = new ArrayList<>();
    List<Coordinate> floorCordinates = new ArrayList<>();

    //World dimensions
    public int height = 0;
    public int width = 0;

    //coordinates of player in world
    Coordinate playerPosition;

    //coordinates of all coins
    List<Coordinate> coins;
}
