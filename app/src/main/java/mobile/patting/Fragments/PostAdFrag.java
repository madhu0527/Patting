package mobile.patting.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.mobile.patting.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.utils.ObjectUtils;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.ganfra.materialspinner.MaterialSpinner;
import mobile.patting.Design.MaterialProgressBar;
import mobile.patting.Interface.HttpInterface;
import mobile.patting.Pojo.PetsPojo;
import mobile.patting.UI.MainActivity;
import mobile.patting.Utils.Aplication;
import mobile.patting.Utils.Constants;
import mobile.patting.Utils.CustomGallery;
import mobile.patting.Utils.CustomImgGalleryActivity;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by goodworklabs on 23/03/2016.
 */
public class PostAdFrag extends Fragment {
    byte[] bytes;
    String fileName, ext, petType = "", petName = "", petDesc = "", petCost = "", petBrfDes = "", petCate = "", petBreed = "",
            petSize = "", petGender = "", petAge = "", state = "", city = "",location="";
    JSONArray eventImages = new JSONArray();
    File filee;
    String[] all_path;
    View v;
    @Bind(R.id.selectedMedia)
    TextView selectedMediaTxt;
    Context context;
    @Bind(R.id.petsTypeSpnr)
    MaterialSpinner spinnerPets;
    @Bind(R.id.cateSpnr)
    MaterialSpinner spinnerCate;
    @Bind(R.id.breedSpnr)
    MaterialSpinner spinnerBreed;
    @Bind(R.id.sizeSpnr)
    MaterialSpinner spinnerSize;
    @Bind(R.id.genderSpnr)
    MaterialSpinner spinnerGender;
    @Bind(R.id.ageSpnr)
    MaterialSpinner spinnerAge;
    @Bind(R.id.stateSpnr)
    MaterialSpinner spinnerState;
    @Bind(R.id.citySpnr)
    MaterialSpinner spinnerCity;
    ArrayAdapter<String> adapterBreed;
    @Bind({R.id.nameEdit, R.id.shortDesEdit, R.id.brfDescEdit, R.id.editCost,R.id.locationEdit})
    List<EditText> editTextViews;
    HashMap<String, HashMap<String, String>> mapPet = new HashMap<String, HashMap<String, String>>();
    @Bind(R.id.coordinatorLayout)
    RelativeLayout mainLayout;
    @Bind(R.id.progressBar)
    MaterialProgressBar progressBar;
    Constants utils = new Constants();
    @Bind(R.id.scrollPost)
    ScrollView scrollPost;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(
                R.layout.postad_frag, container, false);
        ButterKnife.bind(this, v);
        getLocalContextEnableBackPressedEnableMenu();
        initSpinner();
        utils.setStrictMode();
        SpinonClickListerners();
        return v;
    }

    private void getLocalContextEnableBackPressedEnableMenu() {
        context = getActivity();
        ((MainActivity) context).setHomeAsEnabled(true);
        setHasOptionsMenu(true);
    }

    private void SpinonClickListerners() {

        spinnerPets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petType = String.valueOf(spinnerPets.getSelectedItem());

                if (petType == "Dog" || petType.equals("Dog")) {
                    adapterBreed = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getDogBreeds());
                } else if (petType == "Cat" || petType.equals("Cat")) {
                    adapterBreed = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getCatBreeds());
                } else {
                    adapterBreed = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getBirdBreeds());
                }
                adapterBreed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBreed.setAdapter(adapterBreed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petCate = String.valueOf(spinnerCate.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinnerBreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petBreed = String.valueOf(spinnerBreed.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petSize = String.valueOf(spinnerSize.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petGender = String.valueOf(spinnerGender.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                petAge = String.valueOf(spinnerAge.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerState.getSelectedItem().equals("Andhra Pradesh")) {
                    ArrayAdapter<String> s1 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getAndhraPradeshCities());
                    s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s1);

                } else if (spinnerState.getSelectedItem().equals(
                        "Andaman and Nicobar Islands")) {
                    ArrayAdapter<String> s2 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item,
                            utils.getAndamanandNicobarIslandsCities());
                    s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s2);
                } else if (spinnerState.getSelectedItem().equals("Arunachal Pradesh")) {
                    ArrayAdapter<String> s3 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getArunachalPradeshCities());
                    s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s3);
                } else if (spinnerState.getSelectedItem().equals("Assam")) {
                    ArrayAdapter<String> s4 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getAssamCities());
                    s4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s4);
                } else if (spinnerState.getSelectedItem().equals("Bihar")) {
                    ArrayAdapter<String> s5 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getBiharCities());
                    s5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s5);
                } else if (spinnerState.getSelectedItem().equals("Chandigarh")) {
                    ArrayAdapter<String> s6 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getChandigarhCities());
                    s6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s6);
                } else if (spinnerState.getSelectedItem().equals("Chhattisgarh")) {
                    ArrayAdapter<String> s7 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getChhattisgarhCities());
                    s7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s7);
                } else if (spinnerState.getSelectedItem().equals("Dadra and Nagar Haveli")) {
                    ArrayAdapter<String> s6 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getDadracities());
                    s6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s6);
                } else if (spinnerState.getSelectedItem().equals("Daman and Diu")) {
                    ArrayAdapter<String> s7 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getDamancities());
                    s7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s7);
                } else if (spinnerState.getSelectedItem().equals("Delhi")) {
                    ArrayAdapter<String> s8 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getDelhi());
                    s8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s8);
                } else if (spinnerState.getSelectedItem().equals("Goa")) {
                    ArrayAdapter<String> s9 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getGoa());
                    s9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s9);
                } else if (spinnerState.getSelectedItem().equals("Gujarat")) {
                    ArrayAdapter<String> s10 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getGujaratCities());
                    s10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s10);
                } else if (spinnerState.getSelectedItem().equals("Haryana")) {
                    ArrayAdapter<String> s11 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getHaryanaCities());
                    s11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s11);
                } else if (spinnerState.getSelectedItem().equals("Himachal Pradesh")) {
                    ArrayAdapter<String> s12 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getHimachalCities());
                    s12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s12);
                } else if (spinnerState.getSelectedItem().equals("Jammu and Kashmir")) {
                    ArrayAdapter<String> s13 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getJammuCities());
                    s13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s13);
                } else if (spinnerState.getSelectedItem().equals("Jharkhand")) {
                    ArrayAdapter<String> s14 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getJharkhandCities());
                    s14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s14);
                } else if (spinnerState.getSelectedItem().equals("Karnataka")) {
                    ArrayAdapter<String> s15 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getKarnatakaCities());
                    s15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s15);
                } else if (spinnerState.getSelectedItem().equals("Kerala")) {
                    ArrayAdapter<String> s16 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getKeralaCities());
                    s16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s16);
                } else if (spinnerState.getSelectedItem().equals("Lakshadweep")) {
                    ArrayAdapter<String> s17 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getLakshadweepCities());
                    s17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s17);
                } else if (spinnerState.getSelectedItem().equals("Madhya Pradesh")) {
                    ArrayAdapter<String> s18 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getMadhyaPradeshCities());
                    s18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s18);
                } else if (spinnerState.getSelectedItem().equals("Maharashtra")) {
                    ArrayAdapter<String> s19 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getMaharashtraCities());
                    s19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s19);
                } else if (spinnerState.getSelectedItem().equals("Manipur")) {
                    ArrayAdapter<String> s20 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getManipurCities());
                    s20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s20);
                } else if (spinnerState.getSelectedItem().equals("Meghalaya")) {
                    ArrayAdapter<String> s21 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getMeghalayaCities());
                    s21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s21);
                } else if (spinnerState.getSelectedItem().equals("Mizoram")) {
                    ArrayAdapter<String> s22 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getMizoramCities());
                    s22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s22);
                } else if (spinnerState.getSelectedItem().equals("Nagaland")) {
                    ArrayAdapter<String> s23 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getNagalandCities());
                    s23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s23);
                } else if (spinnerState.getSelectedItem().equals("Orissa")) {
                    ArrayAdapter<String> s24 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getOrissaCities());
                    s24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s24);
                } else if (spinnerState.getSelectedItem().equals("Pondicherry")) {
                    ArrayAdapter<String> s25 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getPondicherryCities());
                    s25.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s25);
                } else if (spinnerState.getSelectedItem().equals("Punjab")) {
                    ArrayAdapter<String> s26 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getPunjabCities());
                    s26.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s26);
                } else if (spinnerState.getSelectedItem().equals("Rajasthan")) {
                    ArrayAdapter<String> s27 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getRajasthanCities());
                    s27.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s27);
                } else if (spinnerState.getSelectedItem().equals("Sikkim")) {
                    ArrayAdapter<String> s28 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getSikkimCities());
                    s28.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s28);
                } else if (spinnerState.getSelectedItem().equals("Tamil Nadu")) {
                    ArrayAdapter<String> s29 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getTamilCities());
                    s29.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s29);
                } else if (spinnerState.getSelectedItem().equals("Tripura")) {
                    ArrayAdapter<String> s30 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getTripuraCities());
                    s30.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s30);
                } else if (spinnerState.getSelectedItem().equals("Uttaranchal")) {
                    ArrayAdapter<String> s31 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getUttaranchalCities());
                    s31.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s31);
                } else if (spinnerState.getSelectedItem().equals("Uttar Pradesh")) {
                    ArrayAdapter<String> s32 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getUttarPradeshCities());
                    s32.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s32);
                } else if (spinnerState.getSelectedItem().equals("West Bengal")) {
                    ArrayAdapter<String> s33 = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_item, utils.getWestBengalCities());
                    s33.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(s33);
                }
                state = spinnerState.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                state = "";
            }
        });
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = String.valueOf(spinnerCity.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @OnClick(R.id.addPhotos)
    public void submit() {
        Intent i = new Intent(getActivity(),
                CustomImgGalleryActivity.class);
        startActivityForResult(i, 200);
    }

    private void initSpinner() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.pets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPets.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterCate = ArrayAdapter.createFromResource(getActivity(),
                R.array.cate_array, android.R.layout.simple_spinner_item);
        adapterCate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCate.setAdapter(adapterCate);

        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(getActivity(),
                R.array.pets_size_array, android.R.layout.simple_spinner_item);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapterSize);

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(getActivity(),
                R.array.pets_gender_array, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);

        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(getActivity(),
                R.array.pets_age_array, android.R.layout.simple_spinner_item);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapterAge);

        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, utils.getState());
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapterState);

        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, utils.state_city);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            all_path = data.getStringArrayExtra("all_path");
            if (all_path.length > 0) {
                selectedMediaTxt.setVisibility(View.VISIBLE);
                selectedMediaTxt.setText("Attaching images....");
            }
            System.out.println(all_path);
         /*   showSnack(IdeasCreate.this, "You have Selected " + all_path.length
                    + " images", "OK");*/

            ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

            for (String selectedPath1 : all_path) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = selectedPath1;

                dataT.add(item);

                // parsing
                ext = selectedPath1
                        .substring(selectedPath1.lastIndexOf(".") + 1);
                fileName = selectedPath1.replaceFirst(".*/(\\w+).*", "$1");

                // selectedMediaTxt.setText(fileName+"."+ext);

                filee = new File(selectedPath1);
                int size = (int) filee.length();
                bytes = new byte[size];
                try {
                    BufferedInputStream buf = new BufferedInputStream(
                            new FileInputStream(filee));
                    buf.read(bytes, 0, bytes.length);
                    buf.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(bytes + " !!!!!!!!!!!!!!!!!!!!");

                ext = ext.toLowerCase();
                System.out.println("File name : " + fileName + "   "
                        + "Image extension : " + ext);

                new LoadImages().execute();

            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.post_ad, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                ((MainActivity) context).onBackPress();
                return true;
            case R.id.postAd:
                Log.d("CALLING", petType);
                petName = editTextViews.get(0).getText().toString();
                petDesc = editTextViews.get(1).getText().toString();
                petBrfDes = editTextViews.get(2).getText().toString();
                petCost = editTextViews.get(3).getText().toString();
                location= editTextViews.get(4).getText().toString();
                if (petType.equals("Pet type") || petType == "Pet type") {
                    utils.showMsg("Please Select Pet Type!", mainLayout);
                } else if (petCate.equals("Pet category") || petCate == "Pet category") {
                    utils.showMsg("Please Select Pet Category!", mainLayout);
                } else if (petBreed.equals("Pet breed") || petBreed == "Pet breed") {
                    utils.showMsg("Please Select Pet Breed!", mainLayout);
                } else if (petSize.equals("Pet size") || petSize == "Pet size") {
                    utils.showMsg("Please Select Pet Size!", mainLayout);
                } else if (petGender.equals("Pet gender") || petGender == "Pet gender") {
                    utils.showMsg("Please Select Pet Gender!", mainLayout);
                } else if (petAge.equals("Pet age") || petGender == "Pet age") {
                    utils.showMsg("Please Select Pet Age!", mainLayout);
                } else if (state.equals("Select State") || state == "Select State") {
                    utils.showMsg("Please Select State!", mainLayout);
                } else if (city.equals("Select City") || state == "Select City") {
                    utils.showMsg("Please Select City!", mainLayout);
                } else {
                    HashMap<String, String> mapPost = new HashMap<String, String>();
                    mapPost.put("id", "23");
                    mapPost.put("user_id", "1");
                    mapPost.put("pet_type", petType);
                    mapPost.put("category", petCate);
                    mapPost.put("name", petName);
                    mapPost.put("short_description", petDesc);
                    mapPost.put("description", petBrfDes);
                    mapPost.put("pet_price", petCost);
                    mapPost.put("breed", petBreed);
                    mapPost.put("size", petSize);
                    mapPost.put("gender", petGender);
                    mapPost.put("age", petAge);
                    mapPost.put("location", location);
                    mapPost.put("state", state);
                    mapPost.put("city", city);
                    mapPost.put("photos", eventImages.toString());
                    mapPet.put("pet", mapPost);
                    Log.d("POST INPUT", String.valueOf(mapPet));
                    PostAd();
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void PostAd() {
        Log.d("INPUT", mapPet.toString());
        progressBar.setVisibility(View.VISIBLE);
        utils.sendScrollDown(scrollPost);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(utils.BASE_URL)
                .build();
        HttpInterface api = adapter.create(HttpInterface.class);
        api.postAd(mapPet, new Callback<PetsPojo>() {
            @Override
            public void success(PetsPojo inboxPojos, Response response) {
                PetsPojo postPojo = inboxPojos;
                System.out.println("Success" + " success in postMethodMoreParams- Hash map");
                progressBar.setVisibility(View.GONE);
                utils.showMsg("Successfully posted!", mainLayout);
                ((MainActivity) getActivity()).addFragment(new HomeFrag());
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Error : in postMethod MoreParams- Hash map" + error);
                progressBar.setVisibility(View.GONE);
                utils.showMsg("Oops! Something went wrong!", mainLayout);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        ((MainActivity) context).setHomeAsEnabled(false);
        ((MainActivity) context).manageDrawerLayout(false);
        ((MainActivity) context).changeToolbarTitle("Post Ad");
    }

    public class LoadImages extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Map response = Aplication.cloudinary.uploader().upload(filee, ObjectUtils.emptyMap());
                System.out.println(response + "    RESULT ");
                eventImages.put(response.get("url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            if (all_path.length == eventImages.length()) {
                //     pDialogImg.setVisibility(View.GONE);
                selectedMediaTxt.setText("Attached "
                        + all_path.length + " images");
            } else {
                //   pDialogImg.setVisibility(View.VISIBLE);
                selectedMediaTxt.setText("Attaching images...."
                        + (String.valueOf(eventImages.length())));

            }
        }
    }
}