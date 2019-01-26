package saddam.razon.sos.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.adapter.AdapterSearchCountry;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.model.Country;
import saddam.razon.sos.utils.ClickListener;
import saddam.razon.sos.utils.RecyclerTOuchListener;
import saddam.razon.sos.utils.SharePreferenceSingleton;

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private RecyclerView recyclerView;
    AdapterSearchCountry adapter;
    StaggeredGridLayoutManager layoutManager;
    ArrayList<Country> countryList;

    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (getIntent().getIntExtra("type", 0) == 1){
            type = 1;
        }

        initView();
        adapter.notifyDataSetChanged();
        setupSearchView();
        worksOnDrawer();

    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here...");
    }

    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private void initView() {

        mSearchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view);
        countryList = new ArrayList<>();
        countryList = Globals.getCountryList(Globals.CON_ALL);
        layoutManager= new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterSearchCountry(countryList, SearchActivity.this, type);
        recyclerView.setAdapter(adapter);

        worksONInterstitial();
        worksOnRecyclerItem();

    }

    private void worksOnRecyclerItem() {
        recyclerView.addOnItemTouchListener(new RecyclerTOuchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onCLick(View v, int position) {
                if (type==1){
                    SharePreferenceSingleton.getInstance(SearchActivity.this).saveString(Globals.COUNTRY_NAME,countryList.get(position).getName());
                    SharePreferenceSingleton.getInstance(SearchActivity.this).saveString(Globals.CONTINENT_NAME,countryList.get(position).getContinent());
                }

                Bundle bundle = new Bundle();
                bundle.putString(Globals.COUNTRY_NAME, countryList.get(position).getName());
                bundle.putString("Division", countryList.get(position).getContinent());
                bundle.putInt("type", type);
                Intent explicitIntent = new Intent(SearchActivity.this, CountryDetails.class);
                explicitIntent.putExtras(bundle);
                showAds(explicitIntent);

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));
    }
}
