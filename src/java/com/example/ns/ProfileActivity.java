package com.example.ns;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.ns.Tab1.OnFragmentInteractionListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements OnFragmentInteractionListener, Tab2.OnFragmentInteractionListener, Tab3.OnFragmentInteractionListener {
    private Button buttonLogout;
    private FirebaseAuth firebaseAuth;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Class"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(0);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.setOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
            public void onTabSelected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(Tab tab) {
            }

            public void onTabReselected(Tab tab) {
            }
        });
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.firebaseAuth = instance;
        if (instance.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = this.firebaseAuth.getCurrentUser();
    }

    public void onFragmentInteraction(Uri uri) {
    }
}
