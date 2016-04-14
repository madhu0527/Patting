package mobile.patting.UI;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.mobile.patting.R;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.Stack;

import mobile.patting.Fragments.HomeFrag;
import mobile.patting.Fragments.LeftNavigationDrawerFragment;
import mobile.patting.Fragments.RightNavigationDrawerFragment;
import mobile.patting.Pojo.PetsPojo;

public class MainActivity extends AppCompatActivity {
    private static Stack<android.support.v4.app.Fragment> mFragmentStack = null;
    public boolean isDrawerLocked = false;
    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    DrawerLayout mLDrawerLayout,mRDrawerLayout;
    FrameLayout mFrame;
    PetsPojo petPojo;
    private ActionBarDrawerToggle mActionbarToggle = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrame=(FrameLayout)findViewById(R.id.layout_frame_content);
        initToolbar();
        setUpDrawer();
        replaceFragment(new HomeFrag());
    }

    private void setUpDrawer() {
        mLDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);        // mDrawerLayout object Assigned to the view
        mRDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mLDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        if (((ViewGroup.MarginLayoutParams) findViewById(R.id.navigation_drawer).getLayoutParams()).leftMargin == (int) getResources().getDimension(R.dimen.drawer_width)) {
            mLDrawerLayout.setScrimColor(Color.TRANSPARENT);
            isDrawerLocked = true;
        }
        if (((ViewGroup.MarginLayoutParams) findViewById(R.id.right_navigation_drawer).getLayoutParams()).leftMargin == (int) getResources().getDimension(R.dimen.drawer_width)) {
            mRDrawerLayout.setScrimColor(Color.TRANSPARENT);
        }
        LeftNavigationDrawerFragment drawerFragment = (LeftNavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mActionbarToggle = drawerFragment.setUp(R.id.navigation_drawer, mLDrawerLayout, toolbar);

    }

    public void addFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.layout_frame_content, fragment);
        mFragmentStack.lastElement().onPause();
        transaction.hide(mFragmentStack.lastElement());
        mFragmentStack.push(fragment);
        transaction.commit();
    }

    public void replaceFragment(android.support.v4.app.Fragment fragment) {
        if (mFragmentStack != null) {
            Log.e("Size", mFragmentStack.size() + "");
        }
        mFragmentStack = new Stack<>();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layout_frame_content, fragment);
        mFragmentStack.push(fragment);
        transaction.commit();
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
    }
  /*  public void showHideToolbar(boolean scroll){
        if(scroll){
            toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        }else {
            toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //    mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
        }
    }*/
    public void hideViews() {
        /*FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mFrame.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        mFrame.setLayoutParams(params);*/
       // mFrame.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.MarginLayoutParams.MATCH_PARENT,FrameLayout.MarginLayoutParams.MATCH_PARENT));
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

      //  FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
       // int fabBottomMargin = lp.bottomMargin;
       // mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    public void showViews() {

        /*FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mFrame.getLayoutParams();
        params.setMargins(0, 60, 0, 0);
        mFrame.setLayoutParams(params);*/
       // mFrame.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.MarginLayoutParams.MATCH_PARENT,60));
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
     //   mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }
    public void changeToolbarTitle(String title){
        toolbar.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        if (mLDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mLDrawerLayout.closeDrawers();
        } else {
            onBackPress();
        }
    }

    public void setHomeAsEnabled(boolean status) {
        if (status) {
            mActionbarToggle.setDrawerIndicatorEnabled(status);
        } else mActionbarToggle.setDrawerIndicatorEnabled(status);
    }

    public void onBackPress() {
        if (mFragmentStack.size() >= 2) {
            FragmentTransaction ft = fm.beginTransaction();
            mFragmentStack.lastElement().onPause();
            ft.remove(mFragmentStack.pop());
            mFragmentStack.lastElement().onResume();
            ft.show(mFragmentStack.lastElement());
            ft.commit();
        } else {
            super.onBackPressed();
        }
    }

    public void manageDrawerLayout(boolean status) {
        if (mLDrawerLayout != null) {
            if (status) {
                mLDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            } else {
                mLDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    }
    public void openRightDrawer(boolean status){
        if (mRDrawerLayout != null) {
            if (status) {
                mRDrawerLayout.openDrawer(Gravity.RIGHT);
            } else {
                mRDrawerLayout.closeDrawer(Gravity.RIGHT);
            }
        }
    }
    public void passData(PetsPojo pojo){
        petPojo=pojo;
    }
    public PetsPojo getData(){
        return  petPojo;
    }

}
