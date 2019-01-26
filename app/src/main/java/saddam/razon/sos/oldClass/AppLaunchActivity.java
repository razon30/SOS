package saddam.razon.sos.oldClass;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import saddam.razon.sos.data.Globals;
import saddam.razon.sos.R;

public class AppLaunchActivity extends ListActivity {
    String headerText = "Select Region";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Globals.initDB(this);
        View header = getLayoutInflater().inflate(R.layout.header_region, null);
        TextView tv = header.findViewById(R.id.textView_header);
        if (tv != null) {
            tv.setText(this.headerText);
        }
        TextView tv2 = header.findViewById(R.id.textView_header2);
        if (tv2 != null) {
            tv2.setVisibility(View.GONE);
        }
        getListView().addHeaderView(header);
        setListAdapter(new DivisionArrayAdapter(this, Globals.getDivisions().toArray(new String[0])));
        if (!Globals.default_opened && Globals.default_region.length() > 0 && Globals.default_country.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString("Division", Globals.default_region);
            Intent explicitIntent = new Intent(this, CountryList.class);
            explicitIntent.putExtras(bundle);
            startActivity(explicitIntent);
        }
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (position > 0) {
            String item = (String) getListAdapter().getItem(position - 1);
            if (!item.equalsIgnoreCase(this.headerText)) {
                Bundle bundle = new Bundle();
                bundle.putString("Division", item);
                Intent explicitIntent = new Intent(this, CountryList.class);
                explicitIntent.putExtras(bundle);
                startActivity(explicitIntent);
            }
        }
    }
}
