package me.dicky.crudandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dicky.crudandroid.api.RegisterAPI;
import me.dicky.crudandroid.model.Value;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String URL = "http://10.141.199.90/crud_android/";
    private RadioButton radioButtonJK;
    private ProgressDialog progressDialog;
    String nim,nama,jurusan;

    @BindView(R.id.radioJK) RadioGroup radioGroup;
    @BindView(R.id.editTextNim) EditText editTextNim;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextJurusan) EditText editTextJurusan;

    @OnClick(me.dicky.crudandroid.R.id.btDaftar) void daftar(){
        //Menampilkan progress dialog
        progressDialog = new  ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        nim = editTextNim.getText().toString();
        nama = editTextNama.getText().toString();
        jurusan = editTextJurusan.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();

        radioButtonJK = (RadioButton) findViewById(selectedId);
        String jk = radioButtonJK.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory (GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.daftar(nim,nama,jurusan,jk);

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progressDialog.dismiss();

                if (value.equals("1")){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btLihat ) void lihat(){
        Intent pindah = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(pindah);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
