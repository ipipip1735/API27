package mine.hilt.data;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * Created by Administrator on 2021/1/3.
 */
@HiltViewModel
public class MilkViewModel extends ViewModel {


    private Milk milk;
    private Milk milk1;
    private SavedStateHandle savedStateHandle;


    @Inject
    public MilkViewModel(Milk milk, Milk milk1, SavedStateHandle savedStateHandle) {
//    public MilkViewModel(Milk milk, @Assisted SavedStateHandle savedStateHandle) {
        System.out.println("~~MilkViewModel.MilkViewModel~~");
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