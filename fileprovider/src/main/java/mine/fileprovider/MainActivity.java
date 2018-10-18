package mine.fileprovider;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Created by Administrator on 2018/9/24.
 */
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onActivityResult  **********");

        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode);

        Uri uri = data.getData();
        System.out.println("uri is " + uri);


        //获取文件内容
        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "r")) {
            FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

            try (InputStream inputStream = new FileInputStream(fdp);
                 InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {

                String s;
                while (Objects.nonNull((s = bufferedReader.readLine()))) {
                    System.out.println("File'Content is " + s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        //写数据到文件
        try (ParcelFileDescriptor parcelFileDescriptor =
                     getContentResolver().openFileDescriptor(uri, "wa")) {
            FileDescriptor fdp = parcelFileDescriptor.getFileDescriptor();

            try (OutputStream outputStream = new FileOutputStream(fdp);
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream, UTF_8);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                String sql = "SELECLT * FROM Car WHERE ROWID = " + new Random().nextInt(99) + ";";
                bufferedWriter.write(sql);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //检索类型
        String mimeType = getContentResolver().getType(uri);
        System.out.println("MIME is " + mimeType);

        //检索类型
        Cursor cursor = getContentResolver().query(uri,
                null, null, null, null);

        int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
        cursor.moveToFirst();
        System.out.println("name is " + (cursor.getString(nameIndex)));
        System.out.println("size is " + (Long.toString(cursor.getLong(sizeIndex))) + " Byte");


        //删除文件
//        int n = getContentResolver().delete(uri, null, null);
//        System.out.println(n + " file has been deleted");


    }


    public void write(View view) {
        System.out.println("~~button.write~~");

        try {
            String file = "logs/sql" + new Random().nextInt(100) + ".log";
            Path path = Paths.get(getFilesDir().toString(), file);
            System.out.println("path is " + path);
            System.out.println("parent is " + path.getParent());

            if (!Files.exists(path.getParent())) Files.createDirectories(path.getParent());
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path, UTF_8,
                    CREATE, APPEND);
            String sql = "SELECLT * FROM Car WHERE ROWID = " + new Random().nextInt(99) + ";";
            bufferedWriter.write(sql);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void list(View view) {
        System.out.println("~~button.list~~");

//        listDir();
        walkDir();
    }

    private void listDir() {
        Path path = getFilesDir().toPath();

        try {
            if (Files.exists(path.getParent())) {
                Stream<Path> pathStream = Files.list(path);

                System.out.println("--" + path.getParent().getFileName() + "--");
                pathStream.forEach(System.out::println);

            } else {
                System.out.println("empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void walkDir() {

        //遍历目录树
        try {

            Path path = getFilesDir().toPath();
            Files.walkFileTree(path, new SimpleFileVisitor() {
                @Override
                public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
                    Path parent = Paths.get(getFilesDir().toString());
                    Path relative = parent.relativize((Path) dir);

                    System.out.println("relative path is " + relative);
                    return super.preVisitDirectory(dir, attrs);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void read(View view) {
        System.out.println("~~button.read~~");

        //方法一：读取文件，使用BufferedReader
//        try {
//
//            Path path = Paths.get(getFilesDir().toString(), "logs", "sql.log");
//            System.out.println("path is " + path);
//
//            BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
//
//            StringBuilder stringBuilder = new StringBuilder(128);
//            CharBuffer buffer = CharBuffer.allocate(1024);
//            while (bufferedReader.read(buffer) != -1) {
//                buffer.flip();
//                while (buffer.hasRemaining()) {
//                    stringBuilder.append(buffer.get());
//                }
//                buffer.clear();
//            }
//
//            bufferedReader.close();
//
//            System.out.println(stringBuilder.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //方法二：读取文件，使用Stream对象
        try {
            Path path = Paths.get(getFilesDir().toString(), "logs/sql.log");

            if (Files.exists(path)) {
                Stream<String> stringStream = Files.lines(path);
                stringStream.forEach(System.out::println);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void bind(View view) {
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

        Intent intent = new Intent("getURI");
        intent.setPackage(getPackageName());
        intent.setType("log/sql");

        startActivityForResult(intent, 333);

    }


    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        //最简实例，返回文件映射
//        File file = new File(getFilesDir() + "/logs/sql.log");
//        Uri contentUri = FileProvider.getUriForFile(this, "TNT", file);
//        System.out.println(file + " -> " + contentUri);


        //遍历目录，返回所以文件映射
        Path logsDir = Paths.get(getFilesDir().toString(), "logs");
        System.out.println("dir is " + logsDir);

        try (Stream<Path> pathStream = Files.list(logsDir)) {
            pathStream.forEach(path -> {
                Uri contentUri = FileProvider.getUriForFile(this, "TNT", path.toFile());
                System.out.println(path + " -> " + contentUri);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
