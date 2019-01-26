package saddam.razon.sos.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.codemybrainsout.ratingdialog.RatingDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;

import saddam.razon.sos.R;
import saddam.razon.sos.adapter.AdapterCountryDetails;
import saddam.razon.sos.oldClass.CountryArrayAdapter;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.model.Country;
import saddam.razon.sos.utils.NetworkAvailable;
import saddam.razon.sos.utils.SharePreferenceSingleton;
import saddam.razon.sos.utils.SimpleActivityTransition;

/**
 * Created by HP on 06-Jan-18.
 */

public class BaseActivity extends AppCompatActivity {

    public void worksOnSearch() {

        ImageView search = findViewById(R.id.search);
        search.setOnClickListener(v -> {
            SimpleActivityTransition.goToNextActivity(this, SearchActivity.class);
        });

    }

    InterstitialAd mInterstitialAd;
    AdView mAdView;
    AdRequest adRequest;

    public void worksOnAds() {

        worksOnBanner();
        worksONInterstitial();
        // mInterstitialAd.show();

    }

    public void worksONInterstitial() {

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void worksOnBanner() {

        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                mAdView.setVisibility(View.GONE);
            }
        });


    }

    public void showAds(Intent explicitIntent){

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startActivity(explicitIntent);
            }
        });

        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startActivity(explicitIntent);
        }

    }


    public void worksOnDrawer() {

        SlidingRootNavBuilder slidingRootNavBuilder;
        Toolbar toolbar;


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        slidingRootNavBuilder = new SlidingRootNavBuilder(this);
        slidingRootNavBuilder.withToolbarMenuToggle(toolbar)
                .withMenuLayout(R.layout.layout_drawer)
                .withDragDistance(140) //Horizontal translation of a view. Default == 180dp
                .withRootViewScale(0.7f) //Content view's scale will be interpolated between 1f and 0.7f. Default == 0.65f;
                .withRootViewElevation(10) //Content view's elevation will be interpolated between 0 and 10dp. Default == 8.
                .withRootViewYTranslation(4) //Cont
                .withGravity(SlideGravity.LEFT)
                .inject();

        MaterialRippleLayout yourCountry = findViewById(R.id.yourCountry);
        MaterialRippleLayout changeYourCountry = findViewById(R.id.changeYourCountry);
        MaterialRippleLayout contenents = findViewById(R.id.continents);
        MaterialRippleLayout allCountries = findViewById(R.id.allCountries);
        MaterialRippleLayout rateTheApp = findViewById(R.id.rateTheApp);
        MaterialRippleLayout otherApps = findViewById(R.id.otherApp);
        MaterialRippleLayout share = findViewById(R.id.shareTheApp);

        if (SharePreferenceSingleton.getInstance(BaseActivity.this).getString(Globals.COUNTRY_NAME).equals("0")) {
            changeYourCountry.setVisibility(View.GONE);
        }else {
            changeYourCountry.setVisibility(View.VISIBLE);
        }

        yourCountry.setOnClickListener(view -> {
            yourCountry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            changeYourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            rateTheApp.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            contenents.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            allCountries.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            otherApps.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            share.setBackgroundColor(getResources().getColor(R.color.backgroundColor));

            if (SharePreferenceSingleton.getInstance(BaseActivity.this).getString(Globals.COUNTRY_NAME).equals("0")) {
                Toast.makeText(BaseActivity.this, "Please your country first", Toast.LENGTH_SHORT).show();
                SimpleActivityTransition.goToNextActivity(BaseActivity.this, SearchActivity.class, 1);
                finish();
            } else {
                SimpleActivityTransition.goToNextActivity(BaseActivity.this, CountryDetails.class, 1);
            }


        });
        changeYourCountry.setOnClickListener(view -> {
            changeYourCountry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            yourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            rateTheApp.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            contenents.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            allCountries.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            otherApps.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            share.setBackgroundColor(getResources().getColor(R.color.backgroundColor));

            //   if (SharePreferenceSingleton.getInstance(BaseActivity.this).getString(Globals.COUNTRY_NAME).equals("0")) {
            SimpleActivityTransition.goToNextActivity(BaseActivity.this, SearchActivity.class, 1);
            finish();


        });
        contenents.setOnClickListener(view -> {
            contenents.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            changeYourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            yourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            otherApps.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            allCountries.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            share.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            rateTheApp.setBackgroundColor(getResources().getColor(R.color.backgroundColor));

            SimpleActivityTransition.goToNextActivity(BaseActivity.this, RegionActivity.class);

        });
        allCountries.setOnClickListener(view -> {
            allCountries.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            changeYourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            yourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            contenents.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            share.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            otherApps.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            rateTheApp.setBackgroundColor(getResources().getColor(R.color.backgroundColor));


            Bundle bundle = new Bundle();
            bundle.putString("Division", Globals.CON_ALL);
            Intent explicitIntent = new Intent(BaseActivity.this, CountryListActivity.class);
            explicitIntent.putExtras(bundle);
            startActivity(explicitIntent);

        });


        rateTheApp.setOnClickListener(view -> {
            rateTheApp.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            changeYourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            yourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            contenents.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            allCountries.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            otherApps.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            share.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            showCustomRateMeDialogClick();
        });
        otherApps.setOnClickListener(view -> {
            otherApps.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            changeYourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            yourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            contenents.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            allCountries.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            share.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            rateTheApp.setBackgroundColor(getResources().getColor(R.color.backgroundColor));

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Md.+Razon+Hossain")));

        });
        share.setOnClickListener(view -> {
            share.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            changeYourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            yourCountry.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            contenents.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            allCountries.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            otherApps.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
            rateTheApp.setBackgroundColor(getResources().getColor(R.color.backgroundColor));

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "You can get all Emergency number for free\nhttps://play.google.com/store/apps/details?id=" + getPackageName());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share with"));

        });


    }

    private void worksOnCountrySelect() {

        ArrayList<Country> countryList = Globals.getCountryList(Globals.CON_ALL);

        View view = getLayoutInflater().inflate(R.layout.listview_layout, null);
        ListView listView = view.findViewById(R.id.list);
        listView.setAdapter(new CountryArrayAdapter(this, (String[])
                Globals.getCountryNames(Globals.CON_ALL).toArray(new String[0])));

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        listView.setOnItemClickListener((adapterView, view1, position, l) -> {

            String name = countryList.get(position).getName();
            String region = countryList.get(position).getContinent();

            SharePreferenceSingleton.getInstance(this).saveString(Globals.COUNTRY_NAME, name);
            SharePreferenceSingleton.getInstance(this).saveString(Globals.CONTINENT_NAME, region);

            alertDialog.dismiss();

            SimpleActivityTransition.goToNextActivity(this, CountryDetails.class, 1);

        });


    }

    private void showCustomRateMeDialogClick() {

        final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .icon(getResources().getDrawable(R.drawable.logo))
                //   .session(7)
                .threshold(3)
                .ratingBarBackgroundColor(R.color.backgroundColor)
                .title("How was your experience with us?")
                .titleTextColor(R.color.black)
                .positiveButtonText("Not Now")
                .negativeButtonText("Never")
                .positiveButtonTextColor(R.color.colorPrimary)
                .negativeButtonTextColor(R.color.grey_500)
                .formTitle("Submit Feedback")
                .formHint("Tell us where we can improve")
                .formSubmitText("Submit")
                .formCancelText("Cancel")
                .ratingBarColor(R.color.strokeColor)
                .playstoreUrl("market://details?id=" + getPackageName())
                .onRatingChanged((rating, thresholdCleared) -> {

                })
                .onRatingBarFormSumbit(feedback -> {

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("feedback");
                    databaseReference.push().setValue(feedback);

                }).build();

        ratingDialog.show();

    }

    private void showCustomRateMeDialog() {
        final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                .icon(getResources().getDrawable(R.drawable.logo))
                .session(7)
                .threshold(3)
                .ratingBarBackgroundColor(R.color.backgroundColor)
                .title("How was your experience with us?")
                .titleTextColor(R.color.black)
                .positiveButtonText("Not Now")
                .negativeButtonText("Never")
                .positiveButtonTextColor(R.color.colorPrimary)
                .negativeButtonTextColor(R.color.grey_500)
                .formTitle("Submit Feedback")
                .formHint("Tell us where we can improve")
                .formSubmitText("Submit")
                .formCancelText("Cancel")
                .ratingBarColor(R.color.strokeColor)
                .playstoreUrl("market://details?id=" + getPackageName())
                .onRatingChanged((rating, thresholdCleared) -> {

                })
                .onRatingBarFormSumbit(feedback -> {

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("feedback");
                    databaseReference.push().setValue(feedback);

                }).build();

        ratingDialog.show();
    }

}
