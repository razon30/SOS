package saddam.razon.sos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.adapter.AdapterCountry;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.model.Country;
import saddam.razon.sos.utils.ClickListener;
import saddam.razon.sos.utils.NetworkAvailable;
import saddam.razon.sos.utils.RecyclerTOuchListener;

public class CountryListActivity extends BaseActivity {
    String dName = Globals.CON_ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        this.dName = getIntent().getExtras().getString("Division");
        worksOnCountrySelect();
        worksOnDrawer();
        worksOnSearch();
    }

    private void worksOnCountrySelect() {

        ArrayList<Country> countryList = Globals.getCountryList(this.dName);
        RecyclerView listView = findViewById(R.id.list);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new AdapterCountry(countryList, CountryListActivity.this, 0));

        worksOnAds();

        listView.addOnItemTouchListener(new RecyclerTOuchListener(CountryListActivity.this, listView, new ClickListener() {
            @Override
            public void onCLick(View v, int position) {

                Bundle bundle = new Bundle();
                bundle.putString(Globals.COUNTRY_NAME, countryList.get(position).getName());
                bundle.putString("Division", countryList.get(position).getContinent());
                bundle.putInt("type", 0);
                Intent explicitIntent = new Intent(CountryListActivity.this, CountryDetails.class);
                explicitIntent.putExtras(bundle);
                explicitIntent.putExtras(bundle);

                showAds(explicitIntent);

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));

    }


}
