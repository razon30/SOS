package saddam.razon.sos.oldClass;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import saddam.razon.sos.R;
import saddam.razon.sos.data.Globals;

public class CountryArrayAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;

    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;

        ViewHolder() {
        }
    }

    public CountryArrayAdapter(Activity context, String[] s) {
        super(context, R.layout.countryrowlayout, s);
        this.context = context;
        this.names = s;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            rowView = this.context.getLayoutInflater().inflate(R.layout.countryrowlayout, null, true);
            holder = new ViewHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.label);
            holder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        holder.textView.setText(this.names[position]);
        Log.d("name", names[position]);
        holder.imageView.setImageResource(Globals.getCountryFlagIds(this.names[position]));
        return rowView;
    }
}
