package com.example.arvin.myzhihudaily2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.arvin.myzhihudaily2.Adapter.NewsListAdapter;
import com.example.arvin.myzhihudaily2.AsyncTasks.AsyncLoadNewsList;
import com.example.arvin.myzhihudaily2.AsyncTasks.AsyncLoadThemeList;
import com.example.arvin.myzhihudaily2.AsyncTasks.AsyncLoadTopNews;
import com.example.arvin.myzhihudaily2.Models.News;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    ViewPager topViewPager;
    CircleIndicator indicator;
    NewsListAdapter adapter = null;
    private int chosenState = 1;
    Toolbar toolbar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        topViewPager = (ViewPager) findViewById(R.id.top_viewPager);
        indicator = (CircleIndicator) findViewById(R.id.top_indicator);




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new NewsListAdapter(MainActivity.this,R.layout.list_view);
        new AsyncLoadNewsList(adapter).execute();


        new AsyncLoadTopNews(getLayoutInflater(),topViewPager,indicator).execute();

        autoBanner();


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = adapter.getItem(position);

                System.out.println(news.getId());
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("id",  news.getId());
                intent.putExtra("title",news.getTitle());
                intent.putExtra("image",news.getImage());
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (chosenState)
                {
                    case 1:
                        new AsyncLoadNewsList(adapter).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 13:
                        new AsyncLoadThemeList(adapter,13).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 12:
                        new AsyncLoadThemeList(adapter,12).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 3:
                        new AsyncLoadThemeList(adapter,3).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 11:
                        new AsyncLoadThemeList(adapter,11).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 4:
                        new AsyncLoadThemeList(adapter,4).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 5:
                        new AsyncLoadThemeList(adapter,5).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 6:
                        new AsyncLoadThemeList(adapter,6).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 10:
                        new AsyncLoadThemeList(adapter,10).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 2:
                        new AsyncLoadThemeList(adapter,2).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 7:
                        new AsyncLoadThemeList(adapter,7).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 9:
                        new AsyncLoadThemeList(adapter,9).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    case 8:
                        new AsyncLoadThemeList(adapter,8).execute();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    default:
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                }

            }
        });
        swipeRefreshLayout.setBackgroundColor(Color.WHITE);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,FavActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                chosenState = 1;
                System.out.println("Welcome Home");
                new AsyncLoadNewsList(adapter).execute();
                toolbar.setTitle("知乎日报");
                break;
            case R.id.nav_xinli:
                chosenState = 13;
                System.out.println("日常心理");
                toolbar.setTitle("日常心理");
                new AsyncLoadThemeList(adapter,13).execute();

                break;
            case R.id.nav_yonghu:
                chosenState = 12;
                System.out.println("用户推荐");
                toolbar.setTitle("用户推荐");
                new AsyncLoadThemeList(adapter,12).execute();
                break;
            case R.id.nav_move:
                chosenState = 3;
                System.out.println("电影");
                toolbar.setTitle("电影日报");
                new AsyncLoadThemeList(adapter,3).execute();
                break;
            case R.id.nav_wuliao:
                chosenState = 11;
                System.out.println("不许无聊");
                toolbar.setTitle("不许无聊");
                new AsyncLoadThemeList(adapter,11).execute();
                break;
            case R.id.nav_sheji:
                chosenState = 4;
                System.out.println("设计");
                toolbar.setTitle("设计日报");
                new AsyncLoadThemeList(adapter,4).execute();
                break;
            case R.id.nav_gongsi:
                chosenState = 5;
                System.out.println("大公司");
                toolbar.setTitle("大公司日报");
                new AsyncLoadThemeList(adapter,5).execute();
                break;
            case R.id.nav_caijing:
                chosenState = 6;
                System.out.println("财经");
                toolbar.setTitle("财经日报");
                new AsyncLoadThemeList(adapter,6).execute();
                break;
            case R.id.nav_hulianwang:
                chosenState = 10;
                System.out.println("网络安全");
                toolbar.setTitle("网络安全");
                new AsyncLoadThemeList(adapter,10).execute();
                break;
            case R.id.nav_game:
                chosenState = 2;
                System.out.println("游戏");
                toolbar.setTitle("游戏日报");
                new AsyncLoadThemeList(adapter,2).execute();
                break;
            case R.id.nav_music:
                chosenState = 7;
                System.out.println("音乐");
                toolbar.setTitle("音乐日报");
                new AsyncLoadThemeList(adapter,7).execute();
                break;
            case R.id.nav_dongman:
                chosenState = 9;
                System.out.println("动漫");
                toolbar.setTitle("动漫日报");
                new AsyncLoadThemeList(adapter,9).execute();
                break;
            case R.id.nav_tiyu:
                chosenState = 8;
                System.out.println("体育");
                toolbar.setTitle("体育日报");
                new AsyncLoadThemeList(adapter,8).execute();
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    } public void autoBanner(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int index = topViewPager.getCurrentItem();
                        index = (1 + index) % 5;
                        topViewPager.setCurrentItem(index,true);
                    }
                });
            }
        };
        timer.schedule(timerTask, 5000, 5500);
    }
}
