package org.toilelibre.libe.athg2sms.androidstuff.interactions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.toilelibre.libe.athg2sms.R;
import org.toilelibre.libe.athg2sms.actions.Actions;

import java.util.Arrays;

public class ExportFormUI {

    public void onCreate (final View target, final Activity activity) {

        target.findViewById (R.id.exportfile).setOnClickListener (new OnClickListener () {

            @SuppressLint("InlinedApi")
            public void onClick (final View v) {
                final String patternName = ((Spinner) target.findViewById (R.id.exportPatterns)).getSelectedItem ().toString ();
                activity.getIntent().putExtra("pattern", patternName);

                new ExportUI().retryExportOperation(activity);
            }
        });

        ((Spinner) target.findViewById (R.id.exportPatterns)).setAdapter (new ArrayAdapter<String> (activity, android.R.layout.simple_spinner_item,
                new Actions().getAllFormats()));
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        if (Arrays.asList(permissions).contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
            activity.getIntent().putExtra("pattern", ((Spinner) activity.findViewById (R.id.exportfile)).getSelectedItem ().toString ());
            new ExportUI().retryExportOperation(activity);
        }
    }
}