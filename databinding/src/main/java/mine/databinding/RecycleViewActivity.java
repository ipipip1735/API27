package mine.databinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mine.databinding.data.SheepObservable;
import mine.databinding.databinding.ActivityRecycleBinding;
import mine.databinding.databinding.RecycleItemViewBinding;


/**
 * Created by Administrator on 2022/6/30.
 */
public class RecycleViewActivity extends AppCompatActivity {
    RVA adapter;
    SheepListAdapter sheepListAdapter;
    SheepAdapterWithAsyncListDiffer sheepAdapterWithAsyncListDiffer;
    ActivityRecycleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        setContentView(R.layout.activity_recycle);


        //方法一
//        List<SheepObservable> sheeps = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            sheeps.add(new SheepObservable("Tom - " + i));
//        }
//        adapter =  new RVA(sheeps);
//
//        setContentView(R.layout.activity_recycle);
//        this.<RecyclerView>findViewById(R.id.rv).setAdapter(adapter);


        //方法二
//        List<SheepObservable> sheeps = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            sheeps.add(new SheepObservable("Tom - " + i));
//        }
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycle);
//        binding.setLifecycleOwner(this);
//        binding.setVm(new ViewModelProvider(this).get(TheViewModel.class));
//        binding.getVm().setSheeps(sheeps);
//
//        adapter =  new RVA(binding.getVm().getSheeps());
//        this.<RecyclerView>findViewById(R.id.rv).setAdapter(adapter);


        //方法三
        List<SheepObservable> sheeps = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            sheeps.add(new SheepObservable("Tom - " + i));
        }
        sheepListAdapter =  new SheepListAdapter();
        this.<RecyclerView>findViewById(R.id.rv).setAdapter(sheepListAdapter);
        sheepListAdapter.submitList(sheeps);


        //方法四
