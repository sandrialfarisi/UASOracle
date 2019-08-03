package com.example.uasoracle.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasoracle.R;
import com.example.uasoracle.model.CustomerItem;
import com.example.uasoracle.model.PembelianItem;
import com.example.uasoracle.model.ResponseCustomer;
import com.example.uasoracle.model.ResponsePembelian;
import com.example.uasoracle.util.api.BaseApiService;
import com.example.uasoracle.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PembelianFragment extends Fragment {
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    List<String> listSpinner = new ArrayList<String>();
    List<String> listKdbarang = new ArrayList<String>();
    List<String> listKdsupplier = new ArrayList<String>();
    List<String> listTgl = new ArrayList<String>();
    List<String> listJml = new ArrayList<String>();
    List<String> listHarga = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pembelian,container,false);

        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        TextView kd_barang = (TextView) view.findViewById(R.id.kodebarang);
        TextView kd_supplier = (TextView) view.findViewById(R.id.kodesupplier);
        TextView tgl = (TextView) view.findViewById(R.id.tanggal);
        TextView jml = (TextView) view.findViewById(R.id.jumlah);
        TextView harga = (TextView) view.findViewById(R.id.harga);
        mContext = getContext();
        mApiService = UtilsApi.getAPIService();

        loading = ProgressDialog.show(mContext, null, "harap tunggu...", true, false);
        mApiService.getSemuaPembelian().enqueue(new Callback<ResponsePembelian>() {
            @Override
            public void onResponse(Call<ResponsePembelian> call, Response<ResponsePembelian> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<PembelianItem> semuapembelianItems = response.body().getSemuapembelian();

                    for (int i = 0; i < semuapembelianItems.size(); i++) {
                        String kode = semuapembelianItems.get(i).getKd_transaksi();
                        String kd_barang = semuapembelianItems.get(i).getKd_barang();
                        String kd_supplier = semuapembelianItems.get(i).getKd_supplier();
                        String tgl = semuapembelianItems.get(i).getTgl();
                        String jml = semuapembelianItems.get(i).getJml();
                        String harga = semuapembelianItems.get(i).getHarga();
                        listSpinner.add(kode);
                        listKdbarang.add(kd_barang);
                        listKdsupplier.add(kd_supplier);
                        listTgl.add(tgl);
                        listJml.add(jml);
                        listHarga.add(harga);
                    }

                    spin.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listSpinner));
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
//                                android.R.layout.simple_spinner_item, listSpinner);
//                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        spinner.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data pembelian", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePembelian> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedKdbarang = listKdbarang.get(position);
                String selectedKdsupplier = listKdsupplier.get(position);
                String selectedTanggal = listTgl.get(position);
                String selectedJumlah = listJml.get(position);
                String selectedHarga = listHarga.get(position);
                kd_barang.setText(selectedKdbarang);
                kd_supplier.setText(selectedKdsupplier);
                tgl.setText(selectedTanggal);
                jml.setText(selectedJumlah);
                harga.setText(selectedHarga);
                Toast.makeText(mContext, "Kamu Memilih " + selectedKdbarang, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
