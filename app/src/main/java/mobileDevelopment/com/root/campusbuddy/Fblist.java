package mobileDevelopment.com.root.campusbuddy;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;
import java.util.HashMap;


public class Fblist extends AppCompatActivity{


    Cursor cr;

    ContentValues values;
    String page_id,page_name;
    ListView listview;
    String[] fbpages;
    //    boolean[] fbpagesliked=null;
    ArrayList<String> fbpagesliked;
    public ArrayList<Page> pageList;
    //    ArrayAdapter<String> adapter;
    LoginButton loginButton;
    CallbackManager callbackManager;
    Button submitb;
    ViewGroup v;
    int i,count=0;
    public static boolean flag=true;
    Toolbar toolbar;
    CheckBox c;
    public ArrayList<Page> listofvalues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);

//       recreate();

        setContentView(R.layout.activity_fblist);

        final SQLiteDatabase db = Pages_db_helper.getInstance(getApplicationContext()).getWritableDatabase();


        String[] eventList = {
                PagesDB.PagesEntry.COLUMN_NAME_Pages_ID,
                PagesDB.PagesEntry.COLUMN_NAME_Page_name
        };

        try {
            cr = db.query(PagesDB.PagesEntry.TABLE_NAME, eventList, null, null, null, null, null);
        }
        catch (Exception err){
            Toast.makeText(Fblist.this, err.toString(), Toast.LENGTH_LONG).show();
        }
        values = new ContentValues();


        toolbar = (Toolbar) findViewById(R.id.tool_barfblist);
//        DayNightTheme.setToolbar(toolbar);

//        ctoolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbar);
        toolbar.setTitle("Facebook page list");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        flag=false;

        /*fbpageslikedmap=new HashMap<String,String>();
        fbpageslikedmap.put("Cinema Club", "231275190406200");
        fbpageslikedmap.put("mdg, IITR","198343570325312");
        fbpageslikedmap.put("SDSLabs","182484805131346");
        fbpageslikedmap.put("Team robocon","257702554250168");
        fbpageslikedmap.put("IMG","353701311987");
        fbpageslikedmap.put("edc","265096099170");
        fbpageslikedmap.put("General Notice Board","671125706342859");
        fbpageslikedmap.put("Audio Section","418543801611643");
        fbpageslikedmap.put("Sanskriti Club","420363998145999");
        fbpageslikedmap.put("Group for Interative Learning, IITR","146825225353259");
        fbpageslikedmap.put("ASHARE","754869404569818");
        fbpageslikedmap.put("Cognizance","217963184943488");
        fbpageslikedmap.put("Photography Section","317158211638196");
        fbpageslikedmap.put("IIT Roorkee","415004402015833");
        fbpageslikedmap.put("Technologic 2015","369513426410599");
        fbpageslikedmap.put("Electronics Section","503218879766649");
        fbpageslikedmap.put("ncc","242919515859218");
        fbpageslikedmap.put("Cinematic Section","100641016663545");
        fbpageslikedmap.put("Fine Arts Section","567441813288417");
        fbpageslikedmap.put("Anushruti","272394492879208");
        fbpageslikedmap.put("Rhapsody","1410660759172170");
        fbpageslikedmap.put("SHARE IITR","292035034247");
*/

        listofvalues = Data.getFbPageList();


        fbpagesliked=new ArrayList<String>();
        callbackManager = CallbackManager.Factory.create();
        submitb=(Button)findViewById(R.id.submitbutton);
