package mine.documentsprovider;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.support.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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

    public BaseProvider() {
        System.out.println("*********  BaseProvider.Constructor  *********");

    }

    @Override
    public Cursor queryRoots(String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryRoots  *********");

        MatrixCursor result = new MatrixCursor(DEFAULT_ROOT_PROJECTION);
        result.newRow().add(DocumentsContract.Root.COLUMN_ROOT_ID, "myRoot1")
                .add(DocumentsContract.Root.COLUMN_TITLE, "rootTitle1")
                .add(DocumentsContract.Root.COLUMN_SUMMARY, "rootSummary1")
                .add(DocumentsContract.Root.COLUMN_DOCUMENT_ID, "myRootOne/")
                .add(DocumentsContract.Root.COLUMN_MIME_TYPES, DocumentsContract.Root.MIME_TYPE_ITEM)
                .add(DocumentsContract.Root.COLUMN_FLAGS,
//                        DocumentsContract.Root.FLAG_SUPPORTS_SEARCH |
                        DocumentsContract.Root.FLAG_SUPPORTS_RECENTS |
                                DocumentsContract.Root.FLAG_SUPPORTS_CREATE);

        result.newRow().add(DocumentsContract.Root.COLUMN_ROOT_ID, "myRoot2")
                .add(DocumentsContract.Root.COLUMN_TITLE, "rootTitle2")
                .add(DocumentsContract.Root.COLUMN_DOCUMENT_ID, "myRootTwo/")
                .add(DocumentsContract.Root.COLUMN_MIME_TYPES, DocumentsContract.Root.MIME_TYPE_ITEM)
                .add(DocumentsContract.Root.COLUMN_SUMMARY, "rootSummary2")
                .add(DocumentsContract.Root.COLUMN_FLAGS,
                        DocumentsContract.Root.FLAG_SUPPORTS_SEARCH |
                                DocumentsContract.Root.FLAG_SUPPORTS_RECENTS |
                                DocumentsContract.Root.FLAG_SUPPORTS_CREATE);

        return result;
    }


    @Override
    public Cursor queryDocument(String documentId, String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryDocument  *********");

        System.out.println("documentId is " + documentId);

        MatrixCursor result = new MatrixCursor(DEFAULT_DOCUMENT_PROJECTION);

        switch (documentId) {
            //根1
            case "myRootOne/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId)
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "090")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR)
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_DIR_SUPPORTS_CREATE);
                break;
            case "myRootOne/one/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId)
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "01.jpg")
                        .add(DocumentsContract.Document.COLUMN_LAST_MODIFIED, 13)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR)
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_DIR_SUPPORTS_CREATE);
                break;
            case "myRootOne/one/01": //上面2个是目录，这个是文件
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId)
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "01.jpg")
                        .add(DocumentsContract.Document.COLUMN_LAST_MODIFIED, 13)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "image/jpeg")
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_DIR_SUPPORTS_CREATE);
                break;


            //根2
            case "myRootTwo/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, documentId)
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "000")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR)
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_DIR_SUPPORTS_CREATE);
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

            case "myRootOne/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "01")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "01.txt")
                        .add(DocumentsContract.Document.COLUMN_LAST_MODIFIED, 11)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_SUPPORTS_WRITE);

                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "02")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "02.txt")
                        .add(DocumentsContract.Document.COLUMN_LAST_MODIFIED, 18)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_SUPPORTS_WRITE);

                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "03")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "03.txt")
                        .add(DocumentsContract.Document.COLUMN_LAST_MODIFIED, 15)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_SUPPORTS_WRITE);

                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "one/")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "0d1")
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR)
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_SUPPORTS_WRITE);
                break;

            case "myRootOne/one/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "01")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "01.jpg")
                        .add(DocumentsContract.Document.COLUMN_LAST_MODIFIED, 13)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "image/jpg")
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_SUPPORTS_WRITE);
                ;
                break;


            case "myRootTwo/":
                result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, parentDocumentId + "01")
                        .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "01.jpg")
                        .add(DocumentsContract.Document.COLUMN_SIZE, 20)
                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
                        .add(DocumentsContract.Document.COLUMN_FLAGS,
                                DocumentsContract.Document.FLAG_SUPPORTS_WRITE);
                break;

            default:
                System.out.println("there isn't a vaild file");
        }

        return result;
    }

    @Override
    public Cursor queryRecentDocuments(String rootId, String[] projection) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".queryRecentDocuments  *********");
        System.out.println("rootId is " + rootId);

        MatrixCursor result = new MatrixCursor(DEFAULT_DOCUMENT_PROJECTION);

//        result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, "/text/zero/")
//                .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "090")
////                        .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
//                .add(DocumentsContract.Document.COLUMN_MIME_TYPE, DocumentsContract.Document.MIME_TYPE_DIR)
//                .add(DocumentsContract.Document.COLUMN_FLAGS,
//                        DocumentsContract.Document.FLAG_SUPPORTS_WRITE);

        result.newRow().add(DocumentsContract.Document.COLUMN_DOCUMENT_ID, "myRootOne/01")
                .add(DocumentsContract.Document.COLUMN_DISPLAY_NAME, "01.jpg")
                .add(DocumentsContract.Document.COLUMN_SIZE, 20)
                .add(DocumentsContract.Document.COLUMN_MIME_TYPE, "text/plain")
                .add(DocumentsContract.Document.COLUMN_FLAGS,
                        DocumentsContract.Document.FLAG_SUPPORTS_WRITE);

        return result;
    }

    @Override
    public DocumentsContract.Path findDocumentPath(@Nullable String parentDocumentId, String childDocumentId) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".findDocumentPath  *********");
        System.out.println("parentDocumentId is " + parentDocumentId);
        System.out.println("childDocumentId is " + childDocumentId);

        String root = "myRoot2";
        List<String> list = Arrays.asList("myRootOne/one/", "myRootOne/one/01");
        DocumentsContract.Path path = new DocumentsContract.Path(root, list);

        return path;
    }

    @Override
    public String createDocument(String parentDocumentId, String mimeType, String displayName) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".createDocument  *********");

        System.out.println("parentDocumentId is " + parentDocumentId);
        System.out.println("mimeType is " + mimeType);
        System.out.println("displayName is " + displayName);

        return parentDocumentId + "0f2";
    }

    @Override
    public void deleteDocument(String documentId) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".deleteDocument  *********");
        System.out.println("documentId is " + documentId);
    }

    @Override
    public ParcelFileDescriptor openDocument(String documentId, String mode, @Nullable CancellationSignal signal) throws FileNotFoundException {
        System.out.println("*********  " + getClass().getSimpleName() + ".openDocument  *********");
        System.out.println("documentId is " + documentId);

        Path path = null;
        switch (documentId) {

            case "/text/zero/0f0":
                path = Paths.get(getContext().getFilesDir().toString(), "0f0.txt");
                break;

            case "/text/zero/0f1":
                path = Paths.get(getContext().getFilesDir().toString(), "0f1.txt");
                break;

            case "/text/zero/0f2":
                path = Paths.get(getContext().getFilesDir().toString(), "0f2.txt");
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
