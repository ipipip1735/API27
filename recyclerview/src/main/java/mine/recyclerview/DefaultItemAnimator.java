package mine.recyclerview;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/2.
 */
public class DefaultItemAnimator extends android.support.v7.widget.DefaultItemAnimator {
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        System.out.println("->>animateRemove<<-");
        return super.animateRemove(holder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        System.out.println("->>animateAdd<<-");
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        System.out.println("->>animateMove<<-");
        return super.animateMove(holder, fromX, fromY, toX, toY);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        System.out.println("->>animateChange<<-");
        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        super.endAnimation(item);
    }

    @Override
    public boolean isRunning() {
        System.out.println("->>isRunning<<-");
        return super.isRunning();
    }

    @Override
    public void endAnimations() {
        super.endAnimations();
    }

    @Override
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        System.out.println("->>canReuseUpdatedViewHolder<<-");
        return super.canReuseUpdatedViewHolder(viewHolder, payloads);
    }
}