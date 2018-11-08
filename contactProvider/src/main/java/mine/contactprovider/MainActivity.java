package mine.contactprovider;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Rect;
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
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static android.provider.ContactsContract.CommonDataKinds.Email.TYPE_HOME;
import static android.provider.ContactsContract.CommonDataKinds.Email.TYPE_WORK;
import static android.provider.ContactsContract.CommonDataKinds.Im.PROTOCOL_MSN;
import static android.provider.ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ;

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
        System.out.println("~~button.URI~~");
//        Field[] fields = ContactsContract.class.getFields();
//        Field[] fields = ContactsContract.Contacts.class.getFields();
//        Field[] fields = ContactsContract.RawContacts.class.getFields();
//        Field[] fields = ContactsContract.Data.class.getFields();
//        Field[] fields = ContactsContract.PhoneLookup.class.getFields();
//        Field[] fields = ContactsContract.Groups.class.getFields();
//        Field[] fields = ContactsContract.Profile.class.getFields();
//        Field[] fields = ContactsContract.QuickContact.class.getFields();
//        Field[] fields = ContactsContract.DisplayPhoto.class.getFields();
//        Field[] fields = ContactsContract.Intents.class.getFields();
//        Field[] fields = ContactsContract.Intents.Insert.class.getFields();
        Field[] fields = ContactsContract.SyncState.class.getFields();

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
        insertData();
//        insertRawContactBatch(); //批量插入

    }

    private void insertData() {
        System.out.println("=insertData=");
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();


        int rawContactInsertIndex = ops.size();
        //add item to raw_contacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "YaHoo")
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "yh_mine")
                .build());

        //Display name/Contact name
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "KXX OXX")
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, "KXX")
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, "OXX")
                .build());

        //Phone Number
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "9X-XXXXXXXXX")
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, TYPE_WORK)
                .build());

        //Email details
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, "abc@aho.com")
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, TYPE_HOME)
                .build());

        //Postal Address
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POBOX, "Postbox")
                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.STREET, "street")
                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.CITY, "city")
                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.REGION, "region")
                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, "postcode")
                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY, "country")
                .withValue(ContactsContract.CommonDataKinds.StructuredPostal.TYPE, TYPE_HOME)
                .build());


        //Organization details
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, "Devindia")
                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, "0")
                .withValue(ContactsContract.CommonDataKinds.Organization.LABEL, "D.V.")
                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, "Developer")
                .build());


        rawContactInsertIndex = ops.size();
        //add item to raw_contacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "QQ")
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "qq.tnt")
                .build());

        //IM details
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Im.DATA, "520520520")
                .withValue(ContactsContract.CommonDataKinds.Im.TYPE, TYPE_HOME)
                .withValue(ContactsContract.CommonDataKinds.Im.PROTOCOL, PROTOCOL_QQ)
                .build());

        rawContactInsertIndex = ops.size();
        //add item to raw_contacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "MSN")
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "msn.tnt")
                .build());

        //IM details
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Im.DATA, "kkkk_msn")
                .withValue(ContactsContract.CommonDataKinds.Im.TYPE, TYPE_HOME)
                .withValue(ContactsContract.CommonDataKinds.Im.PROTOCOL, PROTOCOL_MSN)
                .build());


        try {
            ContentProviderResult[] results = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            for (ContentProviderResult result : results) {
                System.out.println("result is " + result);
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    private void insertRawContactBatch() {

        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(Uri.parse("A://B/C"));
        ContentProviderOperation operation = builder.withValue("key1", "value1")
                .withValue("key2", "value2")
                .build();

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
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
        System.out.println("~~button.update~~");


        String accountType = "yahoo";
        String accountName = "Tom Lee";

        Uri uri = ContactsContract.RawContacts.CONTENT_URI;

        ContentValues values = new ContentValues();
//        values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, accountType);
//        values.put(ContactsContract.RawContacts.ACCOUNT_NAME, accountName);
        values.put(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY, "kkkkkkkk");

        String where = ContactsContract.RawContacts._ID + " = ?";
        String[] selectArgs = {"1"};
        getContentResolver().update(uri, values, where, selectArgs);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onActivityResult  *********");

        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode);
        System.out.println(data);

//        System.out.println("Uri is " + data.getData());


    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        startForEdit(); //编辑联系人
//        startForPick(); //选择联系人
//        startForInsert(); //新增联系人


    }

    private void startForEdit() {
        System.out.println("=startForEdit=");

        Uri uri = ContactsContract.Contacts.CONTENT_FILTER_URI;
        uri = Uri.withAppendedPath(uri, "KXX");

        Uri lookupURI = ContactsContract.Contacts.getLookupUri(getContentResolver(), uri);
        System.out.println("lookupURI is " + lookupURI);


//        Intent insertIntent = new Intent(Intent.ACTION_EDIT);
        Intent insertIntent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
        insertIntent.setData(lookupURI);
        startActivityForResult(insertIntent, 200);


    }

    private void startForPick() {
        System.out.println("=startForPick=");

        //新增联系人基本信息
        Intent insertIntent = new Intent(Intent.ACTION_PICK);
//        insertIntent.setData(ContactsContract.Contacts.CONTENT_URI);
//        insertIntent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//        insertIntent.setData(ContactsContract.CommonDataKinds.Email.CONTENT_URI);
        insertIntent.setData(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI);
        startActivityForResult(insertIntent, 200);

    }


    private void startForInsert() {
        System.out.println("=StartForInsert=");


        //新增联系人基本信息
        Intent insertIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        insertIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        insertIntent.putExtra(ContactsContract.Intents.Insert.NAME, "name|Jone");
        insertIntent.putExtra(ContactsContract.Intents.Insert.COMPANY, "company|albbb");
        insertIntent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, "job_title|ceo");
        insertIntent.putExtra(ContactsContract.Intents.Insert.IM_HANDLE, "im_handle|xx");
        insertIntent.putExtra(ContactsContract.Intents.Insert.EMAIL, "email|x@x.com");
        insertIntent.putExtra(ContactsContract.Intents.Insert.POSTAL, "postal_0000");
        insertIntent.putExtra(ContactsContract.Intents.Insert.PHONE, "phone_123");
        insertIntent.putExtra(ContactsContract.Intents.Insert.NOTES, "notes_xx");
        insertIntent.putExtra(ContactsContract.Intents.Insert.EXTRA_DATA_SET, "notes_xx");

        startActivityForResult(insertIntent, 200);


        //新增联系人详细信息
//        Intent insertIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
//        insertIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
//
//
//        insertIntent.putExtra(ContactsContract.Intents.Insert.NAME, "Jone");
//        insertIntent.putExtra(ContactsContract.Intents.Insert.COMPANY, "TNT");
//        insertIntent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, "ceo");
//
//        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
//        ContentValues rawContactRow = new ContentValues();
//        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_TYPE, "");
//        rawContactRow.put(ContactsContract.RawContacts.ACCOUNT_NAME, "");
//        contactData.add(rawContactRow);
//
//        ContentValues phoneRow = new ContentValues();
//        phoneRow.put(ContactsContract.Data.MIMETYPE,
//                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
//        phoneRow.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "(303) 000-1234");
//        contactData.add(phoneRow);
//
//        ContentValues emailRow = new ContentValues();
//        emailRow.put(ContactsContract.Data.MIMETYPE,
//                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
//        emailRow.put(ContactsContract.CommonDataKinds.Email.ADDRESS, "ee@ff.com");
//        contactData.add(emailRow);
//
//        insertIntent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
//
//        startActivity(insertIntent);


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
        queryContact();
//        queryWithLookupKey();
//        queryRawContact();
//        queryData();
//        queryWithEtity();
//        queryPhoneLookup();
//        queryQuickContact();
//        queryPhoto();

    }

    private void queryPhoto() {


        //方法一，使用RawContacts获取URI
//        Uri displayPhotoUri = Uri.withAppendedPath(
//                ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, 4),
//                ContactsContract.RawContacts.DisplayPhoto.CONTENT_DIRECTORY);
//        System.out.println(rawContactPhotoUri);


        //方法二，使用RawContacts.DisplayPhoto获取URI

        long photoKey = -1;
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, 4);
        String[] projection = {ContactsContract.Contacts.PHOTO_FILE_ID};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor.moveToFirst()) {
            photoKey = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_FILE_ID));
            cursor.close();
        } else {
            cursor.close();
            return;
        }

        Uri displayPhotoUri = ContentUris.withAppendedId(ContactsContract.DisplayPhoto.CONTENT_URI, photoKey);

        System.out.println("displayPhotoUri is " + displayPhotoUri);
        try {
            AssetFileDescriptor fd = getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");
            System.out.println("lenght is " + fd.getLength());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void queryQuickContact() {
        System.out.println("=queryQuickContact=");


//        TextView view = new TextView(this);
//        Uri lookupUri = ContactsContract.Contacts.getLookupUri(getContentResolver(),
//                Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "4"));
//        ContactsContract.QuickContact.showQuickContact(this, view, lookupUri,
//                ContactsContract.QuickContact.MODE_LARGE, null);


        Rect rect = new Rect(50, 50, 150, 250);
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(getContentResolver(),
                Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "4"));
        ContactsContract.QuickContact.showQuickContact(this, rect, lookupUri,
                null, null);


    }

    private void queryWithLookupKey() {
        System.out.println("=queryWithLookupKey=");


        //contacts lookupURI
        Uri lookupUri = ContactsContract.Contacts.getLookupUri(
                getContentResolver(),
                Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "2"));


        //raw_contacts lookupURI
