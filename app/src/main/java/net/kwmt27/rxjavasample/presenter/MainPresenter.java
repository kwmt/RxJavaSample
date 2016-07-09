package net.kwmt27.rxjavasample.presenter;

import android.util.Log;

import net.kwmt27.rxjavasample.ModelLocator;
import net.kwmt27.rxjavasample.entity.GithubResponse;

import rx.Subscriber;
import rx.Subscription;

public class MainPresenter implements IMainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private IMainView mMainView;

    private Subscription mSubscription;

    public MainPresenter(IMainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void onGetClick() {
        if(mSubscription != null && !mSubscription.isUnsubscribed()){
            Log.d(TAG, "キャンセル");
            mSubscription.unsubscribe();
            return;
        }
        Log.d(TAG, "実行");
        mSubscription = ModelLocator.getGithubService().fetchGithub(new Subscriber<GithubResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e);
                mMainView.updateTextView("error:" + e);
            }

            @Override
            public void onNext(GithubResponse githubResponse) {
                Log.d(TAG, "onNext:" + githubResponse.getCurrentUserUrl());
                mMainView.updateTextView(githubResponse.getCurrentUserUrl());
            }
        });

    }

    @Override
    public void onClearClick() {
        mMainView.updateTextView("クリアされました");
    }

    public interface IMainView {

        void updateTextView(String str);
    }
}
