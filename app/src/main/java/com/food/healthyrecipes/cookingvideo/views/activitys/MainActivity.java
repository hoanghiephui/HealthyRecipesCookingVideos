package com.food.healthyrecipes.cookingvideo.views.activitys;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.food.healthyrecipes.cookingvideo.DataBanner;
import com.food.healthyrecipes.cookingvideo.R;
import com.food.healthyrecipes.cookingvideo.views.base.BaseSubActivity;
import com.food.healthyrecipes.cookingvideo.views.widget.ImageHolderView;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.holder.ViewHolderCreator;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseSubActivity
        implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener, AppBarLayout.OnOffsetChangedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.content)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.bannerUrl)
    MaterialBanner banner;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.search_container)
    CardView searchContainer;

    private List<DataBanner> dataBanners = new ArrayList<>();
    private TransitionSet set;
    private int[] images = {R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner
    };

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        drawerLayout.addDrawerListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        appBarLayout.addOnOffsetChangedListener(this);
        for (int image : images) {
            DataBanner dataBanner = new DataBanner();
            dataBanner.setUrl(image);
            dataBanners.add(dataBanner);
        }

        banner.setPages(
                new ViewHolderCreator<ImageHolderView>() {
                    @Override
                    public ImageHolderView createHolder() {
                        return new ImageHolderView();
                    }
                }, dataBanners);
        banner.setIndicatorInside(true);
        banner.startTurning(3000);
        set = new TransitionSet();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (mCoordinatorLayout != null) {
            mCoordinatorLayout.setX(slideOffset * drawerView.getWidth());
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                set
                .addTransition(new Scale(0.7f))
                .addTransition(new Fade())
                .setInterpolator(verticalOffset == 0 ? new LinearOutSlowInInterpolator() :
                        new FastOutLinearInInterpolator());
        TransitionManager.beginDelayedTransition(searchContainer, set);
        //Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        searchContainer.setVisibility(verticalOffset == 0 ? View.VISIBLE : View.GONE);
        //searchContainer.startAnimation(slideUp);
    }
}
