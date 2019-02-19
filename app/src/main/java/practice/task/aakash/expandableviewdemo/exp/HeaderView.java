package practice.task.aakash.expandableviewdemo.exp;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.SingleTop;

import practice.task.aakash.expandableviewdemo.R;

@Parent
@SingleTop
@Layout(R.layout.list_header)
public class HeaderView {
    private static String TAG = "HeaderView";

    private Context context;
    private String mHeaderText;

    @View(R.id.text1)
    TextView headerText;

    public HeaderView(Context context,String headerText) {
        this.context = context;
        this.mHeaderText = headerText;
    }

    @Resolve
    private void onResolve(){
        Log.d(TAG,"onResolve");
        headerText.setText(mHeaderText);
    }

    @Expand
    private void onExpand(){
        Log.d(TAG,"onExpand");
    }

    @Collapse
    private void onCollapse(){}
}
