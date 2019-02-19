package practice.task.aakash.expandableviewdemo.exp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import practice.task.aakash.expandableviewdemo.R;
import practice.task.aakash.expandableviewdemo.model.PurchaseOrder;
import practice.task.aakash.expandableviewdemo.network.NetworkClient;
import practice.task.aakash.expandableviewdemo.network.RestApi;

public class ExpandActivity extends AppCompatActivity {
    private static String TAG=ExpandActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private Map<String, List<PurchaseOrder>> categoryMap;
    private List<PurchaseOrder>purchaseOrderList;
    private ExpandablePlaceHolderView expandablePlaceHolderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        purchaseOrderList= new ArrayList<>();
        categoryMap= new HashMap<>();
        expandablePlaceHolderView=(ExpandablePlaceHolderView)findViewById(R.id.expandablePlaceHolder);
        fetchData();
        expandablePlaceHolderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Clixcked", view.getId()).show();
            }
        });
    }
    private void fetchData(){
        this.progressDialog= new ProgressDialog(this);
        this.progressDialog.setMessage("Generating Purchase Order Statement");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
        getObservable().subscribeWith(getObserver());
    }

    private Observable<List<PurchaseOrder>> getObservable() {
        return NetworkClient.getRetrofit()
                .create(RestApi.class)
                .getPurchaseOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    private DisposableObserver<List<PurchaseOrder>> getObserver(){
        return new DisposableObserver<List<PurchaseOrder>>() {
            @Override
            public void onNext(List<PurchaseOrder> purchaseOrders) {
                getHeaderAndChild(purchaseOrders);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        };

    }

    private void getHeaderAndChild(List<PurchaseOrder>purchaseOrderList){
        for(PurchaseOrder purchaseOrder:purchaseOrderList){
            List<PurchaseOrder>purchaseOrderList1=categoryMap.get(purchaseOrder.getOrderNumber());
            if(purchaseOrderList1==null){
                purchaseOrderList1= new ArrayList<>();
            }
            purchaseOrderList1.add(purchaseOrder);
            categoryMap.put(purchaseOrder.getOrderNumber(),purchaseOrderList1);
        }
        Log.d("MAP",categoryMap.toString());
        Iterator iterator=categoryMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair= (Map.Entry)iterator.next();
            Log.d("key",pair.getKey().toString());
            expandablePlaceHolderView.addView(new HeaderView(this,pair.getKey().toString()));
            List<PurchaseOrder>purchaseOrderList1=(List<PurchaseOrder>)pair.getValue();
            for(PurchaseOrder purchaseOrder:purchaseOrderList1){
                expandablePlaceHolderView.addView(
                        new ChildView(this,purchaseOrder));
            }
            iterator.remove();
            }
        }
    }
