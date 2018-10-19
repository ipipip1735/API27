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


    final String ROOT = "/";

    public BaseProvider() {
        System.out.println("*********  BaseProvider.Constructor  *********");
    }

    @Override
    public Cursor queryRoots(String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryRoots  *********");

        final MatrixCursor result = new MatrixCursor(DEFAULT_ROOT_PROJECTION);

        final MatrixCursor.RowBuilder row = result.newRow();
        row.add(DocumentsContract.Root.COLUMN_ROOT_ID, ROOT)
                .add(DocumentsContract.Root.COLUMN_TITLE, "rootTitle")
                .add(DocumentsContract.Root.COLUMN_FLAGS, DocumentsContract.Root.FLAG_SUPPORTS_CREATE | DocumentsContract.Root.FLAG_SUPPORTS_RECENTS | DocumentsContract.Root.FLAG_SUPPORTS_SEARCH )
                .add(DocumentsContract.Root.COLUMN_MIME_TYPES, DocumentsContract.Root.MIME_TYPE_ITEM)
                .add(DocumentsContract.Root.COLUMN_SUMMARY, "rootSummary")
                .add(DocumentsContract.Root.COLUMN_DOCUMENT_ID, ROOT);

        return result;
    }

    @Override
    public Cursor queryDocument(String documentId, String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryDocument  *********");

        System.out.println("documentId is " + documentId);

        MatrixCursor result = new MatrixCursor(DEFAULT_DOCUMENT_PROJECTION);


        switch (documentId) {
            case ROOT:
                MatrixCursor.RowBuilder row = result.newRow();
                row.add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId + "zero/")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0")
                        .add(DocumentsContract.Document.COLUMN_FLAGS, DocumentsContract.Document.FLAG_SUPPORTS_WRITE)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR);

                break;

//            case ROOT:
//                MatrixCursor.RowBuilder row = result.newRow();
//                row.add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId + "zero.jpg")
//                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0")
//                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "image/jpg");
//
//                break;

//            case ROOT + "zero/" + "1f0":
//                row.add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId + "zero/") .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0")
//                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR);
//
//                break;

            default:
                System.out.println("there isn't a vaild file");
        }
        return result;
    }

    @Override
    public Cursor queryChildDocuments(String parentDocumentId, String[] projection, String sortOrder) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryChildDocuments  *********");

        System.out.println("parentDocumentId is " + parentDocumentId);

        MatrixCursor result = new MatrixCursor(DEFAULT_DOCUMENT_PROJECTION);

        switch (parentDocumentId) {

            case ROOT + "zero/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "0f0")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "1f0.txt")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
                .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain");
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "0f1")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "1f1.txt")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain");
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "one/")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "d1")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR);
                break;

            case ROOT + "zero/" + "one/" :
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "1f0")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "1f0.jpg")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "image/jpg");
                break;

            default:
                System.out.println("there isn't a vaild file");
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
