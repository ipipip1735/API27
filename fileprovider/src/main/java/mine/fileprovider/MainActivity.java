package mine.fileprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

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
            System.out.println("path is " + path);

            System.out.println("parent is " + path.getParent());
            Files.createDirectories(path.getParent());

            BufferedWriter bufferedWriter =
                    Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                            CREATE, APPEND);
            String sql = "SELECT * FROM Car;";
            bufferedWriter.write(sql);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void read(View view) {
        System.out.println("~~button.read~~");


        //遍历打印Files目录下的所有目录
//        try {
//
//            Path path = Paths.get(getFilesDir().toString());
//            Files.walkFileTree(path, new SimpleFileVisitor() {
//                @Override
//                public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
//                    Path parent = Paths.get(getFilesDir().toString());
//                    Path relative = parent.relativize((Path) dir);
//
//                    System.out.println("relative path is " + relative);
//                    return super.preVisitDirectory(dir, attrs);
//                }
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        //方法一：读取文件，使用BufferedReader
//        try {
//
//            Path path = Paths.get(getFilesDir().toString(), "Logs", "sql.log");
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

        //方法e二：读取文件，使用Stream对象
        try {

            Path path = Paths.get(getFilesDir().toString(), "Logs", "sql.log");
            System.out.println("path is " + path);
            Stream<String> stringStream = Files.lines(path);
            stringStream.forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

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

    }
}
