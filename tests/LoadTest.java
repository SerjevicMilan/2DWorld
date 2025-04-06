import core.World;
import loading.Load;
import loading.Save;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LoadTest {
    @Test
    public void basicLoadTest() {
        World world = new World(50, 80, 1, 1.0);
        Save save = new Save();
        save.saveGame(world);

        Load load = new Load();
        World loadWorld = load.loadGame();

        assertThat(loadWorld.getPlayerPosition()).isEqualTo(world.getPlayerPosition());
        assertThat(loadWorld.getSeed()).isEqualTo(world.getSeed());
    }
}
