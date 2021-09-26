package com.eneskoc.turkeypharmaciesonduty.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eneskoc.turkeypharmaciesonduty.R;
import com.eneskoc.turkeypharmaciesonduty.databinding.PharmacyListItemBinding;
import com.eneskoc.turkeypharmaciesonduty.model.PharmacyModel;

import java.util.List;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.ViewHolder> {

    private final Context context;
    public List<PharmacyModel> pharmacyModelList;

    public PharmacyAdapter(List<PharmacyModel> pharmacyModelList, Context context) {
        this.pharmacyModelList = pharmacyModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PharmacyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(PharmacyListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapter.ViewHolder holder, final int position) {
        PharmacyModel pharmacyObj = pharmacyModelList.get(position);
        holder.listItemBinding.tvPharmacyName.setText(pharmacyObj.getEczaneAdi() + context.getString(R.string.pharmacy));
        holder.listItemBinding.tvPharmacyAddress.setText(pharmacyObj.getEczaneAdres());
    }

    @Override
    public int getItemCount() {
        return pharmacyModelList == null ? 0 :
                pharmacyModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final PharmacyListItemBinding listItemBinding;

        public ViewHolder(PharmacyListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }
    }
}

