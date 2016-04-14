package mobile.patting.Adapters;

import android.content.Context;
import android.graphics.Movie;
import android.mobile.patting.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.patting.Fragments.PetDetail;
import mobile.patting.Fragments.PetsListFragment;
import mobile.patting.Pojo.PetsPojo;
import mobile.patting.UI.MainActivity;
import mobile.patting.Utils.Constants;

/**
 * Created by goodworklabs on 31/03/2016.
 */
public class PetsListAdapter extends RecyclerView.Adapter<PetsListAdapter.MyViewHolder> {
    List<PetsPojo> petsPojoList;
    Context mContext;
    Constants utils=new Constants();
    public PetsListAdapter(List<PetsPojo> petsList,Context context) {
        this.petsPojoList = petsList;
        mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pets_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PetsListAdapter.MyViewHolder holder, final int position) {
        // PetsPojo pets = petsPojoList.get(position);
        holder.petTitleTxt.setText(petsPojoList.get(position).getPetNameTitle());
         holder.petPriceTxt.setText("\u20B9 "+petsPojoList.get(position).getPetPrice());
        holder.createdTimelocationTxt.setText(petsPojoList.get(position).getCity());
        holder.petBreedTxt.setText(petsPojoList.get(position).getPetBreed());
        String photos=petsPojoList.get(position).getPhotos().replaceAll("\\\\-\\\\"," ");
        Log.d("PHOTOS", photos);
        try {
            JSONArray arrayPhotos=new JSONArray(photos);
            for (int i=0;i<arrayPhotos.length();i++){
                Log.d("SINGLE PIC", arrayPhotos.get(i).toString());
                Glide.with(mContext)
                        .load(arrayPhotos.get(i))
                        .into(holder.petImage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.detailLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).passData(petsPojoList.get(position));
                ((MainActivity) mContext).addFragment(new PetDetail());
            }
        });
        holder.petImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return petsPojoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView petTitleTxt, petPriceTxt, petBreedTxt, createdTimelocationTxt;
        public ImageView petImage;
        RelativeLayout detailLay;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            petTitleTxt = (TextView) view.findViewById(R.id.pet_title);
            petPriceTxt = (TextView) view.findViewById(R.id.priceTxt);
            petBreedTxt = (TextView) view.findViewById(R.id.petCate);
            createdTimelocationTxt = (TextView) view.findViewById(R.id.location);
            petImage = (ImageView) view.findViewById(R.id.petimageView);
            detailLay=(RelativeLayout)view.findViewById(R.id.detailLay);
            petTitleTxt.setTypeface(utils.setfontRegular(mContext));
            petPriceTxt.setTypeface(utils.setfontRegular(mContext));
            petBreedTxt.setTypeface(utils.setfontRegular(mContext));
        }
    }
}
