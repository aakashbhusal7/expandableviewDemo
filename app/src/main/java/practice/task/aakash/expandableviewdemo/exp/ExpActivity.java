//package practice.task.aakash.expandableviewdemo.exp;
//
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.NavigableMap;
//import java.util.TreeMap;
//
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.observers.DisposableObserver;
//import io.reactivex.schedulers.Schedulers;
//import practice.task.aakash.expandableviewdemo.MainActivity;
//import practice.task.aakash.expandableviewdemo.R;
//import practice.task.aakash.expandableviewdemo.databinding.ActivityExpandableBinding;
//import practice.task.aakash.expandableviewdemo.databinding.ListChildrenBinding;
//import practice.task.aakash.expandableviewdemo.databinding.ListHeaderBinding;
//import practice.task.aakash.expandableviewdemo.model.PurchaseOrder;
//import practice.task.aakash.expandableviewdemo.network.NetworkClient;
//import practice.task.aakash.expandableviewdemo.network.RestApi;
//
//public class ExpActivity extends AppCompatActivity {
//
//    private static String TAG=ExpActivity.class.getSimpleName();
//    private List<OrderHeader>orderHeaders= new ArrayList<>();
//    private Map<String,List<PurchaseOrder>> categoryMap;
//    private ArrayList<HashMap<String,String>> purchaseList;
//    ListChildrenBinding listChildrenBinding;
//    ListHeaderBinding listHeaderBinding;
//    ActivityExpandableBinding activityExpandableBinding;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        purchaseList= new ArrayList<HashMap<String, String>>();
//        categoryMap= new HashMap<>();
//        activityExpandableBinding= DataBindingUtil.setContentView(this,R.layout.activity_expandable);
//        listChildrenBinding=DataBindingUtil.setContentView(this,R.layout.list_children);
//        listHeaderBinding=DataBindingUtil.setContentView(this,R.layout.list_header);
//        fetchPurchaseApi();
//    }
//    private void fetchPurchaseApi(){
//        getObservable().subscribeWith(getObserver());
//    }
//
//    private Observable<List<PurchaseOrder>> getObservable() {
//        return NetworkClient.getRetrofit()
//                .create(RestApi.class)
//                .getPurchaseOrder()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    private DisposableObserver<List<PurchaseOrder>>getObserver(){
//        return new DisposableObserver<List<PurchaseOrder>>() {
//            @Override
//            public void onNext(List<PurchaseOrder> purchaseOrders) {
//                for (PurchaseOrder purchaseOrder:purchaseOrders){
//                    List<PurchaseOrder>purchaseOrderList=categoryMap.get(purchaseOrder.getOrderNumber());
//                    if(purchaseOrderList==null){
//                        purchaseOrderList=new ArrayList<>();
//                    }
//                    purchaseOrderList.add(purchaseOrder);
//                    categoryMap.put(purchaseOrder.getOrderNumber(),purchaseOrderList);
//                }
//                Log.d(TAG,"MAp: "+categoryMap.toString());
//                Iterator iterator=categoryMap.entrySet().iterator();
//                while(iterator.hasNext()){
//                    Map.Entry pair=(Map.Entry)iterator.next();
//                    Log.d("key",pair.getKey().toString());
//
//
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//    }
//
//    private class OrderHeader{
//        private int orderId;
//
//        public OrderHeader(int orderId){
//            this.orderId=orderId;
//        }
//
//        public int getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(int orderId) {
//            this.orderId = orderId;
//        }
//    }
//
//}
