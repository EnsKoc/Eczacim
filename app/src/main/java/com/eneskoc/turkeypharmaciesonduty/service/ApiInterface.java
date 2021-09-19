package com.eneskoc.turkeypharmaciesonduty.service;

import com.eneskoc.turkeypharmaciesonduty.model.PharmacyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("Account/NobetciEczaneList")
    Call<List<PharmacyModel>> getPharmacies(@Query("ilKodu") int countryCode, @Query("ilceKodu") String districtsCode, @Query("nobetGunu") int day );
}
