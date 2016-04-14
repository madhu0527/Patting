package mobile.patting.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.mobile.patting.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;

/**
 * Created by goodworklabs on 26/03/2016.
 */
public class PetsFrag extends Fragment {
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.pets_list, container, false);
        return v;
    }
}
