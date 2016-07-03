package net.kwmt27.rxjavasample;

import com.google.gson.annotations.SerializedName;

public class GithubResponse {
    @SerializedName("current_user_url")
    private String mCurrentUserUrl;

    public String getCurrentUserUrl() {
        return mCurrentUserUrl;
    }
}
