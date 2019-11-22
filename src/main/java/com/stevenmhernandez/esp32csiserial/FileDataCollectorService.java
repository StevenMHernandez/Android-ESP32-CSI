package com.stevenmhernandez.esp32csiserial;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileDataCollectorService extends BaseDataCollectorService {
    private String LOG_TAG = "FileDataCollectorService";
    private FileOutputStream localBackup = null;

    public void setup(Context context) {
        try {
            File test = new File(context.getExternalFilesDir(null), "backup" + System.currentTimeMillis() + ".csv");
            localBackup = new FileOutputStream(test, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.w(LOG_TAG, "FileOutputStream exception: - " + e.toString());
        }

    }

    public void handle(String csi) {
        try {
            if (localBackup != null) {
                localBackup.write(csi.getBytes());
                localBackup.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
