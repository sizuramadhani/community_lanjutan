package com.udakita.komunitas.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.udakita.komunitas.MainActivity;
import com.udakita.komunitas.R;
import com.udakita.komunitas.helper.SessionManager;
import com.udakita.komunitas.model.ModelUser;
import com.udakita.komunitas.network.MyRetrofitClient;
import com.udakita.komunitas.network.RestApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends SessionManager {

    @BindView(R.id.regUsername)
    EditText regUsername;
    @BindView(R.id.regPass)
    EditText regPass;
    @BindView(R.id.regAdmin)
    RadioButton regAdmin;
    @BindView(R.id.regUserbiasa)
    RadioButton regUserbiasa;
    @BindView(R.id.regBtnLogin)
    Button regBtnLogin;
    @BindView(R.id.regBtnRegister)
    Button regBtnRegister;
    String strlevel,strusername,strpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (regAdmin.isChecked()){
            strlevel="anggota";
        }else{
            strlevel="komunitas";
        }
    }

    @OnClick({R.id.regAdmin, R.id.regUserbiasa, R.id.regBtnLogin, R.id.regBtnRegister})
    public void onViewClicked(View view) {
        strusername =regUsername.getText().toString();
        strpassword =regPass.getText().toString();

        switch (view.getId()) {
            case R.id.regAdmin:
                strlevel="anggota";
                break;
            case R.id.regUserbiasa:
                strlevel="komunitas";
                break;
            case R.id.regBtnLogin:
                if (TextUtils.isEmpty(strusername)){
                    regUsername.setError("username tidak boleh kosong");
                }else if (TextUtils.isEmpty(strpassword)){
                    regPass.setError("password tidak boleh kosong");
                }else if (strpassword.length()<6){
                    regPass.setError("minimal password 6 karakter");
                }else {
                    loginuser();
                }

                break;
            case R.id.regBtnRegister:
                myIntent(RegisterActivity.class);
                break;
        }
    }

    private void loginuser() {
       // showProgressDialog("proses login user");
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"proses login","loading");
        RestApi api = MyRetrofitClient.getInstaceRetrofit();
        Call<ModelUser> modelUserCall =api.loginUser(
                strusername,strpassword,strlevel
        );
        modelUserCall.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                progressDialog.dismiss();
                String result =response.body().getResult();
                String msg =response.body().getMsg();
                Log.d("mada",result);
                if (result.equals("1")) {
                    mytoast(msg);
                    myIntent(MainActivity.class);
                    String iduser =response.body().getUser().getIdUser();
                    sessionManager.createSession(strusername);
                    sessionManager.setIdUser(iduser);
                    finish();
                }
                else {
                    mytoast(msg);
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                progressDialog.dismiss();
                mytoast("gagal koneksi :"+t.getMessage());
                Log.d("mada",t.getMessage());
            }
        });

    }
}
