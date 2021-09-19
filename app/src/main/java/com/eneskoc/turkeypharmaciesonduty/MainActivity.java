package com.eneskoc.turkeypharmaciesonduty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.eneskoc.turkeypharmaciesonduty.model.ProvinceModel;
import com.eneskoc.turkeypharmaciesonduty.model.PharmacyModel;
import com.eneskoc.turkeypharmaciesonduty.service.ApiClient;
import com.eneskoc.turkeypharmaciesonduty.service.ApiInterface;
import com.eneskoc.turkeypharmaciesonduty.databinding.ActivityMainBinding;
import com.eneskoc.turkeypharmaciesonduty.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private final List<String> citiesList = new ArrayList<>();
    private List<ProvinceModel> provinceModels = new ArrayList<>();

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());


        String jsonFileString = JsonUtils.getJsonFromAssets(getApplicationContext(), "turkey_provinces_and_districts.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<ProvinceModel>>() {}.getType();

        provinceModels = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < provinceModels.size(); i++) {
            citiesList.add(provinceModels.get(i).getIl());
            Log.i("data", i + "::" + provinceModels.get(i).getIl()+ "--" + provinceModels.get(i).getPlaka()+ "\n" + provinceModels.get(i).getIlceleri());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_list_item,
                citiesList);
        mainBinding.spinnerCity.setAdapter(arrayAdapter);
        mainBinding.spinnerCity.setOnItemSelectedListener(this);


        mainBinding.tvSearchBtn.setOnClickListener(v -> {
            int cityIndexValue = mainBinding.spinnerCity.getSelectedItemPosition();
            List<String> ilceler = provinceModels.get(cityIndexValue).getIlceleri();
            int districtsIndexValue = mainBinding.spinnerDistrict.getSelectedItemPosition();
            String districtsStringValue = ilceler.get(districtsIndexValue);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<List<PharmacyModel>> call = apiInterface.getPharmacies(cityIndexValue, districtsStringValue,0);
            call.enqueue(new Callback<List<PharmacyModel>>() {
                @Override
                public void onResponse(Call<List<PharmacyModel>> call, Response<List<PharmacyModel>> response) {

                    List<PharmacyModel> responseBody = response.body();
                    Log.i("TAG", "onResponse: "+responseBody);
                    System.out.println("LİSTE:"+responseBody);
                    System.out.println("0-->"+responseBody.get(0).getEczaneAdi());
                    System.out.println("1-->"+responseBody.get(1).getEczaneAdi());
                    System.out.println("2-->"+responseBody.get(2).getEczaneAdi());

                }

                @Override
                public void onFailure(Call<List<PharmacyModel>> call, Throwable t) {

                }
            });
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int cityIndexValue = mainBinding.spinnerCity.getSelectedItemPosition();
        List<String> ilceler = provinceModels.get(cityIndexValue).getIlceleri();

        ArrayAdapter<String> arrayIlceAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_list_item,
                ilceler);
        mainBinding.spinnerDistrict.setAdapter(arrayIlceAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}