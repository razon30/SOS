package saddam.razon.sos.activity;

import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.oldClass.CountryArrayAdapter;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.model.Country;
import saddam.razon.sos.utils.SharePreferenceSingleton;
import saddam.razon.sos.utils.SimpleActivityTransition;

public class MainActivity extends BaseActivity {
    String dName = Globals.CON_ALL;
    LinearLayout select_your_country;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        skip.setOnClickListener(v->{
            SimpleActivityTransition.goToNextActivity(this, RegionActivity.class);
            finish();
        });
        select_your_country.setOnClickListener(v->
        {
            worksOnCountrySelect();
        });


    }

    private void worksOnCountrySelect() {


        SimpleActivityTransition.goToNextActivity(this, SearchActivity.class, 1);
        finish();



    }

    private void init() {
        select_your_country = findViewById(R.id.select_your_country);
        skip = findViewById(R.id.skip);
//        this.dName = getIntent().getExtras().getString("Division");
    }
}
