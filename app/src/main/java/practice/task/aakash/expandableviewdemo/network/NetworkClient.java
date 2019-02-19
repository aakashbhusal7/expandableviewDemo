package practice.task.aakash.expandableviewdemo.network;

import okhttp3.OkHttpClient;
import practice.task.aakash.expandableviewdemo.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static Retrofit retrofit;

    public NetworkClient(){}

    public static Retrofit getRetrofit(){
            if(retrofit==null){
                OkHttpClient okHttpClient= new OkHttpClient.Builder().build();

                retrofit=new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
            return retrofit;
    }
}
