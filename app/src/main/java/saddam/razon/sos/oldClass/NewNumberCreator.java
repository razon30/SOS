package saddam.razon.sos.oldClass;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import saddam.razon.sos.data.Globals;
import saddam.razon.sos.R;

public class NewNumberCreator extends Activity {
    String cName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newnumber);
        this.cName = getIntent().getExtras().getString("Country");
        ((TextView) findViewById(R.id.textView_title)).setText(this.cName);
    }

    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.button_add:
                EditText cat = (EditText) findViewById(R.id.editText_cat);
                EditText tel = (EditText) findViewById(R.id.editText_tel);
                if (cat == null || cat.getText() == null || cat.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Enter Emergency Type", 0);
                    return;
                } else if (tel == null || cat.getText() == null || cat.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Enter Emergency Type", 0);
                    return;
                } else {
                    Globals.getDB().insertC(this.cName, cat.getText().toString().trim(), tel.getText().toString().trim());
                    finish();
                    return;
                }
            case R.id.button_cancel:
                finish();
                return;
            default:
                return;
        }
    }
}
