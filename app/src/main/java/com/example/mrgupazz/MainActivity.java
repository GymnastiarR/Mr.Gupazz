package com.example.mrgupazz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.mrgupazz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        if(token == null){
            Intent intent = new Intent(this, InitialActivity.class);
            startActivity(intent);
            return;
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            String targetFragment = intent.getStringExtra("targetFragment");
            if (targetFragment != null && targetFragment.equals("LearningFragment")) {
                NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
                // Pindah ke fragment tujuan
                navController.navigate(R.id.menu2);
                return;
            }
        }

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(binding.fragmentContainerView.getId());

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());
    }
}
