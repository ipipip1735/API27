package mine.databinding.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;

import com.google.android.material.textview.MaterialTextView;

import mine.databinding.R;

/**
 * Created by Administrator on 2020/11/1.
 */
@BindingMethods({
        @BindingMethod(type = TheTextView.class, attribute = "android:text", method = "one"),
        @BindingMethod(type = TheTextView.class, attribute = "age", method = "two"),
        @BindingMethod(type = TheTextView.class, attribute = "resource_id", method = "three"),
})
public class TheTextView extends MaterialTextView {
    public int age = 0;
    public InverseBindingListener ageAttrChanged;

    public TheTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("---TheTextView.Constructor---");
    }

    public int getAge() {
        System.out.println("~~TheTextView.getAge~~");
        return age;
    }

    public void two(int age) {
        System.out.println("~~" + getClass().getSimpleName() + ".two~~");
        System.out.println("age = " + age);
        this.age = age;
        ageAttrChanged.onChange();
    }

//    public void setAge(int age) {
//        System.out.println("~~TheTextView.setAge~~");
//        this.age = age;
////        ageAttrChanged.onChange();
//    }

    public void one(String name) {
        System.out.println("~~" + getClass().getSimpleName() + ".one~~");
        System.out.println("name is " + name);
        setText(name);
    }



    public void three(int resourceId) {
        System.out.println("~~" + getClass().getSimpleName() + ".three~~");
        System.out.println("resourceId = " + resourceId);
        if (resourceId == 0) return;

        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background, null);
        drawable.setBounds(0, 0, 40, 40);
        setCompoundDrawables(drawable, null, null, null);

    }
}



