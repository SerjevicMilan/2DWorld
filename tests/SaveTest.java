import game.WorldState;
import loading.Save;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.truth.Truth.assertThat;

public class SaveTest {
    @Test
    public void saveBasicTest() {
        //create world and save seed and player coordinates to file
        WorldState world = new WorldState();
        world.generateWorld(50, 80, 1, 1.0);
        Save SL = new Save();
        SL.saveGame(world);

        String actual = "";

        String expected = String.valueOf(world.getWidth()) + ","
                + String.valueOf(world.getHeight()) + ","
                + String.valueOf(world.getSeed()) + ","
                + Double.toString(world.getDensity()) + ","
                + String.valueOf(world.getPlayer().x) + ","
                + String.valueOf(world.getPlayer().y);
        try {
            actual = Files.readString(Path.of("gameSave.txt"));//read from file
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(actual).isEqualTo(expected);
    }
}
