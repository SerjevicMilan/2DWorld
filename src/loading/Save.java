package loading;

import core.World;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    private final String filename = "gameSave.txt";

    //variables needed for world construction
    private String worldWidth;
    private String worldHeight;
    private String seed;
    private String density;
    private String playerPositionX;
    private String playerPositionY;

    //convert data and assign it to variables and write it to file
    public void saveGame(World world) {
        storeData(world);
        writeGameState();
    }

    //convert all data necessary to string and assign to variable
    private void storeData(World world) {
        worldWidth = String.valueOf(world.getWidth());
        worldHeight = String.valueOf(world.getHeight());
        this.seed = String.valueOf(world.getSeed());
        this.density = Double.toString(world.getDensity());
        playerPositionX = String.valueOf( world.getPlayerPosition().x);
        playerPositionY = String.valueOf( world.getPlayerPosition().y);
    }

    //write data separated by comma. Example (80,50,34,1.0,10,12)
    private void writeGameState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {//create new writer
            writer.write(worldWidth + ",");
            writer.write(worldHeight + ",");
            writer.write(seed + ",");
            writer.write(density + ",");
            writer.write(playerPositionX + ",");
            writer.write(playerPositionY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
