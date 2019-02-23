package mine.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class AlertDialogActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
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

    public void open(View view) {
        System.out.println("~~button.open~~");

//        basic(); //最简alertDialog
        setView(); //自定义header和body


    }

    private void setView() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View header = layoutInflater.inflate(R.layout.dialog_header, null);
        View body = layoutInflater.inflate(R.layout.dialog_embed, null);
        String[] strings = {"one", "two", "three"};
        boolean[] booleans = {false, true, false, true};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.setCancelable(false)
                .setIcon(R.drawable.w1)

                //标题部分
                .setCustomTitle(header)
//                .setTitle("title")  //不能和setCustomTitle同时使用


                //Message部分
                .setMessage("message") //不能和setItems()同时使用
//                .setItems(strings, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.out.println("dialog is " + dialog);
//                        System.out.println("which is " + which);
//                    }
//                })


                //支持多选
                .setMultiChoiceItems(R.array.dialogs, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        System.out.println("dialog is " + dialog);
                        System.out.println("which is " + which);
                        System.out.println("isChecked is " + isChecked);
                    }
                })


                //自定义body部分
                .setView(body)


                //设置确认，否则，中性按钮
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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

                //创建对话框并显示
                .show();

    }

    private void basic() {
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


        //提高兼容性
//        Message message= Message.obtain(new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                System.out.println("msg is " + msg);
//                return true;
//            }
//        }));
//        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "gogo0", message);//设置确认按钮回调
    }



}
