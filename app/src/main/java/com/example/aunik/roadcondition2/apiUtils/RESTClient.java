package com.example.aunik.roadcondition2.apiUtils;

/**
 * Created by aunik on 1/4/17.
 */

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.example.aunik.roadcondition2.model.RoadSegment;
import com.example.aunik.roadcondition2.model.Route;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;

import static android.content.ContentValues.TAG;

public class RESTClient {
    Context context;

    public RESTClient(Context context) {
        this.context = context;
    }

    //String baseUrl = "https://jsonplaceholder.typicode.com";
    String baseUrl = "http://10.0.2.2:8080";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    TestInterface service = retrofit.create(TestInterface.class);

    public void sendData(){

        RoadSegment g = new RoadSegment("123",1, null, null);
        Call<RoadSegment> repos = service.sendData(g);
        repos.enqueue(new Callback<RoadSegment>() {
            @Override
            public void onResponse(Call<RoadSegment>call, Response<RoadSegment> response) {
                RoadSegment roadSegment = response.body();
                Log.d(TAG, "*******post completed " + roadSegment.getPlaceId()  );
            }

            @Override
            public void onFailure(Call<RoadSegment>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    public void sendSegmentData(RoadSegment roadSegment){

        Call<RoadSegment> repos = service.sendData(roadSegment);
        repos.enqueue(new Callback<RoadSegment>() {
            @Override
            public void onResponse(Call<RoadSegment>call, Response<RoadSegment> response) {
                RoadSegment roadSegment = response.body();
                Log.d(TAG, "*******post completed " + roadSegment.getPlaceId()  );
            }

            @Override
            public void onFailure(Call<RoadSegment>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    public void getData(){
        RoadSegment roadSegment;
        Call<RoadSegment> repos = service.listRepos("123");
        repos.enqueue(new Callback<RoadSegment>() {
            @Override
            public void onResponse(Call<RoadSegment>call, Response<RoadSegment> response) {
                //List<Movie> movies = response.body().getResults();
                int statusCode = response.code();
                RoadSegment roadSegment = response.body();

                Log.d(TAG, "Number of movies received: " +roadSegment.getPlaceId());
            }

            @Override
            public void onFailure(Call<RoadSegment>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


 //////////////////// for route
    public void getRouteCondition(){

        Route r = Route.getSampleRoute();
        Call<Route> repos = service.getRouteConditionByRoute(r);
        repos.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route>call, Response<Route> response) {
                Route route = response.body();
                Log.d(TAG, "*******post completed " + route.getId()  );
            }

            @Override
            public void onFailure(Call<Route>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    public void uploadImage(Uri imageUri){
        /*File file = new File(context.getExternalFilesDir(null), "TraceFile.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e("com.FileTest", "Unable to open  TraceFile.txt file.");
            }
        }
*/
        File file = new File(imageUri.getPath());

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

     /*   // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

*/

       // MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        Call<ResponseBody> call = service.upload("123",body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }


}
