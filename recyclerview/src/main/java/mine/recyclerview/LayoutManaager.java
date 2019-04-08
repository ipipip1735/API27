package mine.recyclerview;

/**
 * Created by Administrator on 2019/4/8.
 */
import android.support.v7.widget.RecyclerView;

public class LayoutManaager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        System.out.println("~~generateDefaultLayoutParams~~");
        return null;
    }
}
