package cloud.artik.example.hellocloud.Util.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 2017. 9. 3..
 */

public class Signup {
    @SerializedName("atCheck")
    @Expose
    private Boolean atCheck;
    @SerializedName("confirm")
    @Expose
    private Boolean confirm;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("duplicate")
    @Expose
    private Boolean duplicate;
    @SerializedName("signup")
    @Expose
    private Boolean signup;
    @SerializedName("signin")
    @Expose
    private Boolean signin;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;

    public Boolean getAtCheck() {
        return atCheck;
    }

    public void setAtCheck(Boolean atCheck) {
        this.atCheck = atCheck;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(Boolean duplicate) {
        this.duplicate = duplicate;
    }

    public Boolean getSignup() {
        return signup;
    }

    public void setSignup(Boolean signup) {
        this.signup = signup;
    }

    public Boolean getSignin() {
        return signin;
    }

    public void setSignin(Boolean signin) {
        this.signin = signin;
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
