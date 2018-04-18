package warmup.nikosstais.atcom.com.devtest2.presenters;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import warmup.nikosstais.atcom.com.devtest2.activity.main.MainActivityView;
import warmup.nikosstais.atcom.com.devtest2.remote.data.helpers.SpeakersRepository;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speakers;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.SpeakersResponse;

public class MainPresenter {

    private final MainActivityView mView;
    private Scheduler mMainScheduler;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MainPresenter(MainActivityView view, Scheduler mainScheduler) {
        mMainScheduler = mainScheduler;
        mView = view;
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

                                mView.displaySpeakers();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        }));
    }
}
