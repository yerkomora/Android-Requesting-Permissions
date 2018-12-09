package cl.infomatico.android.examples.requestingpermissions;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_RECORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton bRecord = findViewById(R.id.bRecord);

        if (bRecord != null)
            bRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recordPermissionsCheck();
                }
            });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[]
            , @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_RECORD: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    record();
                }
            }
        }
    }

    private void recordPermissionsCheck() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage(R.string.permission_load);
                builder.setTitle(R.string.permission_required);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recordPermissionsMake();
                    }
                });

                builder.create().show();
            } else {
                recordPermissionsMake();
            }
        } else {
            record();
        }
    }

    private void recordPermissionsMake() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                PERMISSIONS_RECORD);
    }

    private void record() {
    }
}