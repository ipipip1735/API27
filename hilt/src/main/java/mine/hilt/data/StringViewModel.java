package mine.hilt.data;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.android.components.ActivityRetainedComponent;

/**
 * Created by Administrator on 2021/1/3.
 */
public class StringViewModel extends ViewModel {
    private Milk milk;
    private final SavedStateHandle savedStateHandle;



    @ViewModelInject
    public StringViewModel(Milk milk, @Assisted SavedStateHandle savedStateHandle) {
        System.out.println("~~StringViewModel.StringViewModel~~");
        System.out.println("savedStateHandle = " + savedStateHandle);
        this.milk = milk;
        this.savedStateHandle = savedStateHandle;

    }

//    public String getName() {
//
//        if (name == null) {
//            name = "Bob-" + new Random().nextInt(100);
//        }
//        return name;
//    }

//    public void setName(String name) {
//        this.name = name;
//        savedStateHandle.set("male", name);
//    }

    @Override
    protected void onCleared() {
        System.out.println("~~onCleared~~");
        super.onCleared();
    }
}