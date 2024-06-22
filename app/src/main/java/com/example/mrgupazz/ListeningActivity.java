package com.example.mrgupazz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ListeningActivity extends AppCompatActivity {

    private ImageView ivMainImage, ivSoundIcon;
    private Button btn1, btn2, btnCheck;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);

        ivMainImage = findViewById(R.id.iv_main_image);
        ivSoundIcon = findViewById(R.id.iv_sound_icon);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btnCheck = findViewById(R.id.btn_check);

        fetchListeningData();

        ivSoundIcon.setOnClickListener(v -> playSound());
    }

    private void fetchListeningData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your.api.base.url/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getListeningData().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void updateUI(ApiResponse data) {
        Glide.with(this).load(data.getImageUrl()).into(ivMainImage);
        btn1.setText(data.getOption1());
        btn2.setText(data.getOption2());

        ivSoundIcon.setTag(data.getSoundUrl());
    }

    private void playSound() {
        String soundUrl = (String) ivSoundIcon.getTag();
        if (soundUrl != null) {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(soundUrl);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
