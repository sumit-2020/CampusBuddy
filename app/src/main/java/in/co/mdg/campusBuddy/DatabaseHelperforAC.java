package in.co.mdg.campusBuddy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 7/6/15.
 */
public class DatabaseHelperforAC extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/in.co.mdg.campusBuddy/databases/";

    private static String DB_NAME = "academic_calendar_events";

    private SQLiteDatabase myDataBase6;
    public static int i=0;

    private final Context myContext;
    //  private final String TABLE_CONTACT="contacts";



    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DatabaseHelperforAC(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {
        try {
            boolean dbExist = checkDataBase();


            if (dbExist) {
                //do nothing - database already exist
            } else {
                //By calling this method and empty database will be created into the default system path
                //of your application so we are gonna be able to overwrite that database with our database.

                // this.close();
                this.getReadableDatabase();

                try {
                    // this.close();
                    copyDataBase();
                }

                catch (IOException e) {
                    Log.e("Create",e.toString());
                    throw new Error("Error copying database");
                }
            }
        }
        catch (Exception e)
        {
//           Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
            Log.e("Message",e.getMessage()+" this is it");
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            Log.e("Check",e.toString());

            //database does't exist yet.

        }

//        if (checkDB != null) {
//
//            checkDB.close();
//
//        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase6 = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    //Open the database
    public boolean open() {

        try {
            String myPath = DB_PATH + DB_NAME;
            myDataBase6 = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            return true;

        } catch(Exception sqle) {
            myDataBase6 = null;
            Log.e("Open", sqle.toString());

            return false;
        }

    }

    @Override
    public synchronized void close() {

        if (myDataBase6 != null)
            myDataBase6.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        try{createDataBase();}
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<AcadEvents> getEvents(String table_name) {

        List<AcadEvents> eventlist = null;

        try {

            String  query  = "SELECT * FROM " + table_name;
            SQLiteDatabase  db    = SQLiteDatabase.openDatabase( DB_PATH + DB_NAME , null, SQLiteDatabase.OPEN_READWRITE);
            Cursor cursor  = db.rawQuery(query, null);

            // go over each row, build elements and add it to list
            eventlist = new LinkedList<AcadEvents>();

            if (cursor.moveToFirst()) {
                do {
                    i++;
                    AcadEvents events = new AcadEvents();
                    events.day    = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));
                    events.month   = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
                    events.year    = Integer.parseInt(cursor.getString(cursor.getColumnIndex("year")));
                    events.starth=Integer.parseInt(cursor.getString(cursor.getColumnIndex("starth")));
                    events.startmin=Integer.parseInt(cursor.getString(cursor.getColumnIndex("startmin")));
                    events.endh=Integer.parseInt(cursor.getString(cursor.getColumnIndex("endh")));
                    events.endmin=Integer.parseInt(cursor.getString(cursor.getColumnIndex("endmin")));
                    events.title=cursor.getString(cursor.getColumnIndex("title"));
                    events.details=cursor.getString(cursor.getColumnIndex("detail"));
                    events.venue=cursor.getString(cursor.getColumnIndex("venue"));

                    eventlist.add(events);

                } while (cursor.moveToNext());
            }
        } catch(Exception e) {
            // sql error
            Log.e("getEvnets",e.toString());

            e.printStackTrace();
        }

        return eventlist;
    }
    }