package mine.dialog;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
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
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


    public void alertDialog(View view) {
        System.out.println("~~button.alertDialog~~");


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setTitle("title")
                .setMessage("message")
                .setIcon(R.drawable.w1)
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
                .setNeutralButton("waiting", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("id is " + id);
                        System.out.println("~~neutral~~");
                        dialog.cancel();
                    }
                })
                .setOnCancelListener((dialog) -> {
                    System.out.println("OnCancelListener|" + dialog);

                })
                .setOnDismissListener((dialog) -> {
                    System.out.println("OnDismissListener|" + dialog);

                })
                .show();


    }


    public void itemDialog(View view) {
        System.out.println("~~button.itemDialog~~");

        String[] strings = {"one", "two", "three"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setTitle("title")
                .setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("dialog is " + dialog);
                        System.out.println("which is " + which);
                    }
                })
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
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
        alertDialog.registerForContextMenu(findViewById(R.id.textView));
//        alertDialog.


    }


    public void multiChoiceDialog(View view) {
        System.out.println("~~button.itemDialog~~");

        //multiple option
        boolean[] booleans = {false, true, false, true};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setTitle("title")
                .setMultiChoiceItems(R.array.dialogs, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        System.out.println("dialog is " + dialog);
                        System.out.println("which is " + which);
                        System.out.println("isChecked is " + isChecked);
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
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

    }


    public void viewDialog(View view) {
        System.out.println("~~button.stop~~");
        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = inflator.inflate(R.layout.dialog_header, null);


        boolean[] booleans = {false, true, false, true};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                        finish();
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
    }

    public void dataFragmentDialog(View view) {
        System.out.println("~~button.fragmentDialog~~");

        DataDialogFragment dataDialogFragment = new DataDialogFragment();
        dataDialogFragment.show(getSupportFragmentManager(), "dataDialog");


    }

    public void fragmentDialog(View view) {
        System.out.println("~~button.fragmentDialog~~");

        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        baseDialogFragment.show(getSupportFragmentManager(), "dialog");


    }

    public void fullFragmentDialog(View view) {
        System.out.println("~~button.unbind~~");
        FullDialogFragment fullDialogFragment = new FullDialogFragment();
        if (false) {
            fullDialogFragment.show(getSupportFragmentManager(), "dialog");
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(android.R.id.content, fullDialogFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }

    public void update(boolean[] bools) {
        for (int i = 0; i < bools.length; i++) {
            System.out.println(i + " is " + bools[i]);

        }

    }


}
