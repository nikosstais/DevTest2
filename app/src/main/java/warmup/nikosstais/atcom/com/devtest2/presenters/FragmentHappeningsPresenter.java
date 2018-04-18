package warmup.nikosstais.atcom.com.devtest2.presenters;

import android.support.v4.app.Fragment;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentHappenings;
import warmup.nikosstais.atcom.com.devtest2.fragments.views.FragmentHappeningsView;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speakers;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.SpeakersResponse;
import warmup.nikosstais.atcom.com.devtest2.remote.data.repositories.SpeakersRepository;

public class FragmentHappeningsPresenter {

    private final FragmentHappeningsView view;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Scheduler mMainScheduler;

    public FragmentHappeningsPresenter(FragmentHappeningsView view, Scheduler mainScheduler) {
        this.view = view;
        mMainScheduler = mainScheduler;
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

                                view.displaySpeakers(speakerList);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        }));
    }

    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}



