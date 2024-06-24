package com.example.mrgupazz;

import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class ProfileFragment extends Fragment {
    TextView tvName, tvSince;
    ImageView imgEditProfile;
    ProgressDialog loading;

    private static final String TAG = "ProfileFragment";

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        tvName = view.findViewById(R.id.tv_name);
        tvSince = view.findViewById(R.id.tv_since);
        imgEditProfile = view.findViewById(R.id.img_editProfile);
        showData(token);

        imgEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveWithNoData = new Intent(getActivity(), ProfileSettingActivity.class);
                startActivity(moveWithNoData);
            }
        });

        return view;
    }

    private void showData(String token) {
        loading = ProgressDialog.show(getActivity(), "Memuat Data...", "Tunggu...");
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/profile";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response);
                    String name = jo.getJSONObject("data").getString("name");
                    String join = jo.getJSONObject("data").getString("createdAt");

                    ZonedDateTime zdt = ZonedDateTime.parse(join);

                    // Formatter for the desired output format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));

                    // Formatting the ZonedDateTime object to the desired format
                    String output = zdt.format(formatter);

                    tvName.setText(name);
                    tvSince.setText("Join Since " + output);
                    loading.dismiss();
                    Toast.makeText(getActivity(), "berhasil", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Request error: " + error.toString());
                loading.dismiss();
                Toast.makeText(getActivity(), "gagal: " + error, Toast.LENGTH_SHORT).show();
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
}
