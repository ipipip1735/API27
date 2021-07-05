package mine.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        println("~~onCreateViewHolder~~");
        System.out.print("viewType is " + viewType);
        println("|parent is " + parent);

        TextView v = new TextView(parent.getContext());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                println("..onClick target..");
                RecyclerView recyclerView = (RecyclerView) parent;
                println("getChildLayoutPosition is " + recyclerView.getChildLayoutPosition(v));
                println("getChildAdapterPosition is " + recyclerView.getChildAdapterPosition(v));
                println("getChildItemId is " + recyclerView.getChildItemId(v));
                println(((TextView) v).getText() + "|" + recyclerView.getChildViewHolder(v));
                if (RVAdapter.this.target != null) {
                    RVAdapter.this.target.add((TextView) v);
                } else {
                    println("target is null");
                }
            }
        });

        return new RecyclerView.ViewHolder(v){};
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        println("~~onBindViewHolder~~");
        println(holder.itemView + "|" + holder);

        String s = dataset.get(position).toString();
        println(s);
        ((TextView)holder.itemView).setText(s);

        println(((TextView)holder.itemView).getText() + "|" + holder);
    }

    @Override
    public int getItemCount() {
//        println("~~getItemCount~~");
//        println("getItemCount is " + dataset.size());
        return dataset.size();
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        println("~~onViewRecycled~~");
        println(((TextView)holder.itemView).getText() + "|" + holder);

    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        println("~~onFailedToRecycleView~~");
        println(((TextView)holder.itemView).getText() + "|" + holder);

        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        println("~~onViewAttachedToWindow~~");
        println(((TextView)holder.itemView).getText() + "|" + holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        println("~~onViewDetachedFromWindow~~");
        println(((TextView)holder.itemView).getText() + "|" + holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        println("~~onAttachedToRecyclerView~~");
        iterate(recyclerView);

    }


    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        println("~~onDetachedFromRecyclerView~~");
        iterate(recyclerView);
    }


    /*-------------------------------------*/
    private void iterate(@NonNull RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            TextView textView = (TextView) recyclerView.getChildAt(i);
            System.out.print(textView.getText()+"|");
        }
        println("");
    }

    private void println(String content){
        Log.i("RVA", content);
    }

}