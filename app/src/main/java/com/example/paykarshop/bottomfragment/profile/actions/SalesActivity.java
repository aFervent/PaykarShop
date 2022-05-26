package com.example.paykarshop.bottomfragment.profile.actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paykarshop.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SalesActivity extends AppCompatActivity {

    JSONArray Date = new JSONArray();

    ArrayList<String> arrayName = new ArrayList<>();
    ArrayList<String> arrayDate = new ArrayList<>();
    ArrayList<String> arrayPicture = new ArrayList<>();
    ArrayList<String> arrayPeriod = new ArrayList<>();
    ArrayList<String> arrayPreview = new ArrayList<>();
    ArrayList<String> arrayDetail = new ArrayList<>();
    ArrayList<String> arrayDetailPicture = new ArrayList<>();


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        new SaleClass().execute();
        listView = findViewById(R.id.sale_list_view);
    }

    @SuppressLint("StaticFieldLeak")
    public class SaleClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            Log.e("result","result");

            try {

                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .build();

                Request request = new Request.Builder()
                        .url("https://paykar.tj/api/getsales.php")
                        .get()
                        .addHeader("content-type", "application/json; charset=utf-8")
                        .build();



                Response response = okHttpClient.newCall(request).execute();
                String result = response.body().string();

                Date = new JSONArray(result);

                Log.e("result",result);


                for (int i = 0; i < Date.length(); i++ ){

                    JSONObject jsonObject = Date.getJSONObject(i);
                    String Name = jsonObject.getString("name");
                    String Date = jsonObject.getString("date");
                    String Picture = jsonObject.getString("picture");
                    String Period = jsonObject.getString("period");
                    String Preview = jsonObject.getString("preview");
                    String Detail = jsonObject.getString("detail");
                    String DetailPicture = jsonObject.getString("detail_picture");


                    arrayName.add(Name);
                    arrayDate.add(Date);
                    arrayPicture.add(Picture);
                    arrayPeriod.add(Period);
                    arrayPreview.add(Preview);
                    arrayDetail.add(Detail);
                    arrayDetailPicture.add(DetailPicture);

                    Collections.reverse(arrayName);
                    Collections.reverse(arrayDate);
                    Collections.reverse(arrayPicture);
                    Collections.reverse(arrayPeriod);
                    Collections.reverse(arrayPreview);
                    Collections.reverse(arrayDetail);
                    Collections.reverse(arrayDetailPicture);

                }


                return result;

            } catch (Exception e) {

                Log.e("12", String.valueOf(e));

                return e.toString();

            }

        }

        public Drawable LoadImageFromWebOperations(String url) {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "src name");
                return d;
            } catch (Exception e) {
                return null;
            }
        }


        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            MyAdapter adapter = new MyAdapter(SalesActivity.this,arrayPeriod, arrayDetailPicture);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(SalesActivity.this, SaleDetailsActivity.class);
                    intent.putExtra("Name",arrayName.get(position));
                    intent.putExtra("Period",arrayPeriod.get(position));
                    intent.putExtra("Detail",arrayDetail.get(position));
                    intent.putExtra("DetailPicture",arrayDetailPicture.get(position));
                    startActivity(intent);

                }
            });

        }

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;

        ArrayList<String> aPicture;
        ArrayList<String> aPeriod;

        MyAdapter(Context context,  ArrayList<String> preview, ArrayList<String> picture){
            super(context,R.layout.sale_list_view,R.id.image_id,preview);
            this.context=context;

            this.aPicture = picture;
            this.aPeriod = preview;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.sale_list_view, parent,false);

            ImageView imageView = view.findViewById(R.id.image_id);
            //TextView nametext = view.findViewById(R.id.id_txt_name);
            TextView desctext = view.findViewById(R.id.id_txt_desc);

            String url = "https://paykar.tj" + aPicture.get(position);

            //nametext.setText(aName.get(position));
            desctext.setText(aPeriod.get(position));

            new image.DownloadImageTask(imageView).execute(url);

            return view;
        }
    }
}