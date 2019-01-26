package saddam.razon.sos.oldClass;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import saddam.razon.sos.R;
import saddam.razon.sos.data.Globals;

public class DivisionArrayAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;

    static class ViewHolder {
        public TextView textSummary;
        public TextView textView;

        ViewHolder() {
        }
    }

    public DivisionArrayAdapter(Activity context, String[] s) {
        super(context, R.layout.regionrowlayout, s);
        this.context = context;
        this.names = s;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;
        if (rowView == null) {
            rowView = this.context.getLayoutInflater().inflate(R.layout.regionrowlayout, null, true);
            holder = new ViewHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.label);
            holder.textSummary = (TextView) rowView.findViewById(R.id.textView_summary);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        holder.textView.setText(this.names[position]);
        if (this.names[position].equalsIgnoreCase("ALL COUNTRIES")) {
            holder.textView.setTextColor(Color.argb(255, 32, 128, 32));
        }
        holder.textSummary.setText(Globals.getRegionDesc(this.names[position]));
        return rowView;
    }
}
