package mobile.patting.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.mobile.patting.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import mobile.patting.Design.CirclePageIndicator;
import mobile.patting.Pojo.PetsPojo;
import mobile.patting.UI.MainActivity;
import mobile.patting.Utils.Constants;

public class PetDetail extends Fragment {
    View v;
    Context context;
    PetsPojo pojo;
    @Bind(R.id.cost)
    TextView costTxt;
    @Bind(R.id.titlePet)
    TextView titlePet;
    @Bind(R.id.timeCreatedBy)
    TextView timeCreatedByTxt;
    @Bind(R.id.discrptionPet)
    TextView discrptionPet;
    JSONArray arrayPhotos;
    Constants utils=new Constants();
    public PetDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.pet_detail, container, false);
        ButterKnife.bind(this, v);
        ViewPager mViewPager = (ViewPager) v.findViewById(R.id.pagerr);
        CirclePageIndicator mCircularIndicator = (CirclePageIndicator) v.findViewById(R.id.mindicator);
        mCircularIndicator.setFillColor(Color.parseColor("#70c1b3"));
        mCircularIndicator.setPageColor(Color.parseColor("#999999"));

        context = getActivity();
        pojo = ((MainActivity) context).getData();
        String photos = pojo.getPhotos().replaceAll("\\\\-\\\\", " ");
        Log.d("PHOTOS", photos);
        try {
            arrayPhotos = new JSONArray(photos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mViewPager.setAdapter(new PetImgAdapter(getChildFragmentManager(), getActivity()));
        mCircularIndicator.setViewPager(mViewPager);

        costTxt.setTypeface(utils.setfontRegular(getActivity()));
        titlePet.setTypeface(utils.setfontSemiBold(getActivity()));
        titlePet.setTypeface(utils.setfontRegular(getActivity()));
        Log.d("NAME", pojo.getPetNameTitle());
        costTxt.setText("\u20B9 "+pojo.getPetPrice());
        titlePet.setText(pojo.getPetNameTitle());
        discrptionPet.setText(pojo.getPetBrfDesc());
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        ((MainActivity) context).setHomeAsEnabled(false);
        ((MainActivity) context).manageDrawerLayout(false);
        ((MainActivity) context).changeToolbarTitle("Pet Detail");
    }

    public class PetImgAdapter extends PagerAdapter {
        Context context;
        LayoutInflater inflater;

        public PetImgAdapter(FragmentManager fm, Context mContext) {
            context = mContext;
        }

        @Override
        public int getCount() {
            return arrayPhotos.length();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == (View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View itemView = inflater.inflate(R.layout.pet_detail_imgs_row, null);
            ImageView petImage = (ImageView) itemView.findViewById(R.id.discImg);
            try {
                Log.d("SINGLE PIC DETAIL", arrayPhotos.get(position).toString());
                Glide.with(context)
                        .load(arrayPhotos.get(position))
                        .into(petImage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
