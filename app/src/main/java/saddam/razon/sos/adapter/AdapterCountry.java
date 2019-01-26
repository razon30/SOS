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

/**
 * Created by HP on 05-Jan-18.
 */

public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.MyViewHolder> {

    ArrayList<Country> keyValuePairs;
    Context context;
    LayoutInflater inflater;
    int type;

    public AdapterCountry(ArrayList<Country> keyValuePairs, Context context, int type) {
        this.keyValuePairs = keyValuePairs;
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


        holder.region.setText(keyValuePairs.get(position).getName());
        holder.flag.setBackground(context.getResources().getDrawable(keyValuePairs.get(position).getFlagId()));
      //  holder.countryNumber.setText(Globals.getRegionDesc(keyValuePairs.get(position)));

        Log.d("fact", keyValuePairs.get(position).getName());

//        holder.rootView.setOnClickListener(v->{
//
//            Bundle bundle = new Bundle();
//            bundle.putString(Globals.COUNTRY_NAME, keyValuePairs.get(position).getName());
//            bundle.putString("Division", keyValuePairs.get(position).getContinent());
//            bundle.putInt("type", type);
//            Intent explicitIntent = new Intent(context, CountryDetails.class);
//            explicitIntent.putExtras(bundle);
//            context.startActivity(explicitIntent);
//
//        });

    }


    @Override
    public int getItemCount() {
        return keyValuePairs.size();
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
