package saddam.razon.sos.oldClass;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import saddam.razon.sos.model.Country;
import saddam.razon.sos.data.Globals;
import saddam.razon.sos.model.KeyValuePair;
import saddam.razon.sos.R;

public class CountryNumbersList extends Activity {
    String cName;
    int callNumImgWidth = 58;
    int callNumTextWidth = 120;
    String dName;

    public LinearLayout getDescTV(final Context context, final String d, final String n, boolean f) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new LayoutParams(metrics.widthPixels - (this.callNumTextWidth + this.callNumImgWidth), -1));
        ll.setGravity(16);
        TextView valueTV = new TextView(context);
        valueTV.setText(d);
        if (f) {
            valueTV.setTextColor(-16776961);
        } else {
            valueTV.setTextColor(-16777216);
        }
        valueTV.setTextSize(1, 18.0f);
        valueTV.setPadding(5, 2, 5, 2);
        valueTV.setGravity(19);
        valueTV.setLongClickable(true);
        OnLongClickListener olc = new OnLongClickListener() {

            class C00012 implements OnClickListener {
                C00012() {
                }

                public void onClick(DialogInterface dialog, int which) {
                }
            }

            public boolean onLongClick(View v) {
                Builder builder = new Builder(context);
                builder.setMessage("Delete " + d + " (" + n + ") ?");
                builder.setCancelable(true);
                final String str = d;
                final String str2 = n;
                builder.setPositiveButton("Yes", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Globals.getDB().deleteC(CountryNumbersList.this.cName, str, str2);
                        CountryNumbersList.this.constructView();
                    }
                });
                builder.setNegativeButton("No", new C00012());
                builder.create().show();
                return true;
            }
        };
        if (f) {
            valueTV.setOnLongClickListener(olc);
            ll.setOnLongClickListener(olc);
        }
        ll.addView(valueTV);
        return ll;
    }

    public LinearLayout getNumber(final Context context, final String n, boolean f) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new LayoutParams(this.callNumTextWidth + this.callNumImgWidth, -1));
        ll.setLayoutParams(new LayoutParams(-2, -1));
        ll.setGravity(19);
        GradientDrawable gd = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1646378, -1907998, -3750202});
        gd.setCornerRadius(0.0f);
        ll.setBackgroundDrawable(gd);
        TextView valueTV = new TextView(context);
        valueTV.setText(n);
        valueTV.setLayoutParams(new LayoutParams(this.callNumTextWidth, -2));
        valueTV.setTextColor(-65536);
        if (n.length() <= 5) {
            valueTV.setTextSize(1, 24.0f);
        } else {
            valueTV.setTextSize(1, 12.0f);
        }
        valueTV.setTypeface(Typeface.DEFAULT_BOLD);
        valueTV.setMinLines(2);
        valueTV.setPadding(3, 2, 5, 2);
        valueTV.setGravity(17);
        ll.addView(valueTV);
        Button callBtn = new Button(this);
        callBtn.setText("");
        callBtn.setBackgroundResource(R.drawable.ic_launcher_foreground);
        callBtn.setLayoutParams(new LayoutParams(this.callNumImgWidth, this.callNumImgWidth));
        callBtn.setOnClickListener(new View.OnClickListener() {

            class C00042 implements OnClickListener {
                C00042() {
                }

                public void onClick(DialogInterface dialog, int which) {
                }
            }

            public void onClick(View v) {
                Builder builder = new Builder(context);
                builder.setMessage("Call " + n + "?");
                builder.setCancelable(true);
                final String str = n;
                final Context context = CountryNumbersList.this;
                builder.setPositiveButton("Yes", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)));
                    }
                });
                builder.setNegativeButton("No", new C00042());
                builder.create().show();
            }
        });
        ll.addView(callBtn);
        return ll;
    }

    public ImageView getDivider(Context context) {
        ImageView iview = new ImageView(context);
        iview.setImageResource(R.drawable.shape_band1);
        return iview;
    }

    public LinearLayout getKVDetail(Context context, String d, String n, boolean f) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(new LayoutParams(-1, -1));
        ll.setGravity(21);
        GradientDrawable gd = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-593690, -855310, -2697514});
        gd.setCornerRadius(0.0f);
        ll.setBackgroundDrawable(gd);
        ll.addView(getDescTV(context, d, n, f));
        ll.addView(getNumber(context, n, f));
        return ll;
    }

    public void constructView() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.numberdetailslayout);
        View rowView = getLayoutInflater().inflate(R.layout.header_number, null, true);
        ((TextView) rowView.findViewById(R.id.textView_header)).setText(this.cName);
        ((TextView) rowView.findViewById(R.id.textView_header2)).setText(Globals.getRegion(this.cName));
        linearLayout.removeAllViews();
        linearLayout.addView(rowView);
        Country c = Globals.getCountry(this.cName);
        if (c != null) {
            int i;
            ArrayList<KeyValuePair> kv1 = c.getNumbers();
            if (kv1 != null) {
                for (i = 0; i < kv1.size(); i++) {
                    linearLayout.addView(getKVDetail(this, ((KeyValuePair) kv1.get(i)).getKey(), ((KeyValuePair) kv1.get(i)).getValue(), false));
                    linearLayout.addView(getDivider(this));
                }
            }
            ArrayList<KeyValuePair> kv2 = Globals.getDB().getTels(c.getName());
            if (kv2 != null) {
                for (i = 0; i < kv2.size(); i++) {
                    linearLayout.addView(getKVDetail(this, ((KeyValuePair) kv2.get(i)).getKey(), ((KeyValuePair) kv2.get(i)).getValue(), true));
                    linearLayout.addView(getDivider(this));
                }
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        this.cName = bundle.getString("Country");
        this.dName = bundle.getString("Division");
        Globals.default_opened = true;
        setContentView(R.layout.numberdetails);
        constructView();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        constructView();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_create:
                Bundle bundle = new Bundle();
                bundle.putString("Country", this.cName);
                Intent explicitIntent = new Intent(this, NewNumberCreator.class);
                explicitIntent.putExtras(bundle);
                startActivityForResult(explicitIntent, 0);
                return true;
            case R.id.item_delete:
                Builder dialog1 = new Builder(this);
                dialog1.setTitle("Deleting a Number");
                dialog1.setMessage("To delete a number, tap it on the list and hold for two seconds. Any number entered manually may be deleted.");
                dialog1.show();
                return true;
            case R.id.item_setdefault:
                Globals.setDefault(this.dName, this.cName);
                Toast.makeText(this, this.cName + " set as default country", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_cleardefault:
                if (this.cName.equalsIgnoreCase(Globals.default_country) && this.dName.equalsIgnoreCase(Globals.default_region)) {
                    Globals.resetDefault();
                    Toast.makeText(this, this.cName + " is no longer set as default country", Toast.LENGTH_SHORT).show();
                    return true;
                }
                Toast.makeText(this, this.cName + " is not set as default country", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
