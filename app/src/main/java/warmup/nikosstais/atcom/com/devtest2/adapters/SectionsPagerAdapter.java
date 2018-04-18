package warmup.nikosstais.atcom.com.devtest2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import warmup.nikosstais.atcom.com.devtest2.activity.main.MainActivity;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentAgenda;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentAwards;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentCeremony;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentHappenings;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentShortList;
import warmup.nikosstais.atcom.com.devtest2.fragments.PlaceholderFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position){
            case 0: return FragmentHappenings.newInstance(position + 1);
            case 1: return FragmentShortList.newInstance(position + 1);
            case 2: return FragmentAwards.newInstance(position + 1);
            case 3: return FragmentAgenda.newInstance(position + 1);
            case 4: return FragmentCeremony.newInstance(position + 1);
            default: return FragmentHappenings.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}