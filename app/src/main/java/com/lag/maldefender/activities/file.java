package com.lag.maldefender.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.lag.maldefender.R;

import net.gotev.uploadservice.data.UploadInfo;
import net.gotev.uploadservice.network.ServerResponse;
import net.gotev.uploadservice.observer.request.RequestObserverDelegate;
import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;


public class file extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final int pickFileRequestCode = 42;
    public  void pickFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
                .setType("*/*")
                .setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent, pickFileRequestCode);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == pickFileRequestCode && resultCode == Activity.RESULT_OK) {
            if (data != null)
                onFilePicked(data.getData().toString());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    void onFilePicked(String filePath) {


        Log.d(filePath,"sami 5ra");
        try {
            MultipartUploadRequest request= new MultipartUploadRequest(this,  "http://192.168.1.14:8080/files");
            request.setMethod("POST");
            request.setNotificationConfig((x,uploadId)->getNotificationConfig(uploadId, R.string.multipart_upload));
            request.addFileToUpload(filePath, "file");
            request.subscribe(this, this, new RequestObserverDelegate() {
                @Override
                public void onSuccess(@NonNull Context context, @NonNull UploadInfo uploadInfo, @NonNull ServerResponse serverResponse) {
                    Log.e("LIFECYCLE", "Success " + uploadInfo.getProgressPercent());
                }

                @Override
                public void onProgress(@NonNull Context context, @NonNull UploadInfo uploadInfo) {
                    Log.e("LIFECYCLE", "Progress " + uploadInfo.getProgressPercent() );
                }

                @Override
                public void onError(@NonNull Context context, @NonNull UploadInfo uploadInfo, @NonNull Throwable throwable) {
                    Log.e("LIFECYCLE", "Error " + throwable.getMessage());
                }

                @Override
                public void onCompletedWhileNotObserving() {
                    Log.e("LIFECYCLE", "Completed while not observing");
                    finish();
                }

                @Override
                public void onCompleted(@NonNull Context context, @NonNull UploadInfo uploadInfo) {
                    Log.e("LIFECYCLE", "Completed ");
                    finish();

                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
