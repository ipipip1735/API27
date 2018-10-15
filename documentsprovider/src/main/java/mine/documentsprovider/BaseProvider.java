package mine.documentsprovider;

import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsProvider;
import android.support.annotation.Nullable;

import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2018/10/15.
 */
public class BaseProvider extends DocumentsProvider {
    public BaseProvider() {
        System.out.println("*********  BaseProvider.Constructor  *********");
    }

    @Override
    public Cursor queryRoots(String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryRoots  *********");

        return null;
    }

    @Override
    public Cursor queryDocument(String documentId, String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryDocument  *********");

        return null;
    }

    @Override
    public Cursor queryChildDocuments(String parentDocumentId, String[] projection, String sortOrder) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryChildDocuments  *********");

        return null;
    }

    @Override
    public ParcelFileDescriptor openDocument(String documentId, String mode, @Nullable CancellationSignal signal) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".openDocument  *********");

        return null;
    }

    @Override
    public boolean onCreate() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        return true;
    }


}
