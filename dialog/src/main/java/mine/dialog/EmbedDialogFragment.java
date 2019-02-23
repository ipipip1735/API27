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
import android.view.Window;
import android.widget.Button;


/**
 * Created by Administrator on 2018/8/29.
 */
public class EmbedDialogFragment extends DialogFragment {
    public boolean[] booleans;

    @Override
    public void onAttach(Context context) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onAttach  *********");
        super.onAttach(context);
        booleans = new boolean[]{false, false, false, false};
    }

    @Override
    public void onDetach() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDetach  *********");
        booleans = null;
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateView  *********");

        View view = inflater.inflate(R.layout.dialog_embed, container, false);
        Button button = view.findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateDialog  *********");
        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawableResource(R.color.colorAccent);

        return dialog;
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
