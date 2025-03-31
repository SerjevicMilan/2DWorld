import core.Coordinate;
import core.HallwaysGenerator;
import core.Room;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class HallwaysGeneratorTest {

    @Test
    public void BasicHallwaysGeneratorTest() {
        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room(new Coordinate(5, 5), 2, 2));
        rooms.add(new Room(new Coordinate(25, 15), 2, 2));
        rooms.add(new Room(new Coordinate(10, 10), 2, 2));
        rooms.add(new Room(new Coordinate(20, 35), 2, 2));


    }
}
