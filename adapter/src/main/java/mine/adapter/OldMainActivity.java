package mine.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OldMainActivity extends AppCompatActivity {


    private String[] nameArray = new String[]{
            "bob",
            "jack",
            "mack",
            "anna"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldmain);


    }


    @Override
    protected void onStart() {
        super.onStart();

        TextView textView = (TextView) findViewById(R.id.textView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.nameArray);
//        ListView listView = (ListView) findViewById(R.id.myListView);
//        listView.setAdapter(arrayAdapter);
        TextView textView1 = (TextView) arrayAdapter.getView(2,textView , null);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.vg);
        viewGroup.addView(textView1);

    }

}
