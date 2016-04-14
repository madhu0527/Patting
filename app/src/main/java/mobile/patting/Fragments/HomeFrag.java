package mobile.patting.Fragments;

import android.content.Context;
import android.mobile.patting.R;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import mobile.patting.Adapters.CustomGridAdapter;
import mobile.patting.UI.MainActivity;

/**
 * Created by goodworklabs on 22/03/2016.
 */
public class HomeFrag extends Fragment {
    public static String[] namesList = {"Adopt a pet", "Around Me",
            "Find a Shelter", "Lost & Found", "News & Blogs", "Favorites", "Report Abuse"};
    public static int[] imagesList = {R.drawable.adopt_a_pet,
            R.drawable.around_me, R.drawable.find_a_shelter,
            R.drawable.lostfound, R.drawable.news_blogs,
            R.drawable.favorites, R.drawable.report_abuse};
    GridView gv;
    FloatingActionButton fab;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.home_frag, container, false);
        mContext=getActivity();
        gv = (GridView) v.findViewById(R.id.grid);
        fab = (FloatingActionButton) v.findViewById(R.id.postAd);
        gv.setAdapter(new CustomGridAdapter(getActivity(), namesList, imagesList));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addFragment(new PostAdFrag());
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position == 0) {
                    ((MainActivity) getActivity()).addFragment(new PetsListFragment());
                } else if (position == 1) {
                    ((MainActivity) getActivity()).addFragment(new MapFragment());
                } else if (position == 2) {
                    // ((MainActivity) getActivity()).addFragment(new PostAdFrag());
                } else if (position == 3) {
                    // ((MainActivity) getActivity()).addFragment(new PostAdFrag());
                } else if (position == 4) {
                   ((MainActivity) getActivity()).addFragment(new NewsBlogs());
                } else if (position == 5) {
                    // ((MainActivity) getActivity()).addFragment(new PostAdFrag());
                } else if (position == 6) {
                    // ((MainActivity) getActivity()).addFragment(new PostAdFrag());
                } else if (position == 7) {
                    // ((MainActivity) getActivity()).addFragment(new PostAdFrag());
                } else if (position == 8) {
                    // ((MainActivity) getActivity()).addFragment(new PostAdFrag());
                } else if (position == 9) {
                    // ((MainActivity) getActivity()).addFragment(new PostAdFrag());
                }
            }
        });

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        Log.d("home fragment", "onResume");
        ((MainActivity)mContext).setHomeAsEnabled(true);
        ((MainActivity)mContext).manageDrawerLayout(true);
        ((MainActivity) mContext).changeToolbarTitle("Home");
    }
}