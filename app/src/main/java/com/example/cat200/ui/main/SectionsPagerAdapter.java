package com.example.cat200.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cat200.FragmentGF;
import com.example.cat200.FragmentLF1;
import com.example.cat200.FragmentLF2;
import com.example.cat200.FragmentLF3;
import com.example.cat200.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                FragmentGF frag1 = new FragmentGF();
                return frag1;
            case 1:
                FragmentLF1 frag2 = new FragmentLF1();
                return frag2;
            case 2:
                FragmentLF2 frag3 = new FragmentLF2();
                return frag3;
            case 3:
                FragmentLF3 frag4 = new FragmentLF3();
                return frag4;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "GF";
            case 1:
                return "LG1";
            case 2:
                return "LG2";
            case 3:
                return "LG3";
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}