package mine.media;

import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void division() {
        long a = 5L;
        long b=7L;
        float f  = (float)a/b;

        System.out.println(f);
        System.out.printf("%f\n", f);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        System.out.println(df.format(f));

    }
}