// MainActivity.java
package com.example.mrgupazz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            LearningFragment fragment = new LearningFragment();
            transaction.replace(R.id.fragment_container, fragment);
//            ProfileSettingFragment fragment = new ProfileSettingFragment();
//            transaction.replace(R.id.fragment_container, fragment);
//            AiAssistantFragment fragment = new AiAssistantFragment();
//            transaction.replace(R.id.fragment_container,fragment);
            transaction.commit();
        }
    }
}
