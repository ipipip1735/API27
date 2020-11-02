package mine.databinding;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import com.google.android.material.textview.MaterialTextView;

/**
 * Created by Administrator on 2020/11/1.
 */
@BindingMethods({
        @BindingMethod(type = MaterialTextView.class,
                attribute = "android:text",
                method = "one"),
})
public class TheTextView extends MaterialTextView {
    public TheTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("---TheTextView.Constructor---");
    }

    public void one(String name){
        System.out.println("~~" + getClass().getSimpleName() + ".one~~");
        System.out.println("name is " + name);
        setText(name);
    }

}
