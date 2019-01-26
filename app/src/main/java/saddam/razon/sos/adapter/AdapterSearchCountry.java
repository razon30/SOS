package saddam.razon.sos.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.activity.CountryDetails;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.model.Country;
import saddam.razon.sos.utils.MyTextView;
import saddam.razon.sos.utils.SharePreferenceSingleton;

/**
 * Created by HP on 05-Jan-18.
 */

public class AdapterSearchCountry extends RecyclerView.Adapter<AdapterSearchCountry.MyViewHolder> {

    ArrayList<Country> keyValuePairs, filterList;
    Context context;
    LayoutInflater inflater;
    int type;

    public AdapterSearchCountry(ArrayList<Country> keyValuePairs, Context context, int type) {
        this.keyValuePairs = keyValuePairs;
        this.filterList = new ArrayList<>();
        this.filterList.addAll(keyValuePairs);
        this.context = context;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_country, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Log.d("dekhi",filterList.get(position).getName() );

        holder.region.setText(filterList.get(position).getName());
        holder.flag.setBackground(context.getResources().getDrawable(filterList.get(position).getFlagId()));
      //  holder.countryNumber.setText(Globals.getRegionDesc(keyValuePairs.get(position)));

//        holder.rootView.setOnClickListener(v->{
//
//
//
//        });

    }


    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public void filter(String text) {

        Log.d("dekhi",text );

        if (text == null || text.equals("")) {
            filterList.clear();
            filterList.addAll(keyValuePairs);
            notifyDataSetChanged();
            return;
        }

        filterList.clear();

        for (Country foundLost : keyValuePairs) {

            String caption = foundLost.getName().toLowerCase().trim();

            if (caption.contains(text.toLowerCase())){

                filterList.add(foundLost);

            }

        }

        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        MyTextView region;
      //  MyTextView countryNumber;
        LinearLayout flag;

        MyViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.region = rootView.findViewById(R.id.region);
          //  this.countryNumber = rootView.findViewById(R.id.country_number);
            this.flag = rootView.findViewById(R.id.flag);
        }
    }


}
