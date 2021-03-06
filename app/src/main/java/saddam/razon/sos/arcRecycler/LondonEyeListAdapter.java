package saddam.razon.sos.arcRecycler;

/**
 * Created by HP on 05-Jan-18.
 */

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import saddam.razon.sos.R;
import saddam.razon.sos.arcRecycler.utils.Config;

/**
 * Created by danylo.volokh on 10/17/2015.
 */
public class LondonEyeListAdapter extends RecyclerView.Adapter<LondonEyeListAdapter.PassengerCapsuleViewHolder> {

    private static final boolean SHOW_LOGS = Config.SHOW_LOGS;
    private static final String TAG = LondonEyeListAdapter.class.getSimpleName();

    private final FragmentActivity mActivity;
    private final List<String> mList;

    public LondonEyeListAdapter(FragmentActivity activity, List<String> list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public PassengerCapsuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(SHOW_LOGS) Log.v(TAG, "onCreateViewHolder, viewType " + viewType);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.capsule_layout, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = mActivity.getResources().getDisplayMetrics().widthPixels/3;
        layoutParams.width = layoutParams.height;
        return new PassengerCapsuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PassengerCapsuleViewHolder holder, int position) {
        if(SHOW_LOGS) Log.v(TAG, "onBindViewHolder, position " + position);
        holder.mCapsuleName.setText(mList.get(position));
        holder.mItemView.setTag(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class PassengerCapsuleViewHolder extends RecyclerView.ViewHolder {

        public final TextView mCapsuleName;
        public final View mItemView;

        public PassengerCapsuleViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mCapsuleName = (TextView) itemView.findViewById(R.id.capsule_name);
        }
    }
}
