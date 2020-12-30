package mine.hilt;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.components.SingletonComponent;
import mine.hilt.data.Media;

/**
 * Created by Administrator on 2020/12/30 14:22.
 */
public class TheContentProvider extends ContentProvider {

    @EntryPoint
    @InstallIn(SingletonComponent.class)
    interface ExampleContentProviderEntryPoint {
        Media mediaImp();
    }

    @Override
    public boolean onCreate() {
        System.out.println("~~TheContentProvider.onCreate~~");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        System.out.println("~~TheContentProvider.query~~");
        System.out.println("uri = " + uri);
        Context appContext = getContext().getApplicationContext();

        ExampleContentProviderEntryPoint hiltEntryPoint = EntryPointAccessors.fromApplication(appContext, ExampleContentProviderEntryPoint.class);
        System.out.println("hiltEntryPoint = " + hiltEntryPoint);
        System.out.println("mediaImp is " + hiltEntryPoint.mediaImp());


        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
