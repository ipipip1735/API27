package mine.contactprovider;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.QuickContactBadge;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

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

//        insertRawContact();
        insertData();
//        insertRawContactBatch(); //批量插入

    }

    private void insertData() {
        System.out.println("=insertData=");
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();


        int rawContactInsertIndex = 0;//保存后向引用索引为0
        //add item to raw_contacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
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


        rawContactInsertIndex = ops.size();//保存后向引用索引为0
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

    public void quickbadge(View view) {
        System.out.println("~~button.quickbadge~~");

        //方法一
//        Uri lookupURI = ContactsContract.Contacts.getLookupUri(getContentResolver(),
//                Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, "Jobs Lee"));
//
//        QuickContactBadge mBadge = findViewById(R.id.quickbadge);
//        mBadge.assignContactUri(lookupURI);


        //方法二
        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, "Chris");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (Objects.isNull(cursor)) return;


        int mIdColumn;
        int mLookupKeyColumn;
        Uri lookupUri;

        cursor.moveToFirst();
        mIdColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        mLookupKeyColumn = cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY);
        lookupUri = ContactsContract.Contacts.getLookupUri(cursor.getLong(mIdColumn),
                cursor.getString(mLookupKeyColumn));
        System.out.println("mContactUri is " + lookupUri);


        String thmubnailUri = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
        System.out.println("mThmubnailColum is " + thmubnailUri);
        cursor.close();


        QuickContactBadge mBadge = findViewById(R.id.quickbadge);
        mBadge.assignContactUri(lookupUri);

        try {
            AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(Uri.parse(thmubnailUri), "r");
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor(), null, null);
            mBadge.setImageBitmap(bitmap);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

//        queryProfile();
//        queryContact();
//        queryWithLookupKey();
//        queryRawContact();
//        queryData();
//        queryWithEtity();
//        queryPhoneLookup();
//        queryQuickContact();
//        queryPhoto();

    }

    private void queryPhoto() {


        //方法一：使用过滤器匹配联系人
        Uri uri = Uri.parse("content://com.android.contacts/contacts/1/photo");
//        Uri uri = Uri.parse("content://com.android.contacts/contacts/filter/Chris");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        System.out.println("count is " + cursor.getCount());
        cursor.moveToNext();
        //获取缩略图Uri
        Uri photo_thumb_uri = Uri.parse(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)));
        System.out.println("photo_thumb_uri is " + photo_thumb_uri);
        //获取全尺寸图Uri
        Uri photo_uri = Uri.parse(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)));
        System.out.println("photo_uri is " + photo_uri);
        cursor.close();
        try {
            AssetFileDescriptor fd = getContentResolver().openAssetFileDescriptor(photo_thumb_uri, "r");
            System.out.println("photo_thumb_uri'lenght is " + fd.getLength());
            System.out.println("photo_thumb_uri'fd is " + fd.getFileDescriptor());
            QuickContactBadge mBadge = findViewById(R.id.quickbadge);
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd.getFileDescriptor(), null, null);
            System.out.println("w = " + bitmap.getWidth() + ", h = " +  bitmap.getHeight());
            mBadge.setImageBitmap(bitmap);

            fd = getContentResolver().openAssetFileDescriptor(photo_uri, "r");
            System.out.println("photo_uri'lenght is " + fd.getLength());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //方法二：分部查询
