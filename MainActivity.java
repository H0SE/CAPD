package edu.ourincheon.capdtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize(getApplicationContext());
        try{
            SQLiteDatabase db = openOrCreateDatabase("image.db", Context.MODE_PRIVATE, null);
            Cursor cur = db.rawQuery("SELECT * From images order by random();", null);
            cur.moveToFirst();

            ImageView iv1 = findViewById(R.id.iv1);
            byte[] image = cur.getBlob(1);
            Bitmap bm = BitmapFactory.decodeByteArray(image,0,image.length);
            iv1.setImageBitmap(bm);

            cur.moveToNext();

            ImageView iv2 = findViewById(R.id.iv2);
            byte[] image2 = cur.getBlob(1);
            Bitmap bm2 = BitmapFactory.decodeByteArray(image2,0,image2.length);
            iv2.setImageBitmap(bm2);

            cur.moveToNext();

            ImageView iv3 = findViewById(R.id.iv3);
            byte[] image3 = cur.getBlob(1);
            Bitmap bm3 = BitmapFactory.decodeByteArray(image3,0,image3.length);
            iv3.setImageBitmap(bm3);

            cur.moveToNext();

            ImageView iv4 = findViewById(R.id.iv4);
            byte[] image4 = cur.getBlob(1);
            Bitmap bm4 = BitmapFactory.decodeByteArray(image4,0,image4.length);
            iv4.setImageBitmap(bm4);

        }catch(Exception e){
        }
    }
    public static final String PACKAGE_DIR = "/data/data/edu.ourincheon.capdtest/";
    public static final String DATABASE_NAME = "image.db";
    public static final String COPY2DATABASE_NAME = "image.db";
    public static void initialize(Context ctx) {
// check
        File folder = new File(PACKAGE_DIR + "databases");
        folder.mkdirs();
        File outfile = new File(PACKAGE_DIR + "databases/" + COPY2DATABASE_NAME);
        if (outfile.length() <= 0) {
            AssetManager assetManager = ctx.getResources().getAssets();
            try {
                InputStream is = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_BUFFER);
                long filesize = is.available();
                byte [] tempdata = new byte[(int)filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                FileOutputStream fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}