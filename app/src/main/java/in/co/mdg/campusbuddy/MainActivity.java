package in.co.mdg.campusbuddy;


import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import codetail.graphics.drawables.LollipopDrawablesCompat;

/*
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
*/

public class MainActivity extends AppCompatActivity {

   ImageButton mapButt1, mapButt2, tnButt2,tdbtt1, fbbtt1;
    private FloatingActionButton mActionButton;
    LinearLayout main_layout;
    ProgressBar p;
//    SharedPreferences prefsforfb;

   @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       main_layout = (LinearLayout) findViewById(R.id.main_layout);

       BitmapFactory.Options options = new BitmapFactory.Options();
       options.inJustDecodeBounds = true;
       BitmapFactory.decodeResource(getResources(), R.drawable.mainbackground, options);
       int imageHeight = options.outHeight;
       int imageWidth = options.outWidth;
       String imageType = options.outMimeType;

     //  Bitmap bmImg = BitmapFactory.decodeStream(is)
      BitmapDrawable background = new BitmapDrawable(decodeSampledBitmapFromResource(getResources(),
              R.drawable.mainbackground, imageWidth, imageHeight));
       main_layout.setBackground(background);

       //main_layout.setBackgroundDrawable(decodeSampledBitmapFromResource(getResources(),  R.drawable.mainbackground, 100, 100));


       SharedPreferences getPrefs = PreferenceManager
               .getDefaultSharedPreferences(this);
       SharedPreferences.Editor editor = getPrefs.edit();

       boolean isSet = getPrefs.getBoolean("alarm_set", false);
       if(!isSet){
           NotificationHandler.buildNotification(this);
           editor.putBoolean("alarm_set", true);
           editor.commit();
       }
       int sdk = android.os.Build.VERSION.SDK_INT;

       LinearLayout layout =(LinearLayout)findViewById(R.id.main_layout);
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                layout.setBackgroundDrawable( getResources().getDrawable(R.drawable.mainbackground) );
            } else {
                layout.setBackground(getResources().getDrawable(R.drawable.mainbackground));
            }
            //  layout.setBackgroundResource(R.drawable.night_720);

//            ImageView img= (ImageView) findViewById(R.id.sun_moon);
//            img.setImageResource(R.drawable.moon_360);




        FacebookSdk.sdkInitialize(getApplicationContext());
//        prefsforfb=this.getSharedPreferences("com.example.appfb", Context.MODE_PRIVATE);

//        SharedPreferences.Editor editor = prefsforfb.edit();
//        editor.putInt("No of times ", 0);
//        editor.commit();
        mapButt1 = (ImageButton) findViewById(R.id.mapBut1);
        tnButt2 = (ImageButton) findViewById(R.id.tnBut2);
        tdbtt1=(ImageButton)findViewById(R.id.tdbtn);
        fbbtt1=(ImageButton)findViewById(R.id.fbbtn);

//        SpringSystem springSystem = SpringSystem.create();
//// Add a spring to the system.
//        final Spring spring = springSystem.createSpring();
//
//        mapButt1.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                spring.addListener(new SimpleSpringListener() {
//
//                    @Override
//                    public void onSpringUpdate(Spring spring) {
//                        // You can observe the updates in the spring
//                        // state by asking its current value in onSpringUpdate.
//                        float value = (float) spring.getCurrentValue();
//                        float scale = 1f - (value * 0.5f);
//                        mapButt1.setScaleX(scale);
//                        mapButt1.setScaleY(scale);
//                    }
//                });
//
//// Set the spring in motion; moving from 0 to 1
//                spring.setEndValue(1);
//
//                return false;
//            }
//        });
// Add a listener to observe the motion of the spring.
        mapButt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(MainActivity.this, SimpleMap.class);
                startActivity(mapIntent);
            }
        });

        tnButt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ttIntent = new Intent(MainActivity.this, timetable_navigation2.class);
                startActivity(ttIntent);
            }
        });

        tdbtt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tdIntent = new Intent(MainActivity.this, Deptt_list.class);
                startActivity(tdIntent);
            }
        });
        fbbtt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                count=prefsforfb.getInt("No of times ",0);


//                count=prefsforfb.getInt("No of times ",0);

                if(AccessToken.getCurrentAccessToken()==null){
                    Intent tdIntent = new Intent(MainActivity.this, Fblogin.class);
                    startActivity(tdIntent);
                } else {
                    Intent tdIntent = new Intent(MainActivity.this,fb.class);
                    startActivity(tdIntent);
                    
                }
            }
        });

        /*
try {
    mActionButton = (FloatingActionButton) findViewById(R.id.fabtest);
    mActionButton.setBackgroundDrawable(getDrawable2(R.drawable.fab_background));
    mActionButton.setClickable(true);// if we don't set it true, ripple will not be played
    mActionButton.setOnTouchListener(
            new DrawableHotspotTouch((LollipopDrawable) mActionButton.getBackground()));
    Toast.makeText(this, "hello.... try completed", Toast.LENGTH_LONG).show();
}
catch (Exception e){
    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
    Toast.makeText(this, "reached catch", Toast.LENGTH_LONG).show();
}
*/

    }


    /**
     * {@link #getDrawable(int)} is already taken by Android API
     * and method is final, so we need to give another name :(
     */
    public Drawable getDrawable2(int id){
        return LollipopDrawablesCompat.getDrawable(getResources(), id, getTheme());
    }

//    @Override
//    public void onPause()
//    {
//
//        SharedPreferences.Editor editor = prefsforfb.edit();
//        editor.putInt("No of times ", count + 1);
//        editor.commit();
//    }

//    public void broadcastIntent(View view){
//        Intent intent = new Intent();
//        intent.setAction("abc");
//        sendBroadcast(intent);
//    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


}




