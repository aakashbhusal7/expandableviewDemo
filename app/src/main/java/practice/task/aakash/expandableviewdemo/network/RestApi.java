package practice.task.aakash.expandableviewdemo.network;


import java.util.List;

import io.reactivex.Observable;
import practice.task.aakash.expandableviewdemo.model.PurchaseOrder;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("getPO")
    Observable<List<PurchaseOrder>> getPurchaseOrders();

    @GET("getPO")
    Call<List<PurchaseOrder>>getPurchaseOrder();
}
