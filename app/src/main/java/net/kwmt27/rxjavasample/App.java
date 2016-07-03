package net.kwmt27.rxjavasample;

import android.app.Application;

public class App extends Application {

    private static App sInstance;
    private ApiClient mApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mApiClient = new ApiClient();
    }

    public static App getInstance() {
        return sInstance;
    }

    public void setApiClient(ApiClient apiClient) {
        mApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return mApiClient;
    }
}
