package com.example.mrgupazz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileSettingActivity extends AppCompatActivity {
    EditText etFullName, etEmail, etPassword;
    ImageView imgEditFullName, imgBack;
    Button btnSave, btnLogout;
    ProgressDialog loading;
    private static final String TAG = "ProfileSettingActivity";
    private boolean isFullNameEditable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        etFullName = findViewById(R.id.et_FullName);
        etEmail = findViewById(R.id.et_Email);
        etPassword = findViewById(R.id.et_password);

        imgEditFullName = findViewById(R.id.imageView7);
        imgBack = findViewById(R.id.imageView6);

        btnSave = findViewById(R.id.btn_1);
        btnLogout = findViewById(R.id.btn_2);

        showData(token);

        imgEditFullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFullNameEditable) {
                    enableEditText(etFullName);
                } else {
                    disableEditText(etFullName);
                }
                isFullNameEditable = !isFullNameEditable;
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithNoData = new Intent(ProfileSettingActivity.this, ProfileFragment.class);
                startActivity(moveWithNoData);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFullName(token);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent moveWithNoData = new Intent(ProfileSettingActivity.this, login.class);
                startActivity(moveWithNoData);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFullName(token);
            }
        });
    }

    private void enableEditText(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.requestFocus();
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setInputType(InputType.TYPE_NULL);
    }

    private void showData(String token) {
        loading = ProgressDialog.show(this, "Memuat Data...", "Tunggu...");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/profile";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response);
                    String fullName = jo.getJSONObject("data").getString("name");
                    String email = jo.getJSONObject("data").getString("email");

                    etFullName.setText(fullName);
                    etEmail.setText(email);
                    etPassword.setText("*********");
                    loading.dismiss();

                } catch (JSONException e) {
                    Log.e(TAG, "Error Getting Data: " + e.getMessage());
                    loading.dismiss();
                    Toast.makeText(ProfileSettingActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Request error: " + error.toString());
                loading.dismiss();
                Toast.makeText(ProfileSettingActivity.this, "Gagal memuat data: " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private void saveFullName(String token) {
        loading = ProgressDialog.show(this, "Menyimpan Data...", "Tunggu...");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/profile";

        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response);
                    String fullName = jo.getJSONObject("data").getString("name");

                    etFullName.setText(fullName);
                    loading.dismiss();
                    Toast.makeText(ProfileSettingActivity.this, "Nama berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    etFullName.setFocusable(false);
                    showData(token);
                } catch (JSONException e) {
                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                    loading.dismiss();
                    Toast.makeText(ProfileSettingActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Request error: " + error.toString());
                loading.dismiss();
                Toast.makeText(ProfileSettingActivity.this, "Gagal memperbarui nama: " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", etFullName.getText().toString());
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        queue.add(stringRequest);
    }
}
