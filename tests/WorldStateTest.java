import core.World;
import game.WorldState;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class WorldStateTest {
    @Test
    public void worldStateBasicTest() {
        World world = new World(50, 80, 1, 1.0);
        WorldState ws = new WorldState();
        ws.generateWorld(50, 80, 1, 1.0);

        assertThat(world.getAllFloors()).isEqualTo(ws.getFloor());
    }
}
