package com.example.sth.newjustbecause;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "Some text";

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
//                String url1 = "http://url/api/devices/33/action/turnOff";
//        String charset = "UTF-8";
//
//        URL url = new URL(url1);
//
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//        try {
//
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setChunkedStreamingMode(0);
//            urlConnection.setDoOutput(true);
//
//            urlConnection.setRequestProperty("Accept-Charset", charset);
//            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            String input = "";
//
//
//            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//            out.write(input.getBytes());
//
//
//        } finally {
//            urlConnection.disconnect();
//        }

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
