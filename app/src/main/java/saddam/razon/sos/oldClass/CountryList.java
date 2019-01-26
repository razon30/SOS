package saddam.razon.sos.oldClass;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import saddam.razon.sos.data.Globals;
import saddam.razon.sos.R;

public class CountryList extends ListActivity {

    String dName = "";
    String headerText = "Select Country";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dName = getIntent().getExtras().getString("Division");
        View header = getLayoutInflater().inflate(R.layout.header_country, null);
        TextView tv = header.findViewById(R.id.textView_header);
        TextView tv2 = header.findViewById(R.id.textView_header2);
        if (tv != null) {
            tv.setText(this.headerText);
        }
        if (tv2 != null) {
            tv2.setText(this.dName);
        }
        getListView().addHeaderView(header);
        setListAdapter(new CountryArrayAdapter(this, Globals.getCountryNames(this.dName).toArray(new String[0])));
        if (!Globals.default_opened && Globals.default_region.length() > 0 && Globals.default_country.length() > 0) {
            Bundle bundleo = new Bundle();
            bundleo.putString("Country", Globals.default_country);
            bundleo.putString("Division", this.dName);
            Intent explicitIntent = new Intent(this, CountryNumbersList.class);
            explicitIntent.putExtras(bundleo);
            startActivity(explicitIntent);
        }
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (position > 0) {
            String item = (String) getListAdapter().getItem(position - 1);
            if (!item.equalsIgnoreCase(this.headerText)) {
                Bundle bundle = new Bundle();
                bundle.putString("Country", item);
                bundle.putString("Division", this.dName);
                Intent explicitIntent = new Intent(this, CountryNumbersList.class);
                explicitIntent.putExtras(bundle);
                startActivity(explicitIntent);
            }
        }
    }
}
