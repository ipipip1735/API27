package mine.documentsprovider;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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


    final String UUID = "8945";
    final String ROOT = "myRoot-" + UUID;

    public BaseProvider() {
        System.out.println("*********  BaseProvider.Constructor  *********");

    }

    @Override
    public Cursor queryRoots(String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryRoots  *********");

        MatrixCursor result = new MatrixCursor(DEFAULT_ROOT_PROJECTION);
        result.newRow().add(DocumentsContract.Root.COLUMN_ROOT_ID, ROOT + "One")
                .add(DocumentsContract.Root.COLUMN_TITLE, "rootTitle1")
                .add(DocumentsContract.Root.COLUMN_FLAGS,
                        DocumentsContract.Root.FLAG_SUPPORTS_CREATE |
                                DocumentsContract.Root.FLAG_SUPPORTS_RECENTS |
                                DocumentsContract.Root.FLAG_SUPPORTS_SEARCH)
                .add(DocumentsContract.Root.COLUMN_MIME_TYPES, DocumentsContract.Root.MIME_TYPE_ITEM)
                .add(DocumentsContract.Root.COLUMN_SUMMARY, "rootSummary1")
                .add(DocumentsContract.Root.COLUMN_DOCUMENT_ID, "/text/");

        result.newRow().add(DocumentsContract.Root.COLUMN_ROOT_ID, ROOT + "Two")
                .add(DocumentsContract.Root.COLUMN_TITLE, "rootTitle2")
                .add(DocumentsContract.Root.COLUMN_FLAGS,
                        DocumentsContract.Root.FLAG_SUPPORTS_CREATE |
                                DocumentsContract.Root.FLAG_SUPPORTS_RECENTS |
                                DocumentsContract.Root.FLAG_SUPPORTS_SEARCH)
                .add(DocumentsContract.Root.COLUMN_MIME_TYPES, DocumentsContract.Root.MIME_TYPE_ITEM)
                .add(DocumentsContract.Root.COLUMN_SUMMARY, "rootSummary2")
                .add(DocumentsContract.Root.COLUMN_DOCUMENT_ID, "/photo/");

        return result;
    }

    @Override
    public Cursor queryDocument(String documentId, String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryDocument  *********");

        System.out.println("documentId is " + documentId);

        MatrixCursor result = new MatrixCursor(DEFAULT_DOCUMENT_PROJECTION);

        switch (documentId) {
            //根1
            case "/text/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId + "zero/")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "000")
                        .add(DocumentsContract.Document.COLUMN_FLAGS, DocumentsContract.Document.FLAG_SUPPORTS_WRITE)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR);
                break;

            //根2
            case "/photo/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId + "zero/")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "000")
                        .add(DocumentsContract.Document.COLUMN_FLAGS, DocumentsContract.Document.FLAG_SUPPORTS_WRITE)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR);
                break;

            case "/text/zero/0f0":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId + "zero/")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "000")
                        .add(DocumentsContract.Document.COLUMN_FLAGS, DocumentsContract.Document.FLAG_SUPPORTS_WRITE);
//                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain");
                break;


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

            case "/text/zero/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "0f0")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0f0.txt")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain");

                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "0f1")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0f1.txt")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain");
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "one/")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0d1")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR);
                break;

            case "/text/zero/one/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "1f0")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "1f0.jpg")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "image/jpg");
                break;


            case "/photo/zero/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "0f0")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0f0.jpg")
                        .add(DocumentsContract.Document.COLUMN_SIZE, 20)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain");
                break;

            default:
                System.out.println("there isn't a vaild file");
        }

        return result;
    }

    @Override
    public ParcelFileDescriptor openDocument(String documentId, String mode, @Nullable CancellationSignal signal) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".openDocument  *********");
        System.out.println("parentDocumentId is " + documentId);

        Path path = null;
        switch (documentId) {

            case "/text/zero/0f0":
                path = Paths.get(getContext().getFilesDir().toString(), "0f0.txt");
                break;

            case "/text/zero/0f1":
                path = Paths.get(getContext().getFilesDir().toString(), "0f1.txt");
                break;

            case "/text/zero/one/1f0":
                path = Paths.get(getContext().getFilesDir().toString(), "0f0.jpg");
                break;


            case "/photo/zero/0f0":
                path = Paths.get(getContext().getFilesDir().toString(), "0f0.txt");
                break;

            default:
                System.out.println("there isn't a vaild file!");
                return null;
        }



        ParcelFileDescriptor pfd = null;
        if (Objects.nonNull(path)) {
            if (!Files.exists(path)) {
                try {
                    path = Files.createFile(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            Handler handler = new Handler(getContext().getMainLooper());
            try {
                pfd = ParcelFileDescriptor.open(path.toFile(), ParcelFileDescriptor.parseMode(mode),
                        handler, new ParcelFileDescriptor.OnCloseListener() {
                    @Override
                    public void onClose(IOException e) {
                        System.out.println("close pfd");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pfd;
    }

    @Override
    public boolean onCreate() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        return true;
    }


}
