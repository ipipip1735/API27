package mine.databinding;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mine.databinding.data.SheepObservable;

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
    public void copy() {
        List<SheepObservable> sheep = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            sheep.add(new SheepObservable("Tom - " + i));
        }

        ArrayList<SheepObservable> arrayList = new ArrayList<>(sheep.size());
        arrayList.addAll(sheep);

    }
}