//        long photoKey = -1;
//        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, 1);
//        String[] projection = {ContactsContract.Contacts.PHOTO_FILE_ID};
//        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            photoKey = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_FILE_ID));
//            cursor.close();
//        } else {
//            cursor.close();
//            return;
//        }
//
//        Uri displayPhotoUri = ContentUris.withAppendedId(ContactsContract.DisplayPhoto.CONTENT_URI, photoKey);
//
//        System.out.println("displayPhotoUri is " + displayPhotoUri);
//        try {
//            AssetFileDescriptor fd = getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");
//            System.out.println("lenght is " + fd.getLength());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


    }

    private void queryQuickContact() {
        System.out.println("=queryQuickContact=");


//        TextView tween = new TextView(this);
//        Uri lookupUri = ContactsContract.Contacts.getLookupUri(getContentResolver(),
//                Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "4"));
//        ContactsContract.QuickContact.showQuickContact(this, tween, lookupUri,
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
                Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "1"));


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

    /**
     * Data表Uri如下：
     * content://com.android.contacts/data
     * content://com.android.contacts/data/1
     * content://com.android.contacts/data/phones
     * content://com.android.contacts/data_enterprise/phones
     * content://com.android.contacts/data/phones/1
     * content://com.android.contacts/data/phones/filter
     * content://com.android.contacts/data/phones/filter/XXX
     * content://com.android.contacts/data/phones/filter_enterprise?directory=0
     * content://com.android.contacts/data/phones/filter_enterprise/XXX?directory=0
     * content://com.android.contacts/data/emails
     * content://com.android.contacts/data/emails/1
     * content://com.android.contacts/data/emails/lookup
     * content://com.android.contacts/data/emails/lookup/XXX
     * content://com.android.contacts/data/emails/filter
     * content://com.android.contacts/data/emails/filter/XXX
     * content://com.android.contacts/data/emails/filter_enterprise?directory=0
     * content://com.android.contacts/data/emails/filter_enterprise/XXX?directory=0
     * content://com.android.contacts/data/emails/lookup_enterprise
     * content://com.android.contacts/data/emails/lookup_enterprise/XXX
     * content://com.android.contacts/data/postals
     * content://com.android.contacts/data/postals/1
     * content://com.android.contacts/data/usagefeedback/1,2,3
     * content://com.android.contacts/data/callables/
     * content://com.android.contacts/data/callables/1
     * content://com.android.contacts/data/callables/filter
     * content://com.android.contacts/data/callables/filter/XXX
     * content://com.android.contacts/data/callables/filter_enterprise?directory=0
     * content://com.android.contacts/data/callables/filter_enterprise/XXX?directory=0
     * content://com.android.contacts/data/contactables/
     * content://com.android.contacts/data/contactables/filter
     * content://com.android.contacts/data/contactables/filter/XXX
     */
    private void queryData() {
        System.out.println("=queryData=");


        //方法一：返回所有结果集
//        Uri uri = ContactsContract.Data.CONTENT_URI;
//        Uri uri = Uri.parse("content://com.android.contacts/data");
//        Uri uri = Uri.parse("content://com.android.contacts/data/2");
//        Uri uri = Uri.parse("content://com.android.contacts/data/phones");
//        Uri uri = Uri.parse("content://com.android.contacts/data/phones/1");
//        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/chris");
//        Uri uri = Uri.parse("content://com.android.contacts/data/emails");
//        Uri uri = Uri.parse("content://com.android.contacts/data/emails/lookup/ab@de.com");
//        Uri uri = Uri.parse("content://com.android.contacts/data/emails/filter/chris");
        Uri uri = Uri.parse("content://com.android.contacts/phone_lookup/3012345678");
//        Uri uri = Uri.parse("content://com.android.contacts/phone_lookup/1231234");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);


        //方法二：设置查询条件
//        String[] projection = {ContactsContract.Data._ID};
//        String selection = ContactsContract.Data.RAW_CONTACT_ID + " = ?";
//        String[] selectionArgs = {"1"};
//        Cursor cursor = getContentResolver().query(uri, null, selection, selectionArgs, null);


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
        String phoneNumber = "3012345678";
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

    /**
     * content://com.android.contacts/raw_contacts
     * content://com.android.contacts/raw_contacts/1
     * content://com.android.contacts/raw_contacts/1/data
     * content://com.android.contacts/raw_contacts/1/display_photo //测试失败
     * content://com.android.contacts/raw_contacts/1/entity
     * content://com.android.contacts/raw_contact_entities
     * content://com.android.contacts/raw_contact_entities_corp
     */
    private void queryRawContact() {
        System.out.println("=queryRawContact=");

//        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
//        Uri uri = Uri.withAppendedPath(ContactsContract.RawContacts.CONTENT_URI, "1");
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts/1/data");

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

    /**
     * Contacts表相关的Uri如下：
     * content://com.android.contacts/contacts
     * content://com.android.contacts/contacts/1
     * content://com.android.contacts/contacts/1/data
     * content://com.android.contacts/contacts/1/entities
     * content://com.android.contacts/contacts/1/suggestions
     * content://com.android.contacts/contacts/1/suggestions/XXX
     * content://com.android.contacts/contacts/1/photo
     * content://com.android.contacts/contacts/1/display_photo
     * content://com.android.contacts/contacts_corp/1/photo
     * content://com.android.contacts/contacts_corp/1/display_photo
     * content://com.android.contacts/contacts/filter
     * content://com.android.contacts/contacts/filter/XXX
     * content://com.android.contacts/contacts/lookup/nlookup
     * content://com.android.contacts/contacts/lookup/nlookup/data
     * content://com.android.contacts/contacts/lookup/nlookup/photo
     * content://com.android.contacts/contacts/lookup/nlookup/1
     * content://com.android.contacts/contacts/lookup/nlookup/1/data
     * content://com.android.contacts/contacts/lookup/nlookup/1/photo
     * content://com.android.contacts/contacts/lookup/nlookup/display_photo
     * content://com.android.contacts/contacts/lookup/nlookup/1/display_photo
     * content://com.android.contacts/contacts/lookup/nlookup/entities
     * content://com.android.contacts/contacts/lookup/nlookup/1/entities
     * content://com.android.contacts/contacts/as_vcard/nlookup
     * content://com.android.contacts/contacts/as_multi_vcard/XXX
     * content://com.android.contacts/contacts/strequent/
     * content://com.android.contacts/contacts/strequent/filter/XXX
     * content://com.android.contacts/contacts/group/XXX
     * content://com.android.contacts/contacts/frequent
     * content://com.android.contacts/contacts/delete_usage
     * content://com.android.contacts/contacts/filter_enterprise?directory=0
     * content://com.android.contacts/contacts/filter_enterprise/XXX?directory=0
     * Uri uri = ContactsContract.Contacts.CONTENT_FREQUENT_URI;//常用联系人，API29后被废弃
     * Uri uri = ContactsContract.Contacts.CONTENT_GROUP_URI;//联系人分组
     * Uri uri = ContactsContract.Contacts.CONTENT_STREQUENT_FILTER_URI;//过滤认名
     * Uri uri = ContactsContract.Contacts.CONTENT_ITEM_TYPE;//支持的MIME
     * Uri uri = ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI;
     * Uri uri = ContactsContract.Contacts.CONTENT_STREQUENT_URI;
     * Uri uri = ContactsContract.Contacts.CONTENT_TYPE;
     * Uri uri = ContactsContract.Contacts.CONTENT_VCARD_TYPE;
     * Uri uri = ContactsContract.Contacts.CONTENT_VCARD_URI;
     * Uri uri = ContactsContract.Contacts.CORP_CONTENT_URI;
     * Uri uri = ContactsContract.Contacts.ENTERPRISE_CONTENT_FILTER_URI;
     */
    private void queryContact() {
        System.out.println("=queryContact=");

        //查询Contacts表
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
//        uri = Uri.parse(uri.toString() + "/1");//返回contact_id = 1的联系人的状态信息
//        uri = Uri.parse(uri.toString() + "/1/data");//返回contact_id = 1的联系人的数据信息
//        uri = Uri.parse(uri.toString() + "/1/entities");//使用/entities和/data后缀几乎没什么区别，返回id为联系人的数据信息
        uri = Uri.parse(uri.toString() + "/1/photo");//返回contact_id = 1且mimetype = vnd.android.cursor.item/photo的联系人
//        uri = Uri.parse(uri.toString() + "/1/suggestions");
//        uri = Uri.parse(uri.toString() + "/1/suggestions/XXX");


        //查询lookup key
//        Uri uri = ContactsContract.Contacts.getLookupUri(1, "0r1-2E384C3A4E403233");
//        Uri uri = ContactsContract.Contacts.getLookupUri(getContentResolver(), Uri.parse("content://com.android.contacts/contacts/1"));
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI,"0r3-");//获取Contact_ID为3的记录
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI,"0r1-4032322A2A2A");//使用Lookup查询
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI,"0r2-4F45413F3131/2");//Lookup和ID组合查询



        //查询人名
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,"Chris");//名
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,"Lee");//姓
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, "Chris Lee");//姓名


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