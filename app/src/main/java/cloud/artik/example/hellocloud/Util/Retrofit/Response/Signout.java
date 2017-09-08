package cloud.artik.example.hellocloud.Util.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 2017. 9. 8..
 */

public class Signout {
    @SerializedName("signout")
    @Expose
    private Boolean signout;

    public Boolean getSignout() {
        return signout;
    }

    public void setSignout(Boolean signout) {
        this.signout = signout;
    }
}
