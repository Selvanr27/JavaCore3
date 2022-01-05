import div.DividingTwoNum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DividingTwoNumTests {
    @DisplayName("Test for complete number")
    @Test
    public void testCompleteNumbers(){
        var d=new DividingTwoNum();
var num1=10;
var num2=10;
        var expected=1;
        var actual =d.divideNumbers(num1,num2);

        Assertions.assertEquals(expected,actual);
    }
    @DisplayName("test for precision point numbers")
    @Test
    void testPrecisionNumbers() {
        var d = new DividingTwoNum();
        var num1 = 4.2;
        var num2 = 2.2;
        var expected = 1.9090;
        var actual = d.divideNumbers(num1, num2);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Test for Unexpected Numbers")
    @Test
    void testUnexpectedNumbers() {
        var num1 = 4.5;
        var num2 = 0;

        var d = new DividingTwoNum();
        var expected = 0;
        var actual = d.divideNumbers(num1, num2);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Testing Exceptions")
    @Test
    void testUnexpectedNumbersWithException() {
        var num1 = 4.5;
        var num2 = 0;

        Assertions.assertThrows(RuntimeException.class, () -> {
            var d = new DividingTwoNum();
            d.divideNumbersException(num1, num2);
        });
    }

}
