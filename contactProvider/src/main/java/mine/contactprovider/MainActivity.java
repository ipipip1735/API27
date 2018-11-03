package mine.contactprovider;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
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


    public void URI(View view) {
        System.out.println("~~button.start~~");
//        Field[] fields = ContactsContract.class.getFields();
//        Field[] fields = ContactsContract.Contacts.class.getFields();
        Field[] fields = ContactsContract.RawContacts.class.getFields();
//        Field[] fields = ContactsContract.Data.class.getFields();

        for (Field field : fields) {
            try {
                System.out.println(field + " = " + field.get(null));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }


    public void insert(View view) {
        System.out.println("~~button.insert~~");

//        insertContact();
//        insertRawContact();
        insertRawContactBatch();

    }

    private void insertRawContactBatch() {
        String accountType = "yahoo";
        String accountName = "Tom Lee";


        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(Uri.parse("A://B/C"));
        ContentProviderOperation operation = builder.withValue("key1", "value1")
                                                    .withValue("key2", "value2")
                                                    .build();

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, accountType)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, accountName)
                .build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Mike Sullivan")
                .build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void insertRawContact() {
        System.out.println("=insertRawContact=");

        String accountType = "yahoo";
        String accountName = "Tom Lee";

        ContentValues values = new ContentValues();
        values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, accountType);
        values.put(ContactsContract.RawContacts.ACCOUNT_NAME, accountName);
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        System.out.println("rawContactId is " + rawContactId);

        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "Tom Lee");
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

    }

    public void update(View view) {
        System.out.println("~~button.bind~~");


        String accountType = "yahoo";
        String accountName = "Tom Lee";

        Uri uri = ContactsContract.RawContacts.CONTENT_URI;

        ContentValues values = new ContentValues();
        values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, accountType);
        values.put(ContactsContract.RawContacts.ACCOUNT_NAME, accountName);

        String where = ContactsContract.RawContacts._ID + " = ?";

        getContentResolver().update(uri, values, where, );


    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void delete(View view) {
        System.out.println("~~button.del~~");


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

//        queryProfile();
//        queryContact();
//        queryRawContact();
//        queryWithEtity();
//        queryPhoneLookup();
    }

    private void queryWithEtity() {
        int rawContactId = 0;
        Uri rawContactUri = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, rawContactId);
        Uri entityUri = Uri.withAppendedPath(rawContactUri, ContactsContract.Contacts.Entity.CONTENT_DIRECTORY);

        String[] selects = new String[]{ContactsContract.RawContacts.SOURCE_ID,
                ContactsContract.Contacts.Entity.DATA_ID,
                ContactsContract.Contacts.Entity.MIMETYPE,
                ContactsContract.Contacts.Entity.DATA1};

        Cursor cursor = getContentResolver().query(entityUri, null, null, null, null);

//        Cursor cursor = getContentResolver().query(uri, null, null, null, sortOrder);

        if (Objects.isNull(cursor)) return;

        System.out.println("URI is " + entityUri);
        System.out.println("count is " + cursor.getCount());

        while (cursor.moveToNext()) {
            System.out.println("----");
            for (String feild : cursor.getColumnNames()) {
                int index = cursor.getColumnIndex(feild);
                System.out.println(feild + " = " + cursor.getString(index));
            }
        }

        cursor.close();

    }

    private void queryPhoneLookup() {
        System.out.println("=queryPhoneLookup=");

        //查询电话
//        String phoneNumber = "+12011231234";
//        String phoneNumber = "12011231234";
//        String phoneNumber = "2011231234";
//        String phoneNumber = "1231234";
        String phoneNumber = "5205653232";
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

        String sortOrder = ContactsContract.Contacts._ID + " ASC";
        Cursor cursor = getContentResolver().query(uri, null, null, null, sortOrder);

        if (Objects.isNull(cursor)) return;

        System.out.println("URI is " + uri);
        System.out.println("count is " + cursor.getCount());

        while (cursor.moveToNext()) {
            System.out.println("----");
            for (String feild : cursor.getColumnNames()) {
                int index = cursor.getColumnIndex(feild);
                System.out.println(feild + " = " + cursor.getString(index));
            }
        }

        cursor.close();
    }

    private void queryRawContact() {
        System.out.println("=queryRawContact=");

        Uri uri = ContactsContract.RawContacts.CONTENT_URI;

        String sortOrder = ContactsContract.Contacts._ID + " ASC";
        Cursor cursor = getContentResolver().query(uri, null, null, null, sortOrder);

        if (Objects.isNull(cursor)) return;

        System.out.println("URI is " + uri);
        System.out.println("count is " + cursor.getCount());

        while (cursor.moveToNext()) {
            System.out.println("----");
            for (String feild : cursor.getColumnNames()) {
                int index = cursor.getColumnIndex(feild);
                System.out.println(feild + " = " + cursor.getString(index));
            }
        }

        cursor.close();
    }

    private void queryContact() {
        System.out.println("=queryContact=");

//        Uri uri = ContactsContract.Contacts.CONTENT_FREQUENT_URI;
//        Uri uri = ContactsContract.Contacts.CONTENT_GROUP_URI;
//        Uri uri = ContactsContract.Contacts.CONTENT_ITEM_TYPE;
//        Uri uri = ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI;
//        Uri uri = ContactsContract.Contacts.CONTENT_STREQUENT_FILTER_URI;
//        Uri uri = ContactsContract.Contacts.CONTENT_STREQUENT_URI;
//        Uri uri = ContactsContract.Contacts.CONTENT_TYPE;
//        Uri uri = ContactsContract.Contacts.CONTENT_VCARD_TYPE;
//        Uri uri = ContactsContract.Contacts.CONTENT_VCARD_URI;
//        Uri uri = ContactsContract.Contacts.CORP_CONTENT_URI;
//        Uri uri = ContactsContract.Contacts.ENTERPRISE_CONTENT_FILTER_URI;

        //查询所有
//        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        //查询lookup key
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI,"0r2-4F45413F3131");
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI,"0r2-4F45413F3131/2");

        //查询人名
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,"Tom");
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,"Lee");
        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, "Lee Tom");


        String sortOrder = ContactsContract.Contacts._ID + " ASC";
        Cursor cursor = getContentResolver().query(uri, null, null, null, sortOrder);

        if (Objects.isNull(cursor)) return;
        System.out.println("URI is " + uri);
        System.out.println("count is " + cursor.getCount());
        while (cursor.moveToNext()) {
            System.out.println("----");
            for (String feild : cursor.getColumnNames()) {
                int index = cursor.getColumnIndex(feild);
                System.out.println(feild + " = " + cursor.getString(index));
            }
        }
        cursor.close();
    }

    private void queryProfile() {
        System.out.println("=queryProfile=");


        String sortOrder = ContactsContract.Contacts._ID + " ASC";
        Cursor cursor = getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, sortOrder);

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