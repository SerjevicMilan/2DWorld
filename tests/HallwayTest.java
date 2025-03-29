import core.Coordinate;
import core.Hallway;
import core.Room;
import org.junit.jupiter.api.Test;
import tileengine.TETile;

import java.util.Random;

public class HallwayTest {
    @Test
    public void generatePathTest() {
        Coordinate pos1 = new Coordinate(5, 5);
        Coordinate pos2 = new Coordinate(15,15);

        Room r1 = new Room(pos1, 3, 3);
        Room r2 = new Room(pos2, 4, 4);

        Hallway hw = new Hallway(r1.getCenter(), r2.getCenter());



    }
}
