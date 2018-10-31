package mine.contactprovider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
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


    public void start(View view) {
        System.out.println("~~button.start~~");
        Field[] fields = ContactsContract.Contacts.class.getFields();


        for (Field field : fields) {
            try {
                System.out.println(field + " = " + field.get(null));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

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
//        queryProfile();
        queryContact();
    }

    private void queryContact() {
        String[] projection = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.LOOKUP_KEY,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.Contacts.STARRED,
                ContactsContract.Contacts.DISPLAY_NAME};

        String selection = "_id > ?";
        String[] selectionArgs = {"0"};
        String sortOrder = ContactsContract.Contacts._ID + " ASC";
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                projection,
                null,
                null,
                sortOrder);

        if (Objects.isNull(cursor)) return;

        System.out.println("count is " + cursor.getCount());

        while (cursor.moveToNext()) {
            for (String feild : cursor.getColumnNames()) {
                int index = cursor.getColumnIndex(feild);
                System.out.println(feild + " = " + cursor.getString(index));
            }
        }

        cursor.close();
    }

    private void queryProfile() {
        String[] projection = new String[]
                {
                        ContactsContract.Profile._ID,
                        ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
                        ContactsContract.Profile.LOOKUP_KEY,
                        ContactsContract.Profile.PHOTO_THUMBNAIL_URI
                };


        Cursor cursor = getContentResolver().query(
                ContactsContract.Profile.CONTENT_URI,
                projection,
                null,
                null,
                null);

        if (Objects.isNull(cursor)) return;

        System.out.println("count is " + cursor.getCount());

        while (cursor.moveToNext()) {
            for (String feild : cursor.getColumnNames()) {
                int index = cursor.getColumnIndex(feild);
                System.out.println(feild + " = " + cursor.getString(index));
            }
        }
        cursor.close();
    }

}


class Callback implements LoaderCallbacks {

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {

        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object o) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}