package com.pharma;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class RequestPermissionActivity extends PermissionBaseActivity implements EasyPermissions.PermissionCallbacks,EasyPermissions.RationaleCallbacks{
    private static final String TAG = "MainActivity";
    private static final String[] LOCATION_AND_CONTACTS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS};
    private static final String[] CAMERA_PER = {Manifest.permission.CAMERA};
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);
        Button btnCamera=(Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraTask();
            }
        });
        findViewById(R.id.button_location_and_contacts).setOnClickListener(v -> locationAndContactsTask());
    }

    public void cameraTask() {
        if (hasPermissions(CAMERA_PER)) {
            Toast.makeText(this, "TODO: Camera things", Toast.LENGTH_LONG).show();
        } else {
            reqPermissions("Rationale_ Camera",RC_CAMERA_PERM,CAMERA_PER);
        }
    }

    public void locationAndContactsTask() {
        if (hasPermissions(LOCATION_AND_CONTACTS)) {
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show();
        } else {
            reqPermissions(getString(R.string.rationale_location_contacts),RC_LOCATION_CONTACTS_PERM,LOCATION_AND_CONTACTS);
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Toast.makeText(getApplicationContext(),"onRationaleAccepted",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onRationaleDenied(int requestCode) {
        Toast.makeText(getApplicationContext(),"onRationaleDenied",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(getApplicationContext(),"onPermissionsGranted",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(getApplicationContext(),"onPermissionsDenied:" + requestCode + ":" + perms.size(),Toast.LENGTH_LONG).show();
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        /*if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }*/
        new AppSettingsDialog.Builder(this).build().show();
    }

}