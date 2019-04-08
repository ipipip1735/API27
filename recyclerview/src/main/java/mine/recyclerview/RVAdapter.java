package mine.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Administrator on 2019/3/26.
 */
public class RVAdapter<T> extends RecyclerView.Adapter {
    private List<T> dataset;
    private List<TextView> target;

    public RVAdapter(List<T> dataset, List<TextView> v) {
        this.dataset = dataset;
        this.target = v;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        System.out.println("~~onCreateViewHolder~~");
        System.out.print("viewType is " + viewType);
        System.out.println("|parent is " + parent);

        TextView v = new TextView(parent.getContext());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("..onClick target..");
                RecyclerView recyclerView = (RecyclerView) parent;
                System.out.println("getChildLayoutPosition is " + recyclerView.getChildLayoutPosition(v));
                System.out.println("getChildAdapterPosition is " + recyclerView.getChildAdapterPosition(v));
                System.out.println("getChildItemId is " + recyclerView.getChildItemId(v));
                System.out.println(((TextView) v).getText() + "|" + recyclerView.getChildViewHolder(v));
                if (RVAdapter.this.target != null) {
                    RVAdapter.this.target.add((TextView) v);
                } else {
                    System.out.println("target is null");
                }
            }
        });

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("~~onBindViewHolder~~");
        System.out.println(holder.itemView + "|" + holder);

        String s = dataset.get(position).toString();
        System.out.println(s);
        ((MyViewHolder) holder).textView.setText(s);

        System.out.println(((TextView)holder.itemView).getText() + "|" + holder);
    }

    @Override
    public int getItemCount() {
//        System.out.println("~~getItemCount~~");
//        System.out.println("getItemCount is " + dataset.size());
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


    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        System.out.println("~~onViewRecycled~~");
        System.out.println(((TextView)holder.itemView).getText() + "|" + holder);

    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        System.out.println("~~onFailedToRecycleView~~");
        System.out.println(((TextView)holder.itemView).getText() + "|" + holder);

        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        System.out.println("~~onViewAttachedToWindow~~");
        System.out.println(((TextView)holder.itemView).getText() + "|" + holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        System.out.println("~~onViewDetachedFromWindow~~");
        System.out.println(((TextView)holder.itemView).getText() + "|" + holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        System.out.println("~~onAttachedToRecyclerView~~");
        iterate(recyclerView);

    }


    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        System.out.println("~~onDetachedFromRecyclerView~~");
        iterate(recyclerView);
    }


    /*-------------------------------------*/
    private void iterate(@NonNull RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            TextView textView = (TextView) recyclerView.getChildAt(i);
            System.out.print(textView.getText()+"|");
        }
        System.out.println("");
    }



}