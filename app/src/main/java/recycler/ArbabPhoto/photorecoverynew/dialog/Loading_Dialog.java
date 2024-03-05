package recycler.ArbabPhoto.photorecoverynew.dialog;

import android.app.Dialog;
import android.content.Context;

import recycler.ArbabPhoto.photorecoverynew.R;


public class Loading_Dialog extends Dialog {

    private Context mContext;

    public Loading_Dialog(Context activity) {
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        this.mContext = activity;
        requestWindowFeature(1);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.layout_loading_dialog);

    }
}