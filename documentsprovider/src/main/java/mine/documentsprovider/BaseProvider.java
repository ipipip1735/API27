package mine.documentsprovider;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.support.annotation.Nullable;

import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2018/10/15.
 */
public class BaseProvider extends DocumentsProvider {
    private static final String[] DEFAULT_ROOT_PROJECTION = new String[]{
            DocumentsContract.Root.COLUMN_ROOT_ID,
            DocumentsContract.Root.COLUMN_ICON,
            DocumentsContract.Root.COLUMN_TITLE,
            DocumentsContract.Root.COLUMN_FLAGS,
            DocumentsContract.Root.COLUMN_DOCUMENT_ID};

    private static final String[] DEFAULT_DOCUMENT_PROJECTION = new String[]{
            DocumentsContract.Document.COLUMN_DOCUMENT_ID,
            DocumentsContract.Document.COLUMN_DISPLAY_NAME,
            DocumentsContract.Document.COLUMN_MIME_TYPE,
            DocumentsContract.Document.COLUMN_FLAGS,
            DocumentsContract.Document.COLUMN_SIZE,
            DocumentsContract.Document.COLUMN_LAST_MODIFIED};


    public BaseProvider() {
        System.out.println("*********  BaseProvider.Constructor  *********");
    }

    @Override
    public Cursor queryRoots(String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryRoots  *********");

        final MatrixCursor result = new MatrixCursor(DEFAULT_ROOT_PROJECTION);

        final MatrixCursor.RowBuilder row = result.newRow();
        row.add(DocumentsContract.Root.COLUMN_ROOT_ID, 1)
                .add(DocumentsContract.Root.COLUMN_TITLE, "rootTitle")
                .add(DocumentsContract.Root.COLUMN_FLAGS, DocumentsContract.Root.FLAG_SUPPORTS_CREATE | DocumentsContract.Root.FLAG_SUPPORTS_RECENTS | DocumentsContract.Root.FLAG_SUPPORTS_SEARCH)
                .add(DocumentsContract.Root.COLUMN_SUMMARY, "rootSummary");

        return result;
    }

    @Override
    public Cursor queryDocument(String documentId, String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryDocument  *********");


        return null;
    }

    @Override
    public Cursor queryChildDocuments(String parentDocumentId, String[] projection, String sortOrder) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryChildDocuments  *********");

        final MatrixCursor result = new
                MatrixCursor(DEFAULT_DOCUMENT_PROJECTION);
        final File parent = getFileForDocId(parentDocumentId);
        for (File file : parent.listFiles()) {
            // Adds the file&#39;s display name, MIME type, size, and so on.
            includeFile(result, null, file);
        }
        return result;
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
