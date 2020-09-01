package mine.drawerlayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NavigationUIActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");


        //方式一：使用ActionBar
//        setContentView(R.layout.activity_actionbar);
//        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        //方式二：使用toolbar
//        setContentView(R.layout.activity_only_toolbar);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);


        //方式三：使用CollapsingToolbarLayout
//        setContentView(R.layout.activity_collapsing_toolbar);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
//        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
//        CollapsingToolbarLayout layout = findViewById(R.id.collapsing_toolbar_layout);
////        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
//                .setOpenableLayout(drawerLayout)//设置DrawerLayout对象（系统将自动增加ActionBarDrawerToggle）
//                .build();
//        NavigationUI.setupWithNavController(layout, toolbar, navController, appBarConfiguration);





        //方式四：使用NavigationView
//        setContentView(R.layout.activity_navigation_views);
//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        DrawerLayout drawerLayout = findViewById(R.id.dl);
//        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
//        NavigationUI.setupWithNavController(navigationView, navController);
//                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                System.out.println("~~onNavigationItemSelected~~");
//                System.out.println("item is " + item);
//                NavController navController = Navigation.findNavController(NavigationUIActivity.this, R.id.fragmentNav);
//                return NavigationUI.onNavDestinationSelected(item, navController);
//            }
//        });


        //方式五：使用BottomNavigationView
//        setContentView(R.layout.activity_bottom_navigation_views);
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
//        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);




        //方式六：使用ActionBarDrawerToggle
//        setContentView(R.layout.activity_action_bar_drawer_toggle);
//        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateOptionsMenu  *********");
        getMenuInflater().inflate(R.menu.menu_one, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreateOptionsMenu  *********");

        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSupportNavigateUp  *********");

        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
        navController.navigateUp();

        boolean b = super.onSupportNavigateUp();
        System.out.println(b);
        return true;
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


    public void start(View view) {
        System.out.println("~~button.start~~");
        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
        navController.navigate(R.id.action_oneFragment_to_twoFragment);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
        NavController navController = Navigation.findNavController(this, R.id.fragmentNav);
        navController.navigate(R.id.action_twoFragment_to_oneFragment);
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
