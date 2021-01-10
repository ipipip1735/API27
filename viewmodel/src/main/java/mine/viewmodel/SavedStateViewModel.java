package mine.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Administrator on 2021/1/7 16:06.
 */
public class SavedStateViewModel extends ViewModel {

    private SavedStateHandle savedStateHandle;
    private String name;

    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        System.out.println("~~SavedStateViewModel.SavedStateViewModel~~");
        System.out.println("savedStateHandle = " + savedStateHandle);
        this.savedStateHandle = savedStateHandle;

        name = savedStateHandle.<String>get("name");

//        LiveData<String> queryLiveData = savedStateHandle.getLiveData("query");
//        filteredData = Transformations.switchMap(queryLiveData, query -> {
//            return repository.getFilteredData(query);
//        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        savedStateHandle.set("name", this.name);
    }



}
