package mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        System.out.println("--- " + getClass().getSimpleName() + ".getView ---");

//        TextView textView = new TextView(this);


//        return

        return super.getView(position, convertView, parent);
    }
}
