package com.example.mrgupazz;

import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ProfileSettingFragment extends Fragment {
    EditText etFullName, etEmail, etPassword, etUsername;
    ImageView imgEditFullName;
    Button btnSave;
    ProgressDialog loading;
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Imd5bW5hc0BnbWFpbC5jb20iLCJyb2xlIjoidXNlciJ9.wg-RBSQNGjzo21GwpPWbaFqU0QEudhw8twq9b2g6PxA";
    private static final String TAG = "ProfileSettingFragment";
    private boolean isFullNameEditable = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profilesetting, container, false);

        etFullName = view.findViewById(R.id.et_FullName);
        etEmail = view.findViewById(R.id.et_Email);
        etPassword = view.findViewById(R.id.et_password);
        etUsername = view.findViewById(R.id.et_Username);

        imgEditFullName = view.findViewById(R.id.imageView7);
        btnSave = view.findViewById(R.id.btn_1);


        showData();

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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFullName();
            }
        });

        return view;
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

    private void showData() {
        loading = ProgressDialog.show(getActivity(), "Memuat Data...", "Tunggu...");
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://mrgupazzapi.pribadi-gymnas-2911.workers.dev/profile";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response);
                    String fullName = jo.getJSONObject("data").getString("name");
                    String email = jo.getJSONObject("data").getString("email");

                    etUsername.setText(fullName);
                    etFullName.setText(fullName);
                    etEmail.setText(email);
                    etPassword.setText("*********");
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Data berhasil dimuat", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(getActivity(), "Gagal memuat data: " + error, Toast.LENGTH_SHORT).show();
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

    private void saveFullName() {
        loading = ProgressDialog.show(getActivity(), "Menyimpan Data...", "Tunggu...");
        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
                    Toast.makeText(getActivity(), "Nama berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    etFullName.setFocusable(false);
                    showData();
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
                Toast.makeText(getActivity(), "Gagal memperbarui nama: " + error, Toast.LENGTH_SHORT).show();
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