//        loginButton = (LoginButton)findViewById(R.id.login_button);
//        loginButton.setReadPermissions("user_friends");
//
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Toast.makeText(Fblist.this, "Logged in", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
        try{

            listview=(ListView)findViewById(R.id.listfbpages);
            fbpages=getResources().getStringArray(R.array.fbpages);
            pageList = new ArrayList<Page>();
            for(int i=0;i<fbpages.length;i++){
                Page page = new Page(fbpages[i]);
                page.page_id = listofvalues.get(i).getPage_id();
                pageList.add(page);
            }

            PagesSelected.writeSelectedPageIds(Fblist.this, fbpagesliked);

            listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//            adapter=new ArrayAdapter<String>(Fblist.this,R.layout.mytextviewfb,fbpages);
//            try {
//                listofvalues= PagesSelected.getSelectedPageIds(this);
//                for(int j=0;j<listofvalues.size();j++)
//                {
//                    for(int i=0;i<pageList.size();i++)
//                    {
//                        if(listofvalues.get(j).equals(pageList.get(i).page_id))
//                        {
//                            pageList.get(i).setIsSelected(true);
//                            Log.e("True pages", pageList.get(i).getPage_name());
//                        }
//                    }
//
//                }}
//            catch (Exception e)
//            {;}
            cr.moveToFirst();

            if(cr.getCount()>0)
            {

             //   page_id=cr.getString(cr.getColumnIndex(PagesDB.PagesEntry.COLUMN_NAME_Pages_ID));
                page_name=cr.getString(cr.getColumnIndex(PagesDB.PagesEntry.COLUMN_NAME_Page_name));

                for(int i=0;i<fbpages.length;i++)
                {
                    if(page_name.equals(pageList.get(i).getPage_name())){
                        pageList.get(i).setIsSelected(true);
                    }
                }

            }

            while(cr.moveToNext()){
                page_name=cr.getString(cr.getColumnIndex(PagesDB.PagesEntry.COLUMN_NAME_Page_name));

                for(int i=0;i<fbpages.length;i++)
                {
                    if(page_name.equals(pageList.get(i).getPage_name())){
                        pageList.get(i).setIsSelected(true);
                    }
                }

            }
            db.delete(PagesDB.PagesEntry.TABLE_NAME, null, null);
            CustomList adapter=new CustomList(Fblist.this,pageList);
            listview.setAdapter(adapter);


            submitb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                SparseBooleanArray checked = listview.getCheckedItemPositions();
//                fbpagesliked=new boolean[checked.size()];
//                for (int i = 0; i < checked.size(); i++) {
//                    if (checked.valueAt(i)) {
//                        fbpagesliked[i] =true;
//                    }
//                    else{
//                        fbpagesliked[i]=false;
//                    }
//                }

                    for(int i=0;i<fbpages.length;i++)
                    {
                        if(pageList.get(i).isSelected()){
                            fbpagesliked.add(listofvalues.get(i).getPage_id());
                            values.put(PagesDB.PagesEntry.COLUMN_NAME_Pages_ID, listofvalues.get(i).getPage_id());
                            values.put(PagesDB.PagesEntry.COLUMN_NAME_Page_name, pageList.get(i).getPage_name());
                            db.insert(
                                    PagesDB.PagesEntry.TABLE_NAME,
                                    null,
                                    values);
                        }
                    }

                    PagesSelected.writeSelectedPageIds(Fblist.this, fbpagesliked);
                    if(fbpagesliked==null)
                    {
                        Toast.makeText(Fblist.this,"Please Select atleast one page to get the feeds",Toast.LENGTH_LONG).show();
                        onResume();
                    }
                    else
                    {
                        Intent intent = new Intent(Fblist.this, fb.class);
                        finish();
                        startActivity(intent);}
//                try{
//                    fbpagesliked=new boolean[listview.getChildCount()];
//                    for(int i=0;i<listview.getChildCount();i++)
//                {
//                    v=(ViewGroup)listview.getChildAt(i);
//                    c=(CheckBox)v.findViewById(R.id.checkBox);
//                    if(c.isChecked())
//                    {
//                        fbpagesliked[i]=true;
//                    }
//                    else
//                    if(c.isChecked()!=true)
//                    {
//                        fbpagesliked[i]=false;
//                    }
//                }
//
//                    if(fbpagesliked!=null)
//                    {
//                        for(i=0;i<fbpagesliked.length;i++)
//                        {
//                            if(fbpagesliked[i]==true)
//                                count++;
//                        }}
//
//
//
//
//                    if(fbpagesliked==null || count==0
////                            || (AccessToken.getCurrentAccessToken()==null))
//                            )
//                    {
////                        if(AccessToken.getCurrentAccessToken()==null)
////                        {
////                            Toast.makeText(Fblist.this,"Please Login first to get the feeds",Toast.LENGTH_LONG).show();
////
////                        }
////                        else
//                        Toast.makeText(Fblist.this,"Atleast one of the pages have to be chosen to get the feeds",Toast.LENGTH_LONG).show();
//                    }
//                    else
//                    {
//                        fb.fbpls=fbpagesliked;
//                        Intent intent = new Intent(Fblist.this, fb.class);
//                        Bundle b = new Bundle();
//                        b.putBooleanArray("pagesliked", fbpagesliked);
//                        intent.putExtras(b);
//                        startActivity(intent);
//                        finish();
//                    }

//                onPause();
//                Intent intent = new Intent(Fblist.this, fb.class);
//                Bundle b = new Bundle();
//                b.putBooleanArray("pagesliked", fbpagesliked);
//                intent.putExtras(b);
//                startActivity(intent);

//            }
//                catch (Exception e)
//                {
//                    Toast.makeText(Fblist.this,e.toString(),Toast.LENGTH_LONG).show();
//                }
//            }
//        });}
//        catch(Exception e)
//        {
//            Toast.makeText(Fblist.this,e.toString(),Toast.LENGTH_LONG).show();
//        }

               }


            });}catch(Exception e)
        {
            Toast.makeText(Fblist.this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }


    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_fblist, menu);
         return true;
     }
 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    protected void onPause(){
//        Toast.makeText(this,"Atleast one of the pages have to be chosen to get the feeds",Toast.LENGTH_LONG).show();
//        if(fbpagesliked!=null)
//        {
//        for(i=0;i<fbpagesliked.length;i++)
//        {
//            if(fbpagesliked[i]==true)
//                count++;
//        }}
//
//        if(fbpagesliked==null || count==0)
//        {
//            Toast.makeText(this,"Atleast one of the pages have to be chosen to get the feeds",Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Intent intent = new Intent(Fblist.this, fb.class);
//            Bundle b = new Bundle();
//            b.putBooleanArray("pagesliked", fbpagesliked);
//            intent.putExtras(b);
//            startActivity(intent);
//            finish();
//            super.onPause();}
//    }


}
