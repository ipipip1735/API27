package mine.material;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * Created by Administrator on 2020/7/1.
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_dialog);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");

        super.onRestoreInstanceState(savedInstanceState);
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
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");

        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");

        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

//        alertDialog();//警告对话框
//        simpleDialog();//简单对话框
        confirmationDialog();//确认对话框

    }

    private void confirmationDialog() {

        DialogInterface.OnClickListener clickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("~~onClick~~");
                        System.out.println("dialog is " + dialog);
                        System.out.println("which is " + which);
                    }
                };

        String[] items = {"Item 1", "Item 2", "Item 3"};

        new MaterialAlertDialogBuilder(this)
                .setTitle("OOOOOOOO")
                .setNeutralButton("C", clickListener)
                .setPositiveButton("Y", clickListener)
                .setSingleChoiceItems(items, 1, clickListener)
                .show();


    }

    private void simpleDialog() {

        DialogInterface.OnClickListener clickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("~~onClick~~");
                        System.out.println("dialog is " + dialog);
                        System.out.println("which is " + which);
                    }
                };

        String[] items = {"Item 1", "Item 2", "Item 3"};
        new MaterialAlertDialogBuilder(this)
                .setTitle("OOOO")
                .setItems(items, clickListener)
                .show();


    }

    private void alertDialog() {

        DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("~~onClick~~");
                System.out.println("dialog is " + dialog);
                System.out.println("which is " + which);
            }
        };


        new MaterialAlertDialogBuilder(this)
                .setTitle("OOO")
                .setMessage("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZzzzzzzzzzzzzzzzzzzzzzz")
                .setNeutralButton("C", clickListener)
                .setNegativeButton("N", clickListener)
                .setPositiveButton("Y", clickListener)
                .show();
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}