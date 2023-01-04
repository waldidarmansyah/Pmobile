package com.example.tugaspenyimbananinternal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class RegisterActivity extends AppCompatActivity {

    private EditText Username, Nim, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* Menginisialisasi variable dengan Form User, Form Password, dan Form Repassword
        dari Layout RegisterActivity */
        Username =findViewById(R.id.Username);
        Nim =findViewById(R.id.Nim);
        Password =findViewById(R.id.Password);

        /* Menjalankan Method razia() jika merasakan tombol SignUp di keyboard disentuh */
        Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });
        /* Menjalankan Method razia() jika merasakan tombol SignUp disentuh */
        findViewById(R.id.Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity */
    private void razia(){
        /* Mereset semua Error dan fokus menjadi default */
        Username.setError(null);
        Nim.setError(null);
        Password.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String password = Password.getText().toString();
        String user = Username.getText().toString();
        String nim = Nim.getText().toString();

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(user)){
            Username.setError("This field is required");
            fokus = Username;
            cancel = true;
        }else if(cekUser(user)){
            Username.setError("This Username is already exist");
            fokus = Username;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            Password.setError("This field is required");
            fokus = Password;
            cancel = true;
        }else if(cekPassword(password)){
            Password.setError("This Username is already exist");
            fokus = Password;
            cancel = true;
        }
        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel){
            fokus.requestFocus();
        }else{
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredPass(getBaseContext(),password);
            finish();
        }
    }


    private boolean cekPassword(String password){
        return password.equals(password);
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(user);
    }
}