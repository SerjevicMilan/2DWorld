import org.junit.jupiter.api.Test;
import seed.Seed;

import static com.google.common.truth.Truth.assertThat;

public class SeedTest {
    @Test
    public void seedBasicTest() {
        Seed seed =  new Seed(80, 50);

        seed.changeNumber('1');
        seed.changeNumber('2');
        seed.changeNumber('3');
        seed.changeNumber('4');

        assertThat(seed.getSeedInt()).isEqualTo(1234);
        assertThat(seed.getSeedStr()).isEqualTo("1234");
    }
}
