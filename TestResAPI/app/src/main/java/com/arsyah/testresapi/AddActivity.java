package com.arsyah.testresapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arsyah.testresapi.API.RetroServer;
import com.arsyah.testresapi.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {
    private EditText inNim, inNama, inAngkatan;
    private Button btnSimpan;
    private String nim, nama, angkatan;
    private int idmhs;
    private Button btnHapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inNim = findViewById(R.id.in_nim);
        inNama = findViewById(R.id.in_nama);
        inAngkatan = findViewById(R.id.in_angkatan);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnHapus = findViewById(R.id.btn_hapus);

        Intent data = getIntent();
        String id = data.getStringExtra("id");
        if (id !=null){
            inNim.setText(data.getStringExtra("nim"));
            inNama.setText(data.getStringExtra("nama"));
            inAngkatan.setText(data.getStringExtra("angkatan"));
            idmhs = Integer.parseInt(id);
            btnHapus.setVisibility(View.VISIBLE);
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = inNim.getText().toString();
                nama = inNama.getText().toString();
                angkatan = inAngkatan.getText().toString();


                if (nim.trim().equals("")){
                    inNim.setError("Nim harus diisi");
                }else if (nama.trim().equals("")){
                    inNama.setError("Nama harus diisi");
                }else if (angkatan.trim().equals("")){
                    inAngkatan.setError("Angkatan harus diisi");
                }else{
                    if (id != null){
                        updateData();
                    }else {
                        createdata();
                    }
                }
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertData();
            }
        });
    }

    public void createdata() {
        Call<ResponseModel> simpanData = RetroServer.getMhsApi().ardCreateMahasiswa(nim,nama,Integer.parseInt(angkatan));

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String message = response.body().getMessage();
                Toast.makeText(AddActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void updateData() {
        Call<ResponseModel> perbaruiData = RetroServer.getMhsApi().ardUpdateMahasiswa(
                idmhs,
                nim,
                nama,
                Integer.parseInt(angkatan));
        perbaruiData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(AddActivity.this, "Berhasil Mengupdate Data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void alertData(){
        AlertDialog.Builder kotakDialog = new AlertDialog.Builder(AddActivity.this);
        kotakDialog.setMessage("Anda Yakin Akan Menhapus Data ini ?");
        kotakDialog.setCancelable(true);

        kotakDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteData();
                dialogInterface.dismiss();
            }
        });
        kotakDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        kotakDialog.show();
    }

    private void deleteData(){
        Call<ResponseModel> hapusData = RetroServer.getMhsApi().ardDeleteMahasiswa(idmhs);
        hapusData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String message = response.body().getMessage();
                Toast.makeText(AddActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}