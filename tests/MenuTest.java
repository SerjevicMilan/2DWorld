import menu.Menu;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class MenuTest {
    @Test
    public void menuBasicTest() {
        Menu menu = new Menu(80, 50);

        assertThat(menu.getArea()).isEqualTo(4000);
        assertThat(menu.getBlackScreenCoordinates().size()).isEqualTo(4000);
    }
}
