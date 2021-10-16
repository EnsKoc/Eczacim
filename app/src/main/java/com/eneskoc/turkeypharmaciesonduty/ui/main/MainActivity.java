package com.eneskoc.turkeypharmaciesonduty.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.eneskoc.turkeypharmaciesonduty.R;
import com.eneskoc.turkeypharmaciesonduty.databinding.ActivityMainBinding;
import com.eneskoc.turkeypharmaciesonduty.model.ProvinceModel;
import com.eneskoc.turkeypharmaciesonduty.ui.customView.CustomAlertDialog;
import com.eneskoc.turkeypharmaciesonduty.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, MainListener {

    int cityIndexValue, districtsIndexValue;
    private List<ProvinceModel> provinceModels = new ArrayList<>();
    private ActivityMainBinding mainBinding;
    private MainPresenter mainPresenter;
    private CustomAlertDialog customAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mainPresenter = new MainPresenter(MainActivity.this, this);
        initViews();
        setContentView(mainBinding.getRoot());
    }

    public void initViews() {
        List<String> provinceList = mainPresenter.getProvince();
        provinceModels = mainPresenter.getProvinceModels();

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_list_item,
                provinceList);
        mainBinding.spinnerCity.setAdapter(provinceAdapter);
        mainBinding.spinnerCity.setOnItemSelectedListener(this);

        mainBinding.tvSearchBtn.setOnClickListener(v -> {
            cityIndexValue = mainBinding.spinnerCity.getSelectedItemPosition();
            districtsIndexValue = mainBinding.spinnerDistrict.getSelectedItemPosition();
            mainPresenter.isValid(cityIndexValue);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int cityIndexValue = mainBinding.spinnerCity.getSelectedItemPosition();
        List<String> districtsList = provinceModels.get(cityIndexValue).getIlceleri();

        ArrayAdapter<String> districtsAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_list_item,
                districtsList);
        mainBinding.spinnerDistrict.setAdapter(districtsAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showErrorPopup() {
        customAlertDialog = new CustomAlertDialog(this);
        customAlertDialog.setTitle(R.string.selection_error_title);
        customAlertDialog.setDescription(R.string.selection_error_desc);
        customAlertDialog.setPositiveButton(getString(R.string.okay), v -> finish());
        customAlertDialog.show();
    }

    @Override
    public void openResultPage() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("cityIndexValue", cityIndexValue);
        intent.putExtra("districtsIndexValue", districtsIndexValue);

        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_left);
    }
}