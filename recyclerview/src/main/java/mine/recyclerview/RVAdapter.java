package mine.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2019/3/26.
 */
public class RVAdapter<T> extends RecyclerView.Adapter {
    private List<T> dataset;

    public RVAdapter(List<T> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
//        System.out.println("~~onCreateViewHolder~~");
//        System.out.println("parent is " + parent);
//        System.out.println("viewType is " + viewType);

        TextView v = new TextView(parent.getContext());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("..onClick v..");
                RecyclerView recyclerView = (RecyclerView) parent;
                System.out.println("getChildLayoutPosition is " + recyclerView.getChildLayoutPosition(v));
                System.out.println("getChildAdapterPosition is " + recyclerView.getChildAdapterPosition(v));
                System.out.println("getChildItemId is " + recyclerView.getChildItemId(v));
            }
        });

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("~~onBindViewHolder~~");
        System.out.println("holder is " + holder);
        System.out.println("position is " + position);

        String s = dataset.get(position).toString();
        System.out.println(s);
        ((MyViewHolder)holder).textView.setText(s);
    }

    @Override
    public int getItemCount() {
        System.out.println("~~getItemCount~~");
        System.out.println("getItemCount is " + dataset.size());
        return dataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}