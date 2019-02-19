package mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Random;

public class PersonArrayAdapter extends ArrayAdapter {

    private String[] nameArray = new String[]{
            "bob",
            "jack",
            "mack",
            "mike",
            "jenifer",
            "gerrard",
            "sam",
            "mack",
            "anna"
    };

    public PersonArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        System.out.println("--- " + getClass().getSimpleName() + ".constructor ---");

//        addAll(nameArray);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        System.out.println("--- " + getClass().getSimpleName() + ".getView ---");
        System.out.println("position is " + position);
        System.out.println("convertView is " + convertView);
        System.out.println("parent is " + parent);


        TextView textView = new TextView(getContext());
        textView.setText((CharSequence) getItem(position));


        return textView;
//        return super.getView(position, convertView, parent);

    }

}
