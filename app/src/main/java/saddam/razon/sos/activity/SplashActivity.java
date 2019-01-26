package saddam.razon.sos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import saddam.razon.sos.R;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.utils.SharePreferenceSingleton;
import saddam.razon.sos.utils.SimpleActivityTransition;

public class SplashActivity extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {

        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(500); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Customize Path
        configSplash.setPathSplash(Paths.BOOK); //set path String
        configSplash.setOriginalHeight(1000); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(800); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1500);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.header_textcolor); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(1500);
        configSplash.setPathSplashFillColor(R.color.strokeColor); //path object filling color

        //Customize Title
        configSplash.setTitleSplash("SOS World wide");
        configSplash.setTitleTextColor(R.color.header_textcolor);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("font/Ubuntu-R.ttf"); //provide string to your font located in assets/fonts/


    }

    @Override
    public void animationsFinished() {

        if (SharePreferenceSingleton.getInstance(SplashActivity.this).getString(Globals.COUNTRY_NAME).equals("0")){
            SimpleActivityTransition.goToNextActivity(this, MainActivity.class);
            finish();
        }else {
            SimpleActivityTransition.goToNextActivity(this, CountryDetails.class, 1);
            finish();
        }

    }
}
