
package com.example.Sanskrit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewpager = findViewById(R.id.viewpager);
        myFragmentPagerAdapter fm = new myFragmentPagerAdapter(this,getSupportFragmentManager());
        viewpager.setAdapter(fm);
        TabLayout tablayout = findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewpager);

    }
}
