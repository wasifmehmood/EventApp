package com.example.eventapp;


import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eventapp.Utils.EndPoints;
import com.example.eventapp.Utils.SharedPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //defining views
    private Button buttonSendPush;
    private Button buttonRegister;
    private EditText editTextEmail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting views from xml
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonSendPush = (Button) findViewById(R.id.buttonSendNotification);

        //adding listener to view
        buttonRegister.setOnClickListener(this);
        buttonSendPush.setOnClickListener(this);
    }

    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPreferenceManager.getInstance(this).getDeviceToken();
        final String email = editTextEmail.getText().toString();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            sendTokenToServer();
        }

        //starting send notification activity
        if(view == buttonSendPush){
            startActivity(new Intent(this, ActivitySendPushNotification.class));
        }
    }
}
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class MainActivity extends BaseActivity implements View.OnClickListener {
//    //defining views
//    private Button buttonRegister;
//    private EditText editTextEmail;
//    private ProgressDialog progressDialog;
//
//    //URL to RegisterDevice.php
//    private static final String URL_REGISTER_DEVICE = "http://192.168.10.15/EventFcm/RegisterDevice.php";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //getting views from xml
//        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        buttonRegister = (Button) findViewById(R.id.buttonRegister);
//
//        //adding listener to view
//        buttonRegister.setOnClickListener(this);
//    }
//
//    //storing token to mysql server
//    private void sendTokenToServer() {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Registering Device...");
//        progressDialog.show();
//
//        final String token = SharedPreferenceManager.getInstance(this).getDeviceToken();
//        final String email = editTextEmail.getText().toString();
//
//        if (token == null) {
//            progressDialog.dismiss();
//            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
//                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", email);
//                params.put("token", token);
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view == buttonRegister) {
//            sendTokenToServer();
//        }
//    }
//}
//