//        Uri lookupUri = ContactsContract.RawContacts.getContactLookupUri(getContentResolver(),
//                Uri.withAppendedPath(ContactsContract.RawContacts.CONTENT_URI, "2"));


        Cursor cursor = getContentResolver().query(lookupUri, null, null, null, null);


        if (Objects.isNull(cursor)) return;

        System.out.println("URI is " + lookupUri);
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

    private void queryData() {
        System.out.println("=queryData=");


        Uri uri = ContactsContract.Data.CONTENT_URI;

        String[] projection = {ContactsContract.Data._ID};
        String selection = ContactsContract.Data.RAW_CONTACT_ID + " = ?";
        String[] selectionArgs = {"1"};

        Cursor cursor = getContentResolver().query(uri, null, selection, selectionArgs, null);


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

    private void queryWithEtity() {
        System.out.println("=queryWithEtity=");

        //Contacts实体
        int contactId = 2;
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        Uri entityUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Entity.CONTENT_DIRECTORY);
        String[] projection = new String[]{ContactsContract.Contacts.Entity.DATA_ID};

        //Raw_Contacts实体
//        int rawContactId = 2;
//        Uri rawContactUri = ContentUris.withAppendedId(
//                ContactsContract.RawContacts.CONTENT_URI, rawContactId);
//        Uri entityUri = Uri.withAppendedPath(rawContactUri,
//                ContactsContract.RawContacts.Entity.CONTENT_DIRECTORY);


        Cursor cursor = getContentResolver().query(entityUri, null, null, null, null);


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

        Uri uri = Uri.withAppendedPath(ContactsContract.RawContacts.CONTENT_URI, "2");

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