import core.Coordinate;
import org.junit.jupiter.api.Test;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.truth.Truth.assertThat;

public class PlayerTest {
    @Test
    public void playerBasicTest() {
        List<Coordinate> validCordinates = new ArrayList<>();

        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 50; y++) {
                validCordinates.add(new Coordinate(x,y));
            }
        }

        Player player = new Player(80, 50, new Random(1), validCordinates);

        Random randomNumberGenerator = new Random(1);
        int expectedX = randomNumberGenerator.nextInt(0, 80);
        int expectedY = randomNumberGenerator.nextInt(0, 50);
        Coordinate expectedPosition = new Coordinate(expectedX, expectedY);

        assertThat(player.spawnPlayer()).isEqualTo(expectedPosition);
    }
}
