import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Ram on 7/10/17.
 */

public class ArithmeticEvaluatorTest {

    @Test
    public void calc_Test() throws Exception{
        try {
            assertEquals("22.0", ArithmeticEvaluator.evaluate("4*5+2"));
            assertEquals("4.0", ArithmeticEvaluator.evaluate(" 2 * sqrt(4)"));
            assertEquals("1137.0", ArithmeticEvaluator.evaluate("32 * 4 + 1 + 6 * 34 * 8 - 12 * 52"));
            assertEquals("404.0", ArithmeticEvaluator.evaluate("304 / 16 + 11 * (16 + 19)"));
            assertEquals("1764.0", ArithmeticEvaluator.evaluate("32 * 4 / (2 + 6) * 113 - 8 * 12 + 52"));
            assertEquals("42.0",ArithmeticEvaluator.evaluate("sqrt(32 * 4 / (2 + 6) * 113 - 8 * 12 + 52)"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}