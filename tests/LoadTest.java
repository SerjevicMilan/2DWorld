import core.World;
import game.WorldState;
import loading.Load;
import loading.Save;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LoadTest {
    @Test
    public void basicLoadTest() {
        WorldState world = new WorldState();
        world.generateWorld(50, 80, 1, 1.0);
        Save save = new Save();
        save.saveGame(world);

        Load load = new Load();
        WorldState loadWorld = load.loadGame();

        assertThat(loadWorld.getPlayer()).isEqualTo(world.getPlayer());
        assertThat(loadWorld.getSeed()).isEqualTo(world.getSeed());
    }
}
