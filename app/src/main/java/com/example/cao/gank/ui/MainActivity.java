package com.example.cao.gank.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cao.gank.R;
import com.example.cao.gank.adapter.MainAdapter;
import com.example.cao.gank.congiure.GankUrl;
import com.example.cao.gank.fragment.HomeFragment;
import com.example.cao.gank.fragment.MineFragment;
import com.example.cao.gank.fragment.ReadCopyFragment;
import com.example.cao.gank.fragment.ReadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ViewPager viewpager;
    private FragmentManager manager;
    private List<Fragment> mlist = new ArrayList<>();
    private Context context;
    private MainAdapter mainAdapter;
    private BottomNavigationView bottom;
    private NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        bottom.setSelectedItemId(R.id.menu_Read);
    }

    private void initListener() {
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawers();
                switch (item.getItemId()){
                    case R.id.menu_Read:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_home:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_Me:
                        viewpager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottom.setSelectedItemId(R.id.menu_home);
                        break;
                    case 1:
                        bottom.setSelectedItemId(R.id.menu_Read);
                        break;
                    case 2:
                        bottom.setSelectedItemId(R.id.menu_Me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_draw_client:
                        Intent intent = new Intent(context, CateActivity.class);
                        intent.putExtra("cate", GankUrl.CATEGORY_CLIENT);
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.menu_draw_app:
                        Intent intent2 = new Intent(context, CateActivity.class);
                        intent2.putExtra("cate", GankUrl.CATEGORY_APP);
                        MainActivity.this.startActivity(intent2);
                        break;
                    case R.id.menu_draw_expandresource:
                        Intent intent3 = new Intent(context, CateActivity.class);
                        intent3.putExtra("cate", GankUrl.CATEGORY_EXPANDRESOURCE);
                        MainActivity.this.startActivity(intent3);
                        break;
                    case R.id.menu_draw_recommend:
                        Intent intent4 = new Intent(context, CateActivity.class);
                        intent4.putExtra("cate", GankUrl.CATEGROY_RECOMMEND);
                        MainActivity.this.startActivity(intent4);
                        break;
                    case R.id.menu_draw_video:
                        Intent intent5 = new Intent(context, CateActivity.class);
                        intent5.putExtra("cate", GankUrl.CATEGORY_VIDEO);
                        MainActivity.this.startActivity(intent5);
                        break;
                    case R.id.menu_draw_theme:

                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        context = getApplicationContext();
        navigation = (NavigationView) findViewById(R.id.id_main_navigation);
        bottom = (BottomNavigationView) findViewById(R.id.id_main_bottom);
        manager = getSupportFragmentManager();
        drawer = (DrawerLayout) findViewById(R.id.id_main_drawer);
        toolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        viewpager = (ViewPager) findViewById(R.id.id_main_viewpager);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);

            supportActionBar.setHomeAsUpIndicator(R.drawable.menu_action_back);
        }

        initdata();
        mainAdapter = new MainAdapter(manager,mlist,context);
        viewpager.setAdapter(mainAdapter);
    }

    private void initdata() {
        HomeFragment homeFragment = new HomeFragment();
        MineFragment mineFragment = new MineFragment();
        ReadFragment readFragment = new ReadFragment();
        //注意顺序,可能要调整viewpager的适配器
        //不然返回第一个时fragement被销毁
//        mlist.add(homeFragment);
//        mlist.add(new ReadCopyFragment());
//        mlist.add(mineFragment);
        mlist.add(new ReadCopyFragment());
        mlist.add(homeFragment);
        mlist.add(mineFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_edit:
                break;
            case R.id.menu_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                drawer.openDrawer(Gravity.START);
        }
        return true;
    }

    //按返回键时关闭侧滑菜单
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.START)){
            drawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

}
