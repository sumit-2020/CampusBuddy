package mobileDevelopment.com.root.campusbuddy;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import codetail.graphics.drawables.DrawableHotspotTouch;
import codetail.graphics.drawables.LollipopDrawable;
import codetail.graphics.drawables.LollipopDrawablesCompat;

public class MainActivity extends AppCompatActivity {

   ImageButton mapButt1, mapButt2, tnButt2,tdbtt1, fbbtt1;
    private FloatingActionButton mActionButton;
//    SharedPreferences prefsforfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        FacebookSdk.sdkInitialize(getApplicationContext());
//        prefsforfb=this.getSharedPreferences("com.example.appfb", Context.MODE_PRIVATE);

//        SharedPreferences.Editor editor = prefsforfb.edit();
//        editor.putInt("No of times ", 0);
//        editor.commit();


        mapButt1 = (ImageButton) findViewById(R.id.mapBut1);
        tnButt2 = (ImageButton) findViewById(R.id.tnBut2);
        tdbtt1=(ImageButton)findViewById(R.id.tdbtn);
        fbbtt1=(ImageButton)findViewById(R.id.fbbtn);

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

                if (Fblist.flag == true) {
                    Intent tdIntent = new Intent(MainActivity.this, Fblist.class);
                    startActivity(tdIntent);
                } else {
                    Intent tdIntent = new Intent(MainActivity.this, fb.class);
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

}




