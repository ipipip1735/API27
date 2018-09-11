package mine.clipboard;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2018/8/12.
 */

public class ClipContentProvider extends ContentProvider {

    public ClipContentProvider() {
        System.out.println("++++++ " + getClass().getSimpleName() + " ++++++");

    }

    @Override
    public boolean onCreate() {
        System.out.println("**********  " + getClass().getSimpleName() + ".onCreate  **********");

        return false;
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        System.out.println("**********  " + getClass().getSimpleName() + ".openFile  **********");

        return null;
    }

    @Nullable
    @Override
    public String[] getStreamTypes(@NonNull Uri uri, @NonNull String mimeTypeFilter) {
        System.out.println("**********  " + getClass().getSimpleName() + ".getStreamTypes  **********");
        System.out.println("uri is " + uri);
        System.out.println("mimeTypeFilter is " + mimeTypeFilter);
        return super.getStreamTypes(uri, mimeTypeFilter);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        System.out.println("**********  " + getClass().getSimpleName() + ".query  **********");

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        System.out.println("**********  " + getClass().getSimpleName() + ".getType  **********");
        return "text/og";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        System.out.println("**********  " + getClass().getSimpleName() + ".insert  **********");

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onDestroy  **********");

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        System.out.println("**********  " + getClass().getSimpleName() + ".update  **********");

        return 0;
    }
}
