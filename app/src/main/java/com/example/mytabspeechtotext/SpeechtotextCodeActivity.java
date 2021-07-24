package com.example.mytabspeechtotext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SpeechtotextCodeActivity extends AppCompatActivity {

    TabLayout st_tab_layout;
    TabItem st_xml,st_java;
    ViewPager st_vp;
   SpeechtotextPagerAdapter st_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speechtotext_code);

        st_tab_layout=(TabLayout)findViewById(R.id.speech2text_tab_layout);
        st_xml=(TabItem)findViewById(R.id.speech2text_xml_code);
        st_java=(TabItem)findViewById(R.id.speech2text_java_code);
        st_vp=(ViewPager)findViewById(R.id.speech2text_view_pager);

        st_adapter = new SpeechtotextPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,st_tab_layout.getTabCount());
        st_vp.setAdapter(st_adapter);

        st_vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(st_tab_layout));

        st_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                st_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}