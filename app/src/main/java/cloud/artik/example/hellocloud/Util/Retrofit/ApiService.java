package cloud.artik.example.hellocloud.Util.Retrofit;

import cloud.artik.example.hellocloud.Util.Retrofit.Response.NUCRecipe;
import cloud.artik.example.hellocloud.Util.Retrofit.Response.Signin;
import cloud.artik.example.hellocloud.Util.Retrofit.Response.Signup;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mac0314 on 2017-09-04.
 */

public interface ApiService {
    @GET("/auc/recipe/nuc/{recipeId}/{type}")
    Call<NUCRecipe> getNUCRecipe(@Path("recipeId") String recipeId,
                                 @Path("type") String type);


    @POST("/auc/user/signup")
    @FormUrlEncoded
    Call<Signup> userSignup(@Field("email") String email,
                            @Field("password") String password,
                            @Field("confirm") String confirm);
    @POST("/auc/user/signin")
    @FormUrlEncoded
    Call<Signin> userSignin(@Field("email") String email,
                            @Field("password") String password);
}
