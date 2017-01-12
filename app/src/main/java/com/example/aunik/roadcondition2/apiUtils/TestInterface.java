package com.example.aunik.roadcondition2.apiUtils;

import com.example.aunik.roadcondition2.model.RoadSegment;
import com.example.aunik.roadcondition2.model.Route;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by aunik on 1/5/17.
 */
/*
public interface TestInterface {
    @GET("posts/{post}")
    Call<Greeting> listRepos(@Path("post") int post);

    @POST("posts")
    Call<Greeting> sendData(@Body Greeting greeting);

}
*/

public interface TestInterface {

    @GET("segments/{placeId}")
    Call<RoadSegment> listRepos(@Path("placeId") String placeId);

    @POST("segments")
    Call<RoadSegment> sendData(@Body RoadSegment roadSegment);

    @Multipart
    @POST("segments/{placeId}/image")
    Call<ResponseBody> upload(@Path("placeId") String placeId,
                              @Part MultipartBody.Part file);


    @GET("routeconditions")
    Call<Route> getRouteCondition();

    @POST("routeconditions")
    Call<Route> getRouteConditionByRoute(@Body Route route);






}
