package mine.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Administrator on 2018/8/29.
 */
public class BaseDialogFragment extends DialogFragment {
    @Override
    public void onAttach(Context context) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onAttach  *********");

        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDetach  *********");

        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateDialog  *********");
        LayoutInflater inflator = getActivity().getSystemService(LayoutInflater.class);
        View header = inflator.inflate(R.layout.dialog_header, null);


        boolean[] booleans = {false, true, false, true};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        AlertDialog alertDialog = builder.setCustomTitle(header)
                .setMultiChoiceItems(R.array.dialogs, booleans,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                System.out.println("dialog is " + dialog);
                                System.out.println("which is " + which);
                                System.out.println("isChecked is " + isChecked);
                            }
                        })
                .setView(R.layout.dialog_bottom)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        finish();
                        System.out.println("~~positive~~");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("id is " + id);
                        System.out.println("~~negative~~");
                        dialog.cancel();
                    }
                })
                .show();
        return alertDialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCancel  *********");

        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDismiss  *********");

        super.onDismiss(dialog);
    }

    @Override
    public void onDestroyView() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroyView  *********");

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }
}
