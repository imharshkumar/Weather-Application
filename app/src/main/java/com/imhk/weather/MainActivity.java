package com.imhk.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //api.openweathermap.org/data/2.5/weather?q=Delhi&appid=6b7ac77a812179a48095586e558b93cf
    String url0="https://api.openweathermap.org/data/2.5/weather?q=";
    String url1="&appid=6b7ac77a812179a48095586e558b93cf";
    Button button;
    TextView textView;
    EditText editText;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView2);
        requestQueue=VolleySingleton.getInstance(this).getRequestQueue();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()) {
                    editText.setError("enter city name");
                    return;
                }
                String myurl=url0+editText.getText().toString()+url1;
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, myurl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject temp= null;
                        try {
                            temp = response.getJSONObject("main");
                            double get=temp.getInt("temp");
                            get=get-273.15;
                            //String a=String.valueOf(get);
                            //String.format("%.2f",get);
                            textView.setText(String.format("%.1f",get)+" celcius");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(request);
            }
        });

    }
}
