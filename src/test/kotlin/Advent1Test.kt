import org.junit.Test
import java.io.File
import org.hamcrest.core.Is.*
import org.junit.Assert.*

public class Advent1Test {
    @Test fun pass () {}

    @Test fun testSimpleCaptchas () {
        assertThat(proveCaptcha("1122"), `is` ( 3 ))
        assertThat(proveCaptcha("1111"), `is` ( 4 ))
        assertThat(proveCaptcha("1234"), `is` ( 0 ))
        assertThat(proveCaptcha("91212129"), `is` ( 9 ))
    }

    @Test fun testAdvancedCaptchas () {
        assertThat(proveCaptcha2("1212"), `is` ( 6 ))
        assertThat(proveCaptcha2("1221"), `is` ( 0 ))
        assertThat(proveCaptcha2("123425"), `is` ( 4 ))
        assertThat(proveCaptcha2("123123"), `is` ( 12 ))
        assertThat(proveCaptcha2("12131415"), `is` ( 4 ))
    }

}


