package mobile.patting.Fragments;

/**
 * Created by goodworklabs on 04/04/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.mobile.patting.R;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobile.patting.UI.MainActivity;

/**
 * Created by goodworklabs on 26/03/2016.
 */
public class PetsFragTabs extends Fragment {
    FragmentManager fragManager;
    Context mContext;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_favourite,
            R.drawable.ic_tab_call,
    };
    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.pets_tabs, container, false);
        mContext=getActivity();
        tabLayout = (TabLayout)v.findViewById(R.id.tabs);
        fragManager = myContext.getSupportFragmentManager();
        viewPager = (ViewPager)v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return v;
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragManager);
        adapter.addFrag(new PetsListFragment(), "PETS");
        adapter.addFrag(new MapFragment(), "MAP");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        ((MainActivity)mContext).setHomeAsEnabled(false);
        ((MainActivity)mContext).manageDrawerLayout(false);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

