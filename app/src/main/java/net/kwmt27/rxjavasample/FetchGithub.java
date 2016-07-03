package net.kwmt27.rxjavasample;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FetchGithub {

    private static final String TAG = FetchGithub.class.getSimpleName();

    public void requestGithub(Subscriber<GithubResponse> subscriber) {
        final String path = "/";
        Observable.fromCallable(
                new Callable<GithubResponse>() {
                    @Override
                    public GithubResponse call() throws Exception {
                        ApiClient apiClient = App.getInstance().getApiClient();
                        Response response = apiClient.request(path, null);
                        return parseGithubResponse(response);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


    private Gson gson = new GsonBuilder()
            .create();


    private GithubResponse parseGithubResponse(Response response) throws Exception {
        String json = "";
        try {
            json = response.body().string();
            JSONObject jsonObject = new JSONObject(json);
            Log.d(TAG, jsonObject.toString(4));
            return gson.fromJson(jsonObject.toString(), GithubResponse.class);
        } catch (IOException e) {
            throw new IOException(e);
        } catch (JsonParseException e) {
            throw new JsonParseException(e.getMessage());
        } catch (JSONException e) {
            throw new JSONException(e.getMessage());
        }
    }
}
