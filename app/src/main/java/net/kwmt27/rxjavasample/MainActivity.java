package net.kwmt27.rxjavasample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GithubService githubService = new GithubService();
        githubService.fetchGithub(new Subscriber<GithubResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e);
            }

            @Override
            public void onNext(GithubResponse githubResponse) {
                Log.d(TAG, "onNext:" + githubResponse.getCurrentUserUrl());
            }
        });
    }
}
