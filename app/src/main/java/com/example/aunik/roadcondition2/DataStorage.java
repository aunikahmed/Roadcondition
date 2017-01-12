package com.example.aunik.roadcondition2;

import android.app.Activity;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.util.FloatMath;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.data;

/**
 * Created by aunik on 12/4/16.
 */
public class DataStorage {
    private BufferedWriter writer;
    Context context;


    public DataStorage(Context context) {
        this.context = context;
    }

    public void handleFileOpening(){
        File traceFile = new File(context.getExternalFilesDir(null), "TraceFile.txt");
        if (!traceFile.exists())
            try {
                traceFile.createNewFile();
            } catch (IOException e) {
                Log.e("com.FileTest", "Unable to open  TraceFile.txt file.");
            }
        try {
            writer = new BufferedWriter(new FileWriter(traceFile, true /*append*/));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Adds a line to the trace file

    }

    public void handleFileClosing( ){
        try {
            writer.close();
        } catch (IOException e) {
            Log.e("com.FileTest", "Unable to close TraceFile.txt file.");
        }
      /*  MediaScannerConnection.scanFile(context, new String[] { traceFile.toString() },
                null,
                null);
*/
    }

    public void saveData(float x, float y, float z) {
        SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
        String timestamp = sdf.format(new Date());

        try
        {
            writer.write(timestamp + "\t" + Float.toString(x) + "\t" + Float.toString(y) + "\t" +Float.toString(z) +"\n");

        }
        catch (IOException e)
        {
            Log.e("com.FileTest", "Unable to write to the TraceFile.txt file.");
        }
    }


}
