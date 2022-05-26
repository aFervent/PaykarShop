package com.example.paykarshop.authCard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paykarshop.MainActivity;
import com.example.paykarshop.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener {

    //Ключи для сохранения в память телефона
    private SharedPreferences sPref;


    private static final String SAVED_FIRSTNAME = "iFirstName";
    private static final String SAVED_LASTNAME = "iLastName";
    private static final String SAVED_SURNAME = "iSurName";
    private static final String SAVED_BIRTHDAY = "iBirthday";
    private static final String SAVED_PHONE = "iPhoneMobile";
    private static final String SAVED_CARD = "iCard";
    private static final String SAVED_TOKEN = "iToken";
    private static final String SAVED_STREET = "iStreet";
    private static final String SAVED_CITY = "iCity";
    private static final String SAVED_STATUS = "iStatus";


    //Приватные переменные
    private static String iFirstName = "";
    private static String iLastName = "";
    private static String iSurName = "";
    private static String iBirthday = "";
    private static String iPhoneMobile = "";
    private static String iCardCode = "";
    private static final String iToken = "";
    private static String iStreet = "";
    private static String iCity = "";
    private static final String iStatus = "";


    private static final String iConfCode = "";
    private static String NumberCode = "";
    private static String Phone = "";
    private static final String Demosms = "12345";

    AppCompatEditText number;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_card);

        new FeedTask().execute();
        new InfoAPI().execute();

        Intent intent = getIntent();
        String phonenumber = intent.getStringExtra("phone");
        Phone = phonenumber;

        Log.e("Phone",Phone);

        number =  findViewById(R.id.sms_code);
        NumberCode = number.getText().toString();
        button = (Button) findViewById(R.id.btn_code);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        NumberCode = number.getText().toString();
        new ConfCode().execute();


    }

    @SuppressLint("StaticFieldLeak")
    public class FeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .build();

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                Map<String, String> params = new HashMap<>();


                JSONObject parameter = new JSONObject(params);


                RequestBody body = RequestBody.create(JSON, parameter.toString());
                Request request = new Request.Builder()
                        .url("http://paykar.cloud39.ru/BonusWebApi/Mobile/StartLogin?phoneMobile=%2B992"+Phone)
                        //.url("94.199.18.140"+Phone)
                        .post(body)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .build();

                Response response = okHttpClient.newCall(request).execute();
                String result = response.body().string();

                return result;

            } catch (Exception e) {

                return e.toString();

            }

        }


        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Сохраения переменных в память телефона

        }


    }


    @SuppressLint("StaticFieldLeak")
    public class ConfCode extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .build();

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                Map<String, String> params = new HashMap<>();
                //params.put("LicenseGuid", "D66B2D9C-C17C-4D03-868D-477461CC70DB");
                params.put("Login", "+992"+Phone);
                params.put("ConfirmCode", NumberCode);

                JSONObject parameter = new JSONObject(params);

                RequestBody body = RequestBody.create(JSON, parameter.toString());
                Request request = new Request.Builder()
                        .url("http://94.199.18.140/BonusWebApi/Mobile/Login")
                        .post(body)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .build();


                Response response = okHttpClient.newCall(request).execute();

                String result = response.body().string();
                Log.e("res",result);

                int status = response.code();

                if (status == 200){

                    JSONObject jsonObject = new JSONObject(result);
                    String Token = jsonObject.getString("Token");
                    Log.e("1", Token);

                    SharedPreferences.Editor editor = getSharedPreferences("LocalData", MODE_PRIVATE).edit();
                    editor.putString(SAVED_TOKEN, Token);
                    editor.commit();

                    String token = sPref.getString(SAVED_TOKEN,"qwe");


                    if ( token != "") {

                        sPref = getSharedPreferences("LocalData", MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("Auth", "true");
                        ed.apply();

                        Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {

                        Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast toast = Toast.makeText(VerificationActivity.this, "Вы указали не верный код подтверждения.\n" +
                                "Пожалуйста попробуйте ещё раз.  \n",Toast.LENGTH_LONG);
                        toast.show();
                    }

                }

                return result;

            } catch (Exception e) {

//                Log.e("09", String.valueOf(e));
//                Toast toast = Toast.makeText(MainActivity.this, "Вы указали не верный код подтверждения.\n" +
//                   "Пожалуйста попробуйте ещё раз.  \n",Toast.LENGTH_LONG);
//                     toast.show();

                return e.toString();

            }

        }


        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            sPref = getSharedPreferences("LocalData", MODE_PRIVATE);
//            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor ed = sPref.edit();
//            ed.putString(SAVED_TOKEN, iToken);
//            ed.apply();


            Log.e("iToken",iToken);



        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAPI extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .build();

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                Map<String, String> params = new HashMap<>();
                params.put("LicenseGuid", "D66B2D9C-C17C-4D03-868D-477461CC70DB");
                params.put("MobilePhone", "+992"+Phone);

                JSONObject parameter = new JSONObject(params);

                RequestBody body = RequestBody.create(JSON, parameter.toString());
                Request request = new Request.Builder()
                        .url("http://94.199.18.140/BonusWebApi/api/processing/info")
                        .post(body)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .build();



                Response response = okHttpClient.newCall(request).execute();

                String result = response.body().string();

                Log.e("info", result);

                JSONObject jsonObject = new JSONObject(result);


                Object FirstName = jsonObject.getString("FirstName");
                Object PhoneMobile = jsonObject.getString("PhoneMobile");
                Object CardCode = jsonObject.getString("CardCode");
                Object LastName = jsonObject.getString("LastName");
                Object SurName = jsonObject.getString("SurName");
                Object Birthday = jsonObject.getString("Birthday");
                Object City = jsonObject.getString("City");
                Object Street = jsonObject.getString("Street");

                //Сохранения объектов массива в переменные
                iFirstName = FirstName.toString();
                iLastName = LastName.toString();
                iSurName = SurName.toString();
                iBirthday = Birthday.toString();
                iPhoneMobile = PhoneMobile.toString();
                iCardCode = CardCode.toString();
                iCity = City.toString();
                iStreet = Street.toString();

                Log.e("Saved", iCardCode);

                return result;

            } catch (Exception e) {
                return e.toString();

            }

        }


        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Сохраения переменных в память телефона

            sPref = getSharedPreferences("LocalData", MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_FIRSTNAME, iFirstName);
            ed.putString(SAVED_LASTNAME, iLastName);
            ed.putString(SAVED_SURNAME, iSurName);
            ed.putString(SAVED_BIRTHDAY, iBirthday);
            ed.putString(SAVED_PHONE, iPhoneMobile);
            ed.putString(SAVED_CARD, iCardCode);
            ed.putString(SAVED_CITY, iCity);
            ed.putString(SAVED_STREET, iStreet);

            ed.apply();

            Log.e("Saved_Card", iCardCode);

        }


    }

}