package mine.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistry;

import java.io.File;
import java.util.Random;

/**
 * Created by Administrator on 2021/1/9.
 */
public class SavedStateWithProviderViewModel  extends ViewModel {
    private File file;
    private SavedStateHandle savedStateHandle;

    public SavedStateWithProviderViewModel(SavedStateHandle savedStateHandle) {
        System.out.println("~~SavedStateWithProviderViewModel.SavedStateWithProviderViewModel~~");
        this.savedStateHandle = savedStateHandle;
        SavedStateRegistry.SavedStateProvider savedStateProvider = new SavedStateRegistry.SavedStateProvider() {
            @NonNull
            @Override
            public Bundle saveState() {
                System.out.println("~~SavedStateWithProviderViewModel.saveState~~");

                Bundle bundle = new Bundle();
                bundle.putString("path", file.getAbsolutePath());
                System.out.println("bundle = " + bundle);

                return bundle;
            }
        };
        savedStateHandle.setSavedStateProvider("file", savedStateProvider);

        Bundle bundle = savedStateHandle.get("file");
        if (bundle != null) {
            String path = bundle.getString("path");
            System.out.println("path = " + path);

            file = new File(path);
        }

    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
