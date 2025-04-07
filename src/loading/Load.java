package loading;

import core.World;
import game.WorldState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Load {
    private final String filename = "gameSave.txt";
    //variables necessary for world generation
    private int worldWidth;
    private int worldHeight;
    private int seed;
    private double density;
    private int playerPositionX;
    private int playerPositionY;

    public WorldState loadGame() {
        loadGameState();//read from file and assign variables

        //create world and update player position
        WorldState world = new WorldState();
        world.generateWorld(worldHeight, worldWidth, seed, density);
        world.updatePositin(playerPositionX, playerPositionY);

        return world;
    }

    //assign variables by converting array of string to right data type
    private void loadGameState() {
        String[] data = readData();
        if (data.length == 6) {
            worldWidth = Integer.parseInt(data[0]);
            worldHeight = Integer.parseInt(data[1]);
            seed = Integer.parseInt(data[2]);
            density = Double.parseDouble(data[3]);
            playerPositionX = Integer.parseInt(data[4]);
            playerPositionY = Integer.parseInt(data[5]);
        }
        else {
            throw new RuntimeException("format of save file is wrong");
        }
    }

    //read file and split string in array of stings
    private String[] readData() {
        String content = "";
        try {
            content = Files.readString(Path.of(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.split(",");

    }
}
