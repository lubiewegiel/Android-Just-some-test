package com.example.sth.newjustbecause;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "Some text";
    public  String BASE_URL = "http://192.168.0.138/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void sendMessage(View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        if (message.length() > 0) {
            showToast(message);
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        } else {
            showToast("Meh, \n Wpisz Pan coś. \n Cokolwiek ziom.");
        }
        hideKeyboard();
    }

    public void showGoat(View view) throws IOException {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.with(getApplicationContext())
                .load("http://www.worldwideinterweb.com/images/blogphotos/Funny/Goat%20Pictures%20Funny/cool-goat.jpg")
                .into(imageView);
    }

    public void showWildBoar(View view) throws IOException {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.with(getApplicationContext())
                .load("http://i.dailymail.co.uk/i/pix/2011/12/19/article-2076151-0F39557000000578-602_634x559.jpg")
                .into(imageView);
    }

    public  void showNarwhal(View view) throws IOException {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.with(getApplicationContext())
                .load("https://s-media-cache-ak0.pinimg.com/236x/44/c7/e6/44c7e6e21a38075a6cf7d5e41daa9fa8.jpg")
                .into(imageView);
    }


    public void turnOffTheLight(View view) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        HCEndpointInterface apiService = retrofit.create(HCEndpointInterface.class);

        Call<RequestBody> call = apiService.turnOffDimmer();

        changeLight(call);

    }

    public void turnOnTheLight(View view) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        HCEndpointInterface apiService = retrofit.create(HCEndpointInterface.class);

        Call<RequestBody> call = apiService.turnOnDimmer();

        changeLight(call);

    }

    public void changeLight(Call<RequestBody> call) {
        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                int statusCode = response.code();
                StringBuilder str = new StringBuilder();
                str.append("");
                str.append(statusCode);
                String rc = str.toString();
                Log.v("Response code: ", rc);
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

                Log.v("Turn off the light", "failure" + t);

            }
        });
    }

    public void showToast (String message) {
        Context context = getApplicationContext();
        Toast toust = Toast.makeText(context, message, Toast.LENGTH_LONG);
        centerText(toust.getView());
        toust.show();
    }

    public void centerText(View view) {
        if( view instanceof TextView){
            ((TextView) view).setGravity(Gravity.CENTER);
        }else if( view instanceof ViewGroup){
            ViewGroup group = (ViewGroup) view;
            int n = group.getChildCount();
            for( int i = 0; i<n; i++ ){
                centerText(group.getChildAt(i));
            }
        }
    }

    public void hideKeyboard() {
        View view  = this.getCurrentFocus();
        if (view !=null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
