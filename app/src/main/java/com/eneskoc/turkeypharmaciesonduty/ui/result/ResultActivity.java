package com.eneskoc.turkeypharmaciesonduty.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eneskoc.turkeypharmaciesonduty.databinding.ActivityResultBinding;
import com.eneskoc.turkeypharmaciesonduty.model.PharmacyModel;
import com.eneskoc.turkeypharmaciesonduty.model.ProvinceModel;
import com.eneskoc.turkeypharmaciesonduty.ui.adapters.PharmacyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements ResultListener {

    int cityIndexValue, districtsIndexValue;
    private List<String> provinceList = new ArrayList<>();
    private ActivityResultBinding resultBinding;
    private String districtsStringValue;
    private ResultPresenter resultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultBinding = ActivityResultBinding.inflate(getLayoutInflater());
        resultPresenter = new ResultPresenter(ResultActivity.this, this);
        setContentView(resultBinding.getRoot());

        Intent intent = getIntent();
        cityIndexValue = intent.getIntExtra("cityIndexValue", 0);
        districtsIndexValue = intent.getIntExtra("districtsIndexValue", 0);

        setSearchedData();
        resultPresenter.fetchDutyPharmacies(cityIndexValue, districtsStringValue);
    }


    public void setSearchedData() {
        List<ProvinceModel> provinceModels = resultPresenter.getProvinceModels();
        for (int i = 0; i < provinceModels.size(); i++) {
            provinceList.add(provinceModels.get(i).getIl());
        }
        districtsStringValue = provinceModels.get(cityIndexValue).getIlceleri().get(districtsIndexValue);
        resultBinding.tvHeaderTitle.setText(districtsStringValue + "/" + provinceList.get(cityIndexValue));
    }

    @Override
    public void showLoading() {
        resultBinding.rvResult.setVisibility(View.GONE);
        resultBinding.lytHeader.setVisibility(View.GONE);
        resultBinding.loadingAnimationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorPage() {
        //TODO No Results Found Popup Will Be Added
    }

    @Override
    public void showDutyPharmaciesPage() {
        new Handler().postDelayed(() -> {
            resultBinding.rvResult.setVisibility(View.VISIBLE);
            resultBinding.lytHeader.setVisibility(View.VISIBLE);
            resultBinding.loadingAnimationView.setVisibility(View.GONE);

            List<PharmacyModel> pharmacyModelList = resultPresenter.getDutyPharmacies();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ResultActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            linearLayoutManager.scrollToPosition(0);
            resultBinding.rvResult.setLayoutManager(linearLayoutManager);
            PharmacyAdapter customAdapter = new PharmacyAdapter(pharmacyModelList, this);
            resultBinding.rvResult.setAdapter(customAdapter);
        }, 1000);

    }
}
