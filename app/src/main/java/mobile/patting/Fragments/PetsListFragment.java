package mobile.patting.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.mobile.patting.R;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;
import mobile.patting.Adapters.PetsListAdapter;
import mobile.patting.Design.HidingScrollListener;
import mobile.patting.Design.MaterialProgressBar;
import mobile.patting.Interface.HttpInterface;
import mobile.patting.Pojo.PetsPojo;
import mobile.patting.UI.MainActivity;
import mobile.patting.Utils.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PetsListFragment extends Fragment  {
    View v;
    @Bind(R.id.petslist)
    RecyclerView recyclerView;
    Context context;
    @Bind(R.id.filterFab)
    FloatingActionButton fab;
    @Bind(R.id.progressBar)
    MaterialProgressBar progressBar;
    @Bind(R.id.mainLayout)
    RelativeLayout mainLayout;
    Constants utils = new Constants();
    public PetsListFragment() {
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
        v = inflater.inflate(R.layout.pets_list, container, false);
        ButterKnife.bind(this, v);
        context = getActivity();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        context = getActivity();
        ((MainActivity) context).changeToolbarTitle("Pets");
        callPetsList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).openRightDrawer(true);
            }
        });
        /*recyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                ((MainActivity) context).showHideToolbar(true);
                 mainLayout.setPadding(0, 0, 0, 0);
            }

            @Override
            public void onShow() {
                ((MainActivity) context).showHideToolbar(false);
            }
        });*/
        return v;
    }


    private void callPetsList() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();
        HttpInterface api = adapter.create(HttpInterface.class);
        api.getPetsList(new Callback<List<PetsPojo>>() {
            @Override
            public void success(List<PetsPojo> petsPojos, Response response) {
                System.out.println(response);
                PetsListAdapter adapterPets = new PetsListAdapter(petsPojos, context);
                recyclerView.setAdapter(adapterPets);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("GET ERROR", String.valueOf(error));
                utils.showMsg("Oops! Something went wrong!", mainLayout);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        ((MainActivity) context).setHomeAsEnabled(false);
        ((MainActivity) context).manageDrawerLayout(false);
        ((MainActivity) context).changeToolbarTitle("Pets");
    }

}
