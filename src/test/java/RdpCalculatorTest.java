import org.junit.Test;
import recursivedescent.RdpCalculator;

import static org.junit.Assert.assertEquals;

/**
 * Created by ram on 10/7/17.
 */
public class RdpCalculatorTest {

    @Test
    public void Rdp_calc_Test() throws Exception{
        try {
            assertEquals("22.0", RdpCalculator.evaluate("4*5+2"));
            assertEquals("4.0", RdpCalculator.evaluate(" 2 * sqrt(4)"));
            assertEquals("1137.0", RdpCalculator.evaluate("32 * 4 + 1 + 6 * 34 * 8 - 12 * 52"));
            assertEquals("404.0", RdpCalculator.evaluate("304 / 16 + 11 * (16 + 19)"));
            assertEquals("1764.0", RdpCalculator.evaluate("32 * 4 / (2 + 6) * 113 - 8 * 12 + 52"));
            assertEquals("42.0",RdpCalculator.evaluate("sqrt(32 * 4 / (2 + 6) * 113 - 8 * 12 + 52)"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
