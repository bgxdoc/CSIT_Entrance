package np.com.aawaz.csitentrance.fragments.navigation_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;

import np.com.aawaz.csitentrance.R;
import np.com.aawaz.csitentrance.activities.MainActivity;
import np.com.aawaz.csitentrance.fragments.other_fragments.AllColleges;
import np.com.aawaz.csitentrance.fragments.other_fragments.FeaturedColleges;

public class CSITColleges extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    private String mUrl;
    private String mTitle;

    public CSITColleges() {
        // Required empty public constructor
    }

    public static CSITColleges newInstance() {
        return new CSITColleges();
    }


    private void appIndexing() {
        mUrl = "http://csitentrance.brainants.com/colleges";
        mTitle = "Colleges of Bsc CSIT in Nepal";
    }


    public Action getAction() {
        return Actions.newView(mTitle, mUrl);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUserActions.getInstance().start(getAction());
    }

    @Override
    public void onStop() {
        FirebaseUserActions.getInstance().end(getAction());
        super.onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout.removeAllTabs();
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0)
                    return FeaturedColleges.newInstance();
                else
                    return AllColleges.newInstance();
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0)
                    return "Featured";
                else
                    return "All";
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        appIndexing();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = MainActivity.tabLayout;
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerHome);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }
}
