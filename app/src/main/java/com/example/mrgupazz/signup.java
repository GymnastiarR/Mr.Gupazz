package com.example.mrgupazz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText edtfullname, edtemail, edtpassword ;
    private Button btnregister;
    private TextView tvsignuptologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtemail = findViewById(R.id.edt_email);
        edtfullname = findViewById(R.id.edt_fullname);
        edtpassword = findViewById(R.id.edt_password);
        btnregister = findViewById(R.id.btn_register);
        tvsignuptologin = findViewById(R.id.tv_signuptologin);

        tvsignuptologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtemail.getText().toString().isEmpty() || edtfullname.getText().toString().isEmpty() ||edtpassword.getText().toString().isEmpty()){
                    Toast.makeText(signup.this, "Ada data yang masih kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                postDataUsingVolley(edtemail.getText().toString(), edtfullname.getText().toString(), edtpassword.getText().toString());
            }
        });
    }

    private void postDataUsingVolley(String email, String name, String password){
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/register";
        RequestQueue queue = Volley.newRequestQueue(signup.this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("email", email);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                edtfullname.setText("");
                edtemail.setText("");
                edtpassword.setText("");

                Toast.makeText(signup.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // Mulai aktivitas login setelah berhasil mendaftar
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
                finish();  // Mengakhiri aktivitas saat ini
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signup.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}