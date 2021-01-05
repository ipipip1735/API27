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
public class MilkViewModel extends ViewModel {


    private Milk milk;
    private Milk milk1;
    private SavedStateHandle savedStateHandle;



    @ViewModelInject
    public MilkViewModel(Milk milk, Milk milk1, @Assisted SavedStateHandle savedStateHandle) {
//    public MilkViewModel(Milk milk, @Assisted SavedStateHandle savedStateHandle) {
        System.out.println("~~StringViewModel.StringViewModel~~");
        System.out.println("milk = " + milk + ", savedStateHandle = " + savedStateHandle);
        System.out.println("milk1 = " + milk1);


        this.milk = milk;
        this.savedStateHandle = savedStateHandle;

    }

    public void setMilk(Milk milk) {
        this.milk = milk;
    }

    public Milk getMilk() {
        return milk;
    }

    @Override
    protected void onCleared() {
        System.out.println("~~onCleared~~");
        super.onCleared();
    }
}