package mine.hilt.data;

import android.app.Application;
import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Administrator on 2021/1/3.
 */
public class StringViewModel extends ViewModel {
    private String name;
    private final SavedStateHandle savedStateHandle;


//    @Inject
//    public Milk milk;

    @ViewModelInject
    public StringViewModel(@Assisted SavedStateHandle savedStateHandle) {
        System.out.println("~~StringViewModel.StringViewModel~~");
        System.out.println("savedStateHandle = " + savedStateHandle);
//        System.out.println("milk = " + milk);
        this.savedStateHandle = savedStateHandle;
    }

//    public StringViewModel(SavedStateHandle savedStateHandle) {
//        System.out.println("~~StringViewModel.StringViewModel~~");
//        System.out.println("savedStateHandle = " + savedStateHandle);
//        this.savedStateHandle = savedStateHandle;
//
//        savedStateHandle.set("male", "Bob-" + new Random().nextInt(100));
//    }

    public String getName() {

        if (name == null) {
            name = "Bob-" + new Random().nextInt(100);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
        savedStateHandle.set("male", name);
    }

    @Override
    protected void onCleared() {
        System.out.println("~~onCleared~~");
        super.onCleared();
    }
}