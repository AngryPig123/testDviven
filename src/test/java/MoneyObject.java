import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : TestDrivenCLass
 * author         : AngryPig123
 * date           : 26. 1. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 18.        AngryPig123       최초 생성
 */
public class TestDrivenClass {

    @Test
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        five.times(2);
        Assertions.assertEquals(10, five.mount);
    }

}
