package com.example.mytabspeechtotext;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SpeechtotextPagerAdapter extends FragmentPagerAdapter {

    private int s2t_tab_num;
    public SpeechtotextPagerAdapter(@NonNull FragmentManager fm,int s2tbehavior, int s2ttabs) {
        super(fm,s2tbehavior);
        this.s2t_tab_num = s2ttabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new SpeechtotextXmlFragment();
            case 1:
                return new SpeechtotextJavaFragment();

            default: return null;

        }
    }

    @Override
    public int getCount() {
        return s2t_tab_num;
    }
}
