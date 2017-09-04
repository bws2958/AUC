package cloud.artik.example.hellocloud.Util.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 2017. 9. 3..
 */

public class Signin {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("signin")
    @Expose
    private Boolean signin;
    @SerializedName("emailCheck")
    @Expose
    private Boolean emailCheck;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getSignin() {
        return signin;
    }

    public void setSignin(Boolean signin) {
        this.signin = signin;
    }

    public Boolean getEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(Boolean emailCheck) {
        this.emailCheck = emailCheck;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
