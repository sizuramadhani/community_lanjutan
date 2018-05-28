package com.udakita.komunitas.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.udakita.komunitas.R;
import com.udakita.komunitas.helper.MyFunction;
import com.udakita.komunitas.model.ModelUser;
import com.udakita.komunitas.network.MyRetrofitClient;
import com.udakita.komunitas.network.RestApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends MyFunction {

    @BindView(R.id.edtnama)
    EditText edtnama;
    @BindView(R.id.edtalamat)
    EditText edtalamat;
    @BindView(R.id.edtnotelp)
    EditText edtnotelp;
    @BindView(R.id.spinjenkel)
    Spinner spinjenkel;
    @BindView(R.id.edtusername)
    EditText edtusername;
    @BindView(R.id.edtpassword)
    EditText edtpassword;
    @BindView(R.id.edtpasswordconfirm)
    EditText edtpasswordconfirm;
//    @BindView(R.id.regAdmin)
//    RadioButton regAdmin;
//    @BindView(R.id.regUserbiasa)
//    RadioButton regUserbiasa;
    @BindView(R.id.btnregister)
    Button btnregister;
    String jenkel[]={"laki-laki","perempuan"};
    String strnama,strusername,strpassword,strconpassword,stralamat,strlevel,strjenkel,strnohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
//        if (regAdmin.isChecked()){
//            strlevel="anggota";
//        }else{
//            strlevel="komunitas";
//        }
        //mengisi spinner jenis kelamin
        ArrayAdapter adapter = new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item,jenkel);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinjenkel.setAdapter(adapter);
        spinjenkel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strjenkel=jenkel[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.btnregister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.regAdmin:
//                strlevel="anggota";
//                break;
//            case R.id.regUserbiasa:
//                strlevel="komunitas";
//                break;
            case R.id.btnregister:
                strnama =edtnama.getText().toString();
                stralamat =edtalamat.getText().toString();
                strnohp =edtnotelp.getText().toString();
                strusername =edtusername.getText().toString();
                strpassword =edtpassword.getText().toString();
                strconpassword =edtpasswordconfirm.getText().toString();
                if (TextUtils.isEmpty(strnama)){
                    edtnama.setError("nama tidak boleh kosong");
                    edtnama.requestFocus();
                }else if(TextUtils.isEmpty(stralamat)){
                    edtalamat.requestFocus();
                    edtalamat.setError("alamt tidak boleh kosong");
                }else if (TextUtils.isEmpty(strnohp)){
                    edtnotelp.requestFocus();
                    edtnotelp.setError("no hp tidak boleh kosong");
                }else if(TextUtils.isEmpty(strusername)){
                    edtusername.requestFocus();
                    edtusername.setError("username tidak boleh kosong");
                }else if (TextUtils.isEmpty(strpassword)){
                    edtpassword.requestFocus();
                    edtpassword.setError("password tidak boleh kosong");
                }else if (strpassword.length()<6){
                    edtpassword.setError("password minimal 6 karakter");
                }else if (TextUtils.isEmpty(strconpassword)){
                    edtpasswordconfirm.requestFocus();
                    edtpasswordconfirm.setError("password confirm tidak boleh kosong");
                }else if (!strpassword.equals(strconpassword)){
                    edtpasswordconfirm.requestFocus();
                    edtpasswordconfirm.setError("password tidak sama");
                }else{
                    registeruser();
                }

                    break;
        }
    }

    private void registeruser() {
            final ProgressDialog dialog =ProgressDialog.show(RegisterActivity.this,"process register user",
                    "harap bersabar");
            String tglsekarang = MyFunction.currentDate();
            RestApi api = MyRetrofitClient.getInstaceRetrofit();
            Call<ModelUser> modelUserCall =api.registerUser(
                strnama,stralamat,strnohp,strjenkel,strusername,strpassword,strlevel,tglsekarang);
        //nangkap callback
    modelUserCall.enqueue(new Callback<ModelUser>() {
        @Override
        public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                dialog.dismiss();
                String result =response.body().getResult();
                String msg =response.body().getMsg();
                if (result.equals("1")){
                    mytoast(msg);
                    myIntent(LoginActivity.class);
                    finish();
                }else{
                    mytoast(msg);
                }
        }

        @Override
        public void onFailure(Call<ModelUser> call, Throwable t) {
            dialog.dismiss();
            mytoast("gagal koneksi :"+t.getMessage());
        }
    });
    }
}
