package warmup.nikosstais.atcom.com.devtest2.activity.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import warmup.nikosstais.atcom.com.devtest2.R;
import warmup.nikosstais.atcom.com.devtest2.adapters.SectionsPagerAdapter;
import warmup.nikosstais.atcom.com.devtest2.fragments.OnFragmentInteractionListener;
import warmup.nikosstais.atcom.com.devtest2.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener, MainActivityView {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;

    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //instantiateItem(ViewPager pager, int position)
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        tabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(getTabIcon(i));
            tabLayout.getTabAt(i).setText(getString(getTabTitle(i)));
        }



//        presenter = new MainPresenter(this, AndroidSchedulers.mainThread(), mSectionsPagerAdapter);
//        presenter.loadSpeakers();
    }


    private int getTabTitle(int pos){
        switch(pos){
            case 0: return R.string.tab_text_1;
            case 1: return R.string.tab_text_2;
            case 2: return R.string.tab_text_3;
            case 3: return R.string.tab_text_4;
            case 4: return R.string.tab_text_5;
            default: return 0;
        }
    }
    private int getTabIcon(int pos){
        switch(pos){
            case 0: return R.mipmap.ic_tab_events_normal;
            case 1: return R.mipmap.ic_tab_shortlist_normal;
            case 2: return R.mipmap.ic_tab_awards_normal;
            case 3: return R.mipmap.ic_tab_agenda_normal;
            case 4: return R.mipmap.ic_tab_ceremony_normal;
            default: return R.mipmap.ic_ac_logo;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.barcode_menu || id == R.id.ticket_menu ) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(int position) {
        //could possible set dynamic information to view (per fragment here)
    }


    public void displayError() {
        Toast.makeText(this.getApplicationContext(),
                "Network Error, Please come back later",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

}
