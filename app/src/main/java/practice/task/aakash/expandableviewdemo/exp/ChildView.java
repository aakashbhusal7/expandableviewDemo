package practice.task.aakash.expandableviewdemo.exp;

import android.content.Context;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import practice.task.aakash.expandableviewdemo.R;
import practice.task.aakash.expandableviewdemo.model.PurchaseOrder;

@Layout(R.layout.list_children)
public class ChildView {
    private static String TAG ="ChildView";

    @View(R.id.itemTitle)
    TextView textViewTitle;

    @View(R.id.itemQuantity)
    TextView textViewQuantity;

    @View(R.id.itemUnit)
    TextView textViewUnit;

    @View(R.id.total)
    TextView textViewTotal;

    private Context mContext;
    private PurchaseOrder purchaseOrder;

    public ChildView(Context mContext, PurchaseOrder purchaseOrder) {
        this.mContext = mContext;
        this.purchaseOrder = purchaseOrder;
    }

    @Resolve
    private void onResolve(){
        textViewTitle.setText(purchaseOrder.getItemName());
        textViewQuantity.setText(purchaseOrder.getQuantity().toString());
        textViewUnit.setText(purchaseOrder.getMuCode());
        textViewTotal.setText(purchaseOrder.getNetTotal().toString());
    }

}
