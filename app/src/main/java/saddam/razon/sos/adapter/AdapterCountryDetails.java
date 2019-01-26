package saddam.razon.sos.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.model.KeyValuePair;
import saddam.razon.sos.utils.MyTextView;

/**
 * Created by HP on 05-Jan-18.
 */

public class AdapterCountryDetails extends RecyclerView.Adapter<AdapterCountryDetails.MyViewHolder> {

    ArrayList<KeyValuePair> keyValuePairs;
    Context context;
    LayoutInflater inflater;

    public AdapterCountryDetails(ArrayList<KeyValuePair> keyValuePairs, Context context) {
        this.keyValuePairs = keyValuePairs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_number, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        KeyValuePair keyValuePair = keyValuePairs.get(position);
        holder.name.setText(keyValuePair.getKey());
       // String phone = keyValuePair.getValue();

        holder.phone.setText("Number: "+keyValuePair.getValue());

        holder.card.setOnClickListener(v->{

           // Toast.makeText(context, "Calling", Toast.LENGTH_SHORT).show();

            context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + keyValuePair.getValue())));

        });

    }

    @Override
    public int getItemCount() {
        return keyValuePairs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public MyTextView name;
        public MyTextView phone;
        public LinearLayout makeCall;
        LinearLayout card;

        public MyViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.name = rootView.findViewById(R.id.name);
            this.phone = rootView.findViewById(R.id.phone);
            this.makeCall = rootView.findViewById(R.id.make_call);
            this.card = rootView.findViewById(R.id.card);
        }
    }


}
