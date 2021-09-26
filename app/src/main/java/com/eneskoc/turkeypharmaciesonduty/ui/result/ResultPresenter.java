package com.eneskoc.turkeypharmaciesonduty.ui.result;

import android.content.Context;

import com.eneskoc.turkeypharmaciesonduty.model.PharmacyModel;
import com.eneskoc.turkeypharmaciesonduty.model.ProvinceModel;
import com.eneskoc.turkeypharmaciesonduty.service.ApiClient;
import com.eneskoc.turkeypharmaciesonduty.service.ApiInterface;
import com.eneskoc.turkeypharmaciesonduty.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultPresenter {

    private final String JSON_FILE_NAME = "turkey_provinces_and_districts.json";
    private Context context;
    private ResultListener listener;
    private List<PharmacyModel> pharmacyModelList = new ArrayList<>();


    public ResultPresenter(Context context, ResultListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public List<PharmacyModel> getDutyPharmacies() {
        return pharmacyModelList;
    }

    public List<ProvinceModel> getProvinceModels() {
        String jsonFileString = JsonUtils.getJsonFromAssets(context, JSON_FILE_NAME);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<ProvinceModel>>() {
        }.getType();

        return gson.fromJson(jsonFileString, listUserType);
    }

    public void fetchDutyPharmacies(int cityIndexValue, String districtsStringValue) {
        listener.showLoading();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PharmacyModel>> call = apiInterface.getPharmacies(cityIndexValue, districtsStringValue, 0);
        call.enqueue(new Callback<List<PharmacyModel>>() {
            @Override
            public void onResponse(Call<List<PharmacyModel>> call, Response<List<PharmacyModel>> response) {
                if (response.body() != null) {
                    pharmacyModelList = response.body();
                    listener.showDutyPharmaciesPage();
                }
            }

            @Override
            public void onFailure(Call<List<PharmacyModel>> call, Throwable t) {

            }
        });

    }
}
