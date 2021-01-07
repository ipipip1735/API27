package mine.viewmodel;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

/**
 * Created by Administrator on 2018/9/14.
 */
public class LiveDataActivity extends AppCompatActivity {

    MutableLiveData<Car> carLiveData;
    LiveData<User> userLiveData;

    MutableLiveData<Integer> userIdLiveData = new MutableLiveData<>();
    Repository repository = new Repository();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_client);

        if (carLiveData == null) {
            System.out.println("--carLiveData initial--");
            carLiveData = CarLiveData.get();
            carLiveData.observe(this, car -> {
                System.out.println("observer car|" + car);
            });
        }

//        userLiveData.setValue(new User("tom", "lee"));



        //方法一
//        userLiveData = LiveDataActivity.map(carLiveData, car->{
//            System.out.println("~~Transformations~~");
//            System.out.println(car);
//            return new User("bob", "lee");
//        });
//        userLiveData.observe(this, user -> {
//            System.out.println("observer user|" + user);
//        });


        //方法二
//        userLiveData = LiveDataActivity.switchMap(userIdLiveData, userId -> repository.getUser(userId));
        userLiveData = Transformations.switchMap(userIdLiveData, userId -> repository.getUser(userId));
        userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                System.out.println("userLiveData|" + user);
            }
        });

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

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
////                carLiveData = new MutableLiveData<Car>();
////                carLiveData.observe(LiveDataActivity.this, car -> {
////                    System.out.println("observer1 car|" + car);
////                });
//                carLiveData.postValue(new Car("VOLVO"));
////                userLiveData.postValue(new User("tom", "lee"));
//            }
//        }).start();

        carLiveData.setValue(new Car("VOLVO"));
//        System.out.println(userLiveData);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        System.out.println("carLiveData is " + carLiveData.getValue());
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");
        System.out.println("carLiveData is " + userLiveData.getValue());

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");
        int n = new Random().nextInt(3);
        System.out.println("n is " + n);
        userIdLiveData.setValue(n);
    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        repository.update(2, new User("Mack", "Lee"));

    }



    class Repository{
        Map<Integer, User> map = new HashMap<>();
        MutableLiveData<User> liveData = new MediatorLiveData<>();
        Integer id = -1;

        private Repository() {
            for (int i = 0; i < 3; i++) {
                map.put(i, new User("Bob", "Lee"));
                map.put(i, new User("Tom", "Lee"));
                map.put(i, new User("Jack", "Lee"));
            }
        }

        void update(int index, User user){

            User temp = map.get(index);
            map.put(index, user);
            System.out.println(map);

            if(id == index) liveData.setValue(user);
//            if(id == index && !temp.getFirstName().equals(temp.getFirstName())) liveData.setValue(user);
        }

        private LiveData<User> getUser(Integer id) {
            if (!this.id.equals(id)) {
                this.id = id;
                liveData = new MediatorLiveData<>();
                liveData.setValue(map.get(id));
            }
            return liveData;
        }



    }


    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> trigger,
                                               @NonNull final Function<X, LiveData<Y>> func) {
        final MediatorLiveData<Y> result = new MediatorLiveData<>();
        result.addSource(trigger, new Observer<X>() {
            LiveData<Y> mSource;

            @Override
            public void onChanged(@Nullable X x) {
                System.out.println("addSource.onChanged");
                LiveData<Y> newLiveData = func.apply(x);
                if (mSource == newLiveData) {
                    System.out.println("mSource == newLiveData");
                    return;
                }
                if (mSource != null) {
                    System.out.println("mSource != null - 1");
                    result.removeSource(mSource);
                }
                mSource = newLiveData;
                System.out.println(mSource);
                if (mSource != null) {
                    System.out.println("mSource != null - 2");
                    result.addSource(mSource, new Observer<Y>() {
                        @Override
                        public void onChanged(@Nullable Y y) {
                            System.out.println("newLiveData.onChanged|" + y);
                            result.setValue(y);
                        }
                    });
                }
            }
        });
        return result;
    }
}
