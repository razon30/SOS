package saddam.razon.sos.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.adapter.AdapterCountryDetails;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.model.Country;
import saddam.razon.sos.model.KeyValuePair;
import saddam.razon.sos.utils.NetworkAvailable;
import saddam.razon.sos.utils.SharePreferenceSingleton;

public class CountryDetails extends BaseActivity {

    Country country;
    String name, region;
    int type;
    ArrayList<KeyValuePair> keyValuePairs;

    LinearLayout empty;
    RelativeLayout details;

    TextView tvName;
    ImageView country_image;
    RecyclerView list;

    StaggeredGridLayoutManager layoutManager;
    AdapterCountryDetails adapter;

//    List<String> mList;
//    DebugRecyclerView mRecyclerView;
//    LondonEyeLayoutManager mLondonEyeLayoutManager;
//    LondonEyeListAdapter mVideoRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        init();
        defineName();
        country = Globals.getCountry(name);
        worksOnData();
        worksOnSearch();
        worksOnDrawer();

    }

    private void worksOnData() {

        if (country != null) {
            keyValuePairs.addAll(country.getNumbers());
            keyValuePairs.addAll(Globals.getDB(this).getTels(country.getName()));
        }
        if (keyValuePairs == null || keyValuePairs.size() < 1) {
            setEmptyMessage();
        } else {
            worksOnDetails();
        }

    }

    private void defineName() {

        if (type == 1) {
            name = SharePreferenceSingleton.getInstance(this).getString(Globals.COUNTRY_NAME);
        } else {
            name = getIntent().getStringExtra(Globals.COUNTRY_NAME);
        }

    }

    private void worksOnDetails() {
        details.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);

        tvName.setText(name);
        country_image.setBackground(getResources().getDrawable(country.getFlagId()));
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        list.setLayoutManager(layoutManager);
        adapter = new AdapterCountryDetails(keyValuePairs, CountryDetails.this);
        list.setAdapter(adapter);

    }

    private void init() {
        type = getIntent().getIntExtra("type", 0);
        details = findViewById(R.id.details);
        empty = findViewById(R.id.empty);
        tvName = findViewById(R.id.name);
        country_image = findViewById(R.id.country_image);
        list = findViewById(R.id.list);
        //mList = new ArrayList<>();
        details.setVisibility(View.GONE);
        empty.setVisibility(View.GONE);
        Globals.default_opened = true;
        keyValuePairs = new ArrayList<>();
        worksOnAds();
    }

    private void setEmptyMessage() {

        details.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);

    }

}
