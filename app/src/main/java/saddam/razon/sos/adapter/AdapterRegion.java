package saddam.razon.sos.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.activity.CountryListActivity;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.utils.MyTextView;

/**
 * Created by HP on 05-Jan-18.
 */

public class AdapterRegion extends RecyclerView.Adapter<AdapterRegion.MyViewHolder> {

    ArrayList<String> keyValuePairs;
    Context context;
    LayoutInflater inflater;

    public AdapterRegion(ArrayList<String> keyValuePairs, Context context) {
        this.keyValuePairs = keyValuePairs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_region, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        setFlag(holder, keyValuePairs.get(position));
        holder.region.setText(keyValuePairs.get(position));
        holder.countryNumber.setText(Globals.getRegionDesc(keyValuePairs.get(position)));

//        holder.rootView.setOnClickListener(v->{
//
//            Bundle bundle = new Bundle();
//            bundle.putString("Division", keyValuePairs.get(position));
//            Intent explicitIntent = new Intent(context, CountryListActivity.class);
//            explicitIntent.putExtras(bundle);
//            context.startActivity(explicitIntent);
//
//        });

    }

    private void setFlag(MyViewHolder holder, String region) {

        switch (region) {

            case Globals.CON_ASIA:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.asia));
                return;
            case Globals.CON_AFRICA:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.africa));
                return;
            case Globals.CON_EUROPE:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.europe));
                return;
            case Globals.CON_AUST:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.australia_cont));
                return;
            case Globals.CON_NA:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.north_america));
                return;
            case Globals.CON_SA:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.south_america));
                return;
            case Globals.CON_CA:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.carribean));
                return;
            default:
                holder.flag.setBackground(context.getResources().getDrawable(R.drawable.back));

        }

    }

    @Override
    public int getItemCount() {
        return keyValuePairs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        MyTextView region;
        MyTextView countryNumber;
        LinearLayout flag;

        MyViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.region = rootView.findViewById(R.id.region);
            this.countryNumber = rootView.findViewById(R.id.country_number);
            this.flag = rootView.findViewById(R.id.flag);
        }
    }


}
