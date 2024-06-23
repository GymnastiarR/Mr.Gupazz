package com.example.mrgupazz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
 // Import Log class
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView tvLoginToSignup;

    private static final String TAG = "login"; // Tag for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        tvLoginToSignup = findViewById(R.id.tv_logintosignup);

        tvLoginToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), signup.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Input validation
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(login.this, "Please enter your email and password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Perform login using Volley with POST request
                loginWithVolley(email, password);
            }
        });
    }

    private void loginWithVolley(String email, String password) {
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/login";
        RequestQueue queue = Volley.newRequestQueue(login.this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Assuming the response contains a field "data" which contains the "token"
                            String token = response.getJSONObject("data").getString("token");

                            // Save the token securely (SharedPreferences is used for demonstration)
                            getSharedPreferences("user_prefs", MODE_PRIVATE).edit().putString("token", token).apply();

                            // Proceed to the main activity
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent);
                            finish(); // Close the login activity

                        } catch (JSONException e) {
                            Toast.makeText(login.this, "Failed to parse response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle login error
                        Toast.makeText(login.this, "Login failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        queue.add(request);
    }
}
