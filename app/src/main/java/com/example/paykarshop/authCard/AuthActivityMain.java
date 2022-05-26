package com.example.paykarshop.authCard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.paykarshop.MainActivity;
import com.example.paykarshop.R;

public class AuthActivityMain extends AppCompatActivity  implements View.OnClickListener {

    private static final String SAVED_PASS = "iPass";
    private static final String SAVED_BIOMETRIC = "Biometric";
    EditText editText;
    Button button;
    private SharedPreferences sPref, Pref;
    Vibrator vibratoro;
    AppCompatEditText maskEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_card);

        vibratoro = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        sPref = getSharedPreferences("LocalData", MODE_PRIVATE);
        String Auth = sPref.getString("Auth", "false");

        String Pass= sPref.getString(SAVED_PASS,"" );
        Boolean Biometric= sPref.getBoolean("biometric",false);

        Log.e("QWE", String.valueOf(Biometric));

        if(Biometric == false){

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Пароль");

            alertDialog.setTitle("Ваш пароль " );
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Сохранить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

        } else {

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("П");

            alertDialog.setTitle("Ва " );

        }

        if (Pass.equals("")) {

            if (Auth.equals("true")) {



                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            } else {
                SharedPreferences.Editor editor = sPref.edit();
                editor.putString("Auth", "false");
                editor.apply();
            }
        } else {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        maskEditText = findViewById(R.id.phone_txt_mask);
        button = (Button) findViewById(R.id.btn_search);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


//    if (maskEditText.getText().length() == 9){

        Intent intent = new Intent(this, VerificationActivity.class);
        intent.putExtra("phone", maskEditText.getText().toString());
        startActivity(intent);

//    }else {
//
//      Toast toast = Toast.makeText(this, "Укажите Ваш мобильный телефон", Toast.LENGTH_SHORT);
//      toast.show();
//    }

    }
}