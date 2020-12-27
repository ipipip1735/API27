package mine.hilt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import mine.hilt.data.Company;
import mine.hilt.data.Employee;

/**
 * Created by Administrator on 2020/12/27.
 */
@AndroidEntryPoint
public class TheFragment extends Fragment {

//    @Inject
//    Company company;

    @Override
    public void onAttach(@NonNull Context context) {
        System.out.println("~~TheFragment.onAttach~~");
        super.onAttach(context);
//        System.out.println("Fragment|company = " + company);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_the, container, false);
    }
}
