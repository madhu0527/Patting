package mobile.patting.Fragments;

import android.content.Context;
import android.mobile.patting.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import mobile.patting.UI.MainActivity;

/**
 * Created by goodworklabs on 12/04/2016.
 */
public class NewsBlogs extends Fragment {
    @Bind(R.id.webview)
    WebView webView;
    Context mContext;
    public NewsBlogs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.news_blogs, container,
                false);
        mContext=getActivity();
        ButterKnife.bind(this, v);
        webView.loadUrl("http://www.thebetterindia.com/topics/animal-welfare/");
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        ((MainActivity) mContext).setHomeAsEnabled(false);
        ((MainActivity) mContext).manageDrawerLayout(false);
        ((MainActivity) mContext).changeToolbarTitle("Pets News");
    }
}