//        List<SheepObservable> sheeps = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            sheeps.add(new SheepObservable("Tom - " + i));
//        }
//        sheepAdapterWithAsyncListDiffer =new SheepAdapterWithAsyncListDiffer(sheeps);
//        setContentView(R.layout.activity_recycle);
//        this.<RecyclerView>findViewById(R.id.rv).setAdapter(sheepAdapterWithAsyncListDiffer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }

    public void start(View view) {
        System.out.println("~~button.start~~");

//        for (SheepObservable sheep : adapter.list) {
//            String[] name = sheep.getName().split(" - ");
//            sheep.setName(name[1] + " - " + name[0]);
//        }
////        adapter.notifyDataSetChanged(); //不需要更新适配器，数据绑定对象会自动更新UI
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

        for (SheepObservable sheep : binding.getVm().getSheeps()) {
            String[] name = sheep.getName().split(" - ");
            sheep.setName(name[1] + " - " + name[0]);
        }

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

//        for (SheepObservable sheep : listAdapter.getCurrentList()) {
//            String[] name = sheep.getName().split(" - ");
//            sheep.setName(name[1] + " - " + name[0]);
//        }

        //使用ListAdapter
        ArrayList<SheepObservable> list = new ArrayList<>(sheepListAdapter.getCurrentList());
        list.remove(0); // CURD
        list.add(2, new SheepObservable("XX"));
        sheepListAdapter.submitList(list);


        //使用AsyncListDiffer
//        sheepAdapterWithAsyncListDiffer.add(new SheepObservable("Kate"));

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");
        sheepAdapterWithAsyncListDiffer.remove(0);
    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");
    }

    public void del(View view) {
        System.out.println("~~button.del~~");
    }

    public void query(View view) {
        System.out.println("~~button.query~~");
    }


    /**
     * 使用数据绑定
     */
    static class RVA extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<SheepObservable> list;

        public RVA(List<SheepObservable> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            System.out.println("~~" + getClass().getSimpleName() + ".onCreateViewHolder~~");
            System.out.println("parent = " + parent + ", viewType = " + viewType);
            RecycleItemViewBinding binding = RecycleItemViewBinding.inflate(LayoutInflater.from(parent.getContext()));
            binding.setLifecycleOwner((LifecycleOwner) parent.getContext());
            return new RecyclerView.ViewHolder(binding.getRoot()) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            System.out.println("~~" + getClass().getSimpleName() + ".onBindViewHolder~~");
            RecycleItemViewBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setSheep(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    //===========================================================//

    /**
     * 区别计算
     */
    static class ItemCallback extends DiffUtil.ItemCallback<SheepObservable> {

        @Override
        public boolean areItemsTheSame(@NonNull SheepObservable oldItem, @NonNull SheepObservable newItem) {
            System.out.println("~~" + getClass().getSimpleName() + ".areItemsTheSame~~");
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SheepObservable oldItem, @NonNull SheepObservable newItem) {
            System.out.println("~~" + getClass().getSimpleName() + ".areContentsTheSame~~");
            return oldItem.getName().equals(newItem.getName());
        }

        @Nullable
        @Override
        public Object getChangePayload(@NonNull SheepObservable oldItem, @NonNull SheepObservable newItem) {
            System.out.println("~~" + getClass().getSimpleName() + ".getChangePayload~~");
//            return super.getChangePayload(oldItem, newItem);

            System.out.println("oldItem = " + oldItem + ", newItem = " + newItem);
            return "xxxx";
        }
    }

    /**
     * 使用ListAdapter
     */
    static class SheepListAdapter extends ListAdapter<SheepObservable, RecyclerView.ViewHolder> {

        protected SheepListAdapter() {
            super(new ItemCallback());
            System.out.println("~~SheepListAdapter~~");
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            System.out.println("~~" + getClass().getSimpleName() + ".onCreateViewHolder~~");
            RecycleItemViewBinding binding = RecycleItemViewBinding.inflate(LayoutInflater.from(parent.getContext()));
            binding.setLifecycleOwner((LifecycleOwner) parent.getContext());
            return new RecyclerView.ViewHolder(binding.getRoot()) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            System.out.println("~~" + getClass().getSimpleName() + ".onBindViewHolder~~");
            RecycleItemViewBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setSheep(getItem(position));

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
            System.out.println("~~" + getClass().getSimpleName() + ".onBindViewHolder~~");
            System.out.println("holder = " + holder + ", position = " + position + ", payloads = " + payloads);
//            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public void onCurrentListChanged(@NonNull List<SheepObservable> previousList, @NonNull List<SheepObservable> currentList) {
            System.out.println("~~" + getClass().getSimpleName() + ".onCurrentListChanged~~");
            System.out.println("previousList = " + previousList + ", currentList = " + currentList);
            super.onCurrentListChanged(previousList, currentList);
        }
    }


    static class SheepAdapterWithAsyncListDiffer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        AsyncListDiffer<SheepObservable> mDiffer = new AsyncListDiffer<>(this, new ItemCallback());
        List<SheepObservable> sheeps;

        public SheepAdapterWithAsyncListDiffer(List<SheepObservable> sheeps) {
            this.sheeps = sheeps;
            mDiffer.submitList(sheeps);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecycleItemViewBinding binding = RecycleItemViewBinding.inflate(LayoutInflater.from(parent.getContext()));
            binding.setLifecycleOwner((LifecycleOwner) parent.getContext());
            return new RecyclerView.ViewHolder(binding.getRoot()) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            System.out.println("~~" + getClass().getSimpleName() + ".onBindViewHolder~~");
            RecycleItemViewBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setSheep(mDiffer.getCurrentList().get(position));
        }

        @Override
        public int getItemCount() {
            return mDiffer.getCurrentList().size();
        }

        public void submitList(List<SheepObservable> list) {
            mDiffer.submitList(list);
        }

        public void add(SheepObservable sheep) {
            sheeps.add(sheep);
            mDiffer.submitList(sheeps);
            notifyDataSetChanged();
        }

        public void remove(int index) {
            sheeps.remove(index);
            mDiffer.submitList(sheeps);
            notifyDataSetChanged();
        }
    }
}