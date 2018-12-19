package com.example.ritwik.wallify;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //starting of navigation detail...................................................
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Setting toolbar...............................


        //Setting Text in actionbar...............................


//Fragment Operation .........................................................................

        getSupportActionBar().setTitle("Category");
        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add fragment here
        adapter.AddFragment(new FragmentCategory(), " ");
        adapter.AddFragment(new FragmentRecent(), " ");
        adapter.AddFragment(new FragmentPopular(), " ");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        //Setting icons on tab

        tabLayout.getTabAt(0).setIcon(R.drawable.category_selected);
        tabLayout.getTabAt(1).setIcon(R.drawable.recent);
        tabLayout.getTabAt(2).setIcon(R.drawable.popular);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                String[] title = {"Category", "Recent", "Popular"};

                //Here we update default icons by coloured one
                updateColouredIcons(tab.getPosition());

                //We set title of ctionbar accordingly
                toolbar.setTitle(title[tab.getPosition()]);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //Here we update coloured icons by default one
                updateDefaultIcons(tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    //For all Navigation Drawer
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
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_favourites) {


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateColouredIcons(int position) {

        switch (position) {

            case 0:
                tabLayout.getTabAt(0).setIcon(R.drawable.category_selected);
                break;

            case 1:
                tabLayout.getTabAt(1).setIcon(R.drawable.recent_selected);
                break;

            case 2:
                tabLayout.getTabAt(2).setIcon(R.drawable.popular_selected);
                break;

            default:
                break;
        }

    }

    public void updateDefaultIcons(int position) {

        switch (position) {

            case 0:
                tabLayout.getTabAt(0).setIcon(R.drawable.category);
                break;

            case 1:
                tabLayout.getTabAt(1).setIcon(R.drawable.recent);
                break;

            case 2:
                tabLayout.getTabAt(2).setIcon(R.drawable.popular);
                break;

            default:
                break;
        }

    }
}
