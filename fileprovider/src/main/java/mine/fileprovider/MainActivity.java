package mine.fileprovider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
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
            System.out.println("path is " + path);

            System.out.println("parent is " + path.getParent());
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


        //方法二：读取文件，使用Stream对象
        try {
            Path path = Paths.get(getFilesDir().toString(), "Logs/sql.log");

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
