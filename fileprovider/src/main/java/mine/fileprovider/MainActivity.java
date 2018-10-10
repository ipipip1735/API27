package mine.fileprovider;

import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

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


    public void write(View view) {
        System.out.println("~~button.write~~");


        try {
            Path path = Paths.get(getFilesDir().toString(), "Logs/sql.log");

            if (!Files.exists(path.getParent())) Files.createDirectories(path.getParent());

            BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
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


        walkDir();
        listDirAndPrint();
    }

    private void walkDir() {
        Path path = Paths.get(getFilesDir().toString(), "Logs/sql.log");


    }

    private void listDirAndPrint() {
        try {
            Path path = Paths.get(getFilesDir().toString(), "Logs/sql.log");

            if (Files.exists(path.getParent())) {
                Stream<Path> pathStream = Files.list(path.getParent());
                System.out.println("--" + path.getParent().getFileName() + "--");
                pathStream.forEach(dir -> {
                    System.out.println(dir);
                });
                System.out.println("----------");

                if (Files.exists(path)) {
                    Stream<String> stringStream = Files.lines(path);
                    stringStream.forEach(System.out::println);
                }
            } else {
                System.out.println("empty");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(View view) {
        System.out.println("~~button.bind~~");

//        File file = new File(getFilesDir(), "logs/first");
//        file.mkdirs();
//
//        file = new File(file, "one.log");
//        System.out.println(file);
//
//        try {
//            FileOutputStream outputStream = new FileOutputStream(file, true);
//            String sql = "[SQL]select * from Car;";
//            outputStream.write(sql.getBytes());
//            outputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

//        File log = new File(getFilesDir(), "logs/first/one.log");
//        System.out.println("File is " + log);
//
//        Uri contentUri = FileProvider.getUriForFile(this, "TNT", log);
//        System.out.println(contentUri);

    }

}
