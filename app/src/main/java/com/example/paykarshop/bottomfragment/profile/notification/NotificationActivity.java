package com.example.paykarshop.bottomfragment.profile.notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paykarshop.MainActivity;
import com.example.paykarshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotificationActivity extends AppCompatActivity {

    public NotifiView notifi;
    private SharedPreferences sPref;

    ArrayList<String> arrayTitle = new ArrayList<>();
    ArrayList<String> arrayDesc = new ArrayList<>();
    ArrayList<String> arrayDate = new ArrayList<>();



    JSONArray NotifiArry = new JSONArray();
    ListView notifiLV;

    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        new PaykarNotification().execute();

        sPref = getSharedPreferences("LocalData", MODE_PRIVATE);
        notifiLV = findViewById(R.id.notification_list);

        GetMemoryNotification();
        Back();
    }


    public void Back(){

        Back = findViewById(R.id.back_notifi);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public String getMilliFromDate(String dateFormat) throws ParseException {
        //Дата которая приходить
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = fmt.parse(dateFormat);

        //Желаемая дата
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmtOut = new SimpleDateFormat("dd MMM yyyy | HH:mm");
        return fmtOut.format(date);

    }

    public void GetMemoryNotification() {

        String arrayResult = sPref.getString("PaykarNotifi", "arrayResult");

        try {
            NotifiArry = new JSONArray(arrayResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < NotifiArry.length(); i++) {

            JSONObject jsonObject = null;
            try {

                jsonObject = NotifiArry.getJSONObject(i);
                String Title = jsonObject.getString("Title");
                String Desc = jsonObject.getString("Message");
                String Date = jsonObject.getString("SendDate");


                String DateForm = null;
                try {
                    DateForm = getMilliFromDate(Date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                arrayTitle.add(Title);
                arrayDesc.add(Desc);
                arrayDate.add(DateForm);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        notifi = new NotifiView(NotificationActivity.this, arrayTitle, arrayDesc, arrayDate);
        notifiLV.setAdapter(notifi);
        notifi.notifyDataSetChanged();


    }

    class NotifiView extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> Title;
        ArrayList<String> Desc;
        ArrayList<String> Date;


        NotifiView(Context c, ArrayList<String> title, ArrayList<String> desc, ArrayList<String> date) {
            super(c, R.layout.notifi_item, R.id.ntf_title, title);
            this.context = c;
            this.Title = title;
            this.Desc = desc;
            this.Date = date;


        }

        @SuppressLint({"SetTextI18n", "DefaultLocale"})
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.notifi_item, parent, false);

            TextView title = row.findViewById(R.id.ntf_title);
            TextView desc = row.findViewById(R.id.ntf_desc);
            TextView date = row.findViewById(R.id.ntf_time);

            title.setText(Title.get(position));
            desc.setText(Desc.get(position));
            date.setText(Date.get(position));


            return row;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class PaykarNotification extends AsyncTask<String, Void, String> {

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
                params.put("typeos", "Android");
                params.put("phone", sPref.getString("iPhoneMobile", "Нет сохраненного значения"));
                params.put("token", sPref.getString("iToken", "Нет сохраненного значения"));


                JSONObject parameter = new JSONObject(params);

                RequestBody body = RequestBody.create(JSON, parameter.toString());
                Request request = new Request.Builder()
                        .url("https://mobileapp.paykar.tj/notification/func/getnotify.php")
                        .post(body)
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .build();

                Response response = okHttpClient.newCall(request).execute();

                String result = response.body().string();

                SharedPreferences.Editor editor = getSharedPreferences("LocalData", MODE_PRIVATE).edit();
                editor.putString("PaykarNotifi", result);
                editor.apply();

                return result;

            } catch (Exception e) {
                return e.toString();

            }

        }


        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }

    }
}