package warmup.nikosstais.atcom.com.devtest2.presenters;

import android.support.v4.app.Fragment;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import warmup.nikosstais.atcom.com.devtest2.activity.main.MainActivityView;
import warmup.nikosstais.atcom.com.devtest2.adapters.SectionsPagerAdapter;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentHappenings;
import warmup.nikosstais.atcom.com.devtest2.remote.data.repositories.SpeakersRepository;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speakers;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.SpeakersResponse;

public class MainPresenter {

    private final MainActivityView mView;
    private final SectionsPagerAdapter mSectionsPagerAdapter;
    private Scheduler mMainScheduler;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MainPresenter(MainActivityView view, Scheduler mainScheduler, SectionsPagerAdapter mSectionsPagerAdapter) {
        mMainScheduler = mainScheduler;
        mView = view;

        this.mSectionsPagerAdapter = mSectionsPagerAdapter;
    }

    public void loadSpeakers(){
        mCompositeDisposable
                .add(SpeakersRepository.getInstance().getSpeakersRespone()
                        .subscribeOn(Schedulers.io())
                        .observeOn(mMainScheduler)
                        .subscribeWith(new DisposableSingleObserver<SpeakersResponse>() {
                            @Override
                            public void onSuccess(SpeakersResponse response) {
                                final String error = response.getError();
                                final Speakers speakers = response.getSpeakers();
                                final List<Speaker> speakerList = speakers.getSpeakerList();

                                final Fragment item = mSectionsPagerAdapter.getItem(0);
                                ((FragmentHappenings) item).displaySpeakers(speakerList);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                mView.displayError();
                            }
                        }));
    }


    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
