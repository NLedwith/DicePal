package com.bignerdranch.android.ttrpgtracker.ui.npcs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bignerdranch.android.ttrpgtracker.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class NPCSFragment extends Fragment {

    private AppBarLayout appBar;
    private TabLayout tabs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_npcs, container, false);

        View viewContainer = (View)container.getParent();
        appBar = viewContainer.findViewById(R.id.appbar);
        tabs = new TabLayout(getActivity());
        tabs.setTabTextColors(R.color.colorPrimary, R.color.colorPrimaryDark);
        appBar.addView(tabs);

        ViewPager viewPager = view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        appBar.removeView(tabs);
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        private ViewPagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }

        String[] tabTitles = {"Standard", "Custom"};

        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0: return new TabStandardNPCSFragment();
                case 1: return new TabCustomNPCSFragment();
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return tabTitles[position];
        }
    }
}

