package saddam.razon.sos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.google.android.gms.ads.AdListener;

import saddam.razon.sos.R;
import saddam.razon.sos.adapter.AdapterCountry;
import saddam.razon.sos.adapter.AdapterRegion;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.utils.ClickListener;
import saddam.razon.sos.utils.MyRecyclerView;
import saddam.razon.sos.utils.NetworkAvailable;
import saddam.razon.sos.utils.RecyclerTOuchListener;

public class RegionActivity extends BaseActivity {

    MyRecyclerView list;
    AdapterRegion adapter;
    StaggeredGridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        initialization();
        worksOnDrawer();
        worksOnSearch();

    }

    private void initialization() {
        list = findViewById(R.id.list);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        list.setLayoutManager(layoutManager);
        adapter = new AdapterRegion(Globals.getDivisions(), this);
        list.setAdapter(adapter);
        worksOnAds();
        worksOnRecyclerItem();
    }

    private void worksOnRecyclerItem() {
        list.addOnItemTouchListener(new RecyclerTOuchListener(this, list, new ClickListener() {
            @Override
            public void onCLick(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("Division", Globals.getDivisions().get(position));
                Intent explicitIntent = new Intent(RegionActivity.this, CountryListActivity.class);
                explicitIntent.putExtras(bundle);
                showAds(explicitIntent);
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));
    }

}
