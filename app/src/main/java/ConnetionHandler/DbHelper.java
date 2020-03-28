package ConnetionHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String KEY_ROWID = "Bid";
    public static final String KEY_BOOKMARK_ID = "bookmarkid";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SRCNAME = "source";
    public static final String URL_TITLE = "url";
    public static final String KEY_DESCP = "description";
    public static final String KEY_DEL = "del";
    /*public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_DESCP = "description";
    public static final String KEY_MSG_FROM = "msg_from";
    public static final String KEY_DEL = "del";
    public static final String KEY_IS_READ = "isread";

    public static final String KEY_NUMBER = "mobileno";
    public static final String KEY_IS_WISHED = "iswished";*/

//village data


    public static final String DATABASE_NAME = "IFS_db.db";
    public static final String DATABASE_TABLE = "BookMark";
    public static final String DATABASE_TABLE_TWO = "information";
    public static final String DATABASE_TABLE_THREE = "bdnumbers";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_BOOKMARK_ID + " TEXT NOT NULL UNIQUE, "

                + KEY_TITLE + " TEXT NOT NULL, "
                + URL_TITLE + " TEXT NOT NULL, "
                + KEY_DESCP + " TEXT NOT NULL, "
                + KEY_SRCNAME + " TEXT NOT NULL, "
                + KEY_DEL + " INTEGER NOT NULL"

                + "); ");
      /*  db.execSQL("CREATE TABLE " + DATABASE_TABLE_TWO + " ("
                + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_WEB_ID + " INTEGER NOT NULL, "
                + KEY_NAME + " TEXT NOT NULL, "
                + KEY_IMAGE + " TEXT NOT NULL, "
                + KEY_DESCP + " TEXT NOT NULL, "
                + KEY_IS_READ + " INTEGER NOT NULL DEFAULT 0, "
                + KEY_DEL + " INTEGER NOT NULL"
                + "); ");
        db.execSQL("CREATE TABLE " + DATABASE_TABLE_THREE + " ("
                + KEY_NUMBER + " BIGINT PRIMARY KEY, "
                + KEY_NAME + " TEXT NOT NULL, "
                + KEY_IS_WISHED + " INTEGER NOT NULL"
                + "); ");
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        if(oldVersion == 1)
        {
            db.execSQL("DROP TABLE " + DATABASE_TABLE);
            onCreate(db);
        }else {
            db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
            onCreate(db);
        }
      /*  if(oldVersion == 1)
        {
            db.execSQL("DROP TABLE " + DATABASE_TABLE_TWO);
            onCreate(db);
        }else {
            db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE_TWO);
            onCreate(db);
        }

        if(oldVersion == 1)
        {
            db.execSQL("DROP TABLE " + DATABASE_TABLE_THREE);
            onCreate(db);
        }else {
            db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE_THREE);
            onCreate(db);
        }*/
    }
    public void deleteTable(String TABLE_NAME)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete  from " + TABLE_NAME);
    }
}
