package ConnetionHandler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {
    private DbHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }
    public SQLController open() throws SQLException {
        dbhelper = new DbHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbhelper.close();
    }
    //Inserting Data into table
    public void insertData(String BookMarkid,String url,String title,String description,String sourcename,int del) {
        ContentValues cv = new ContentValues();
        //cv.put(DbHelper.KEY_ROWID, pid);
        cv.put(DbHelper.KEY_BOOKMARK_ID, BookMarkid);
        cv.put(DbHelper.URL_TITLE, url);
        cv.put(DbHelper.KEY_TITLE, title);

        cv.put(DbHelper.KEY_DESCP, description);
        cv.put(DbHelper.KEY_SRCNAME, sourcename);



        cv.put(DbHelper.KEY_DEL, del);



        database.insert(DbHelper.DATABASE_TABLE, null, cv);
        cv.put(DbHelper.KEY_DEL, del);
    }

    //Getting Cursor to read data from table
    public Cursor readData() {
        String[] allColumns = new String[] { DbHelper.KEY_ROWID,DbHelper.KEY_BOOKMARK_ID
               };
        open();
        //Cursor c = database.query(DbHelper.DATABASE_TABLE, allColumns, DbHelper.KEY_DEL + "!=" + 1,
        //       null, null, null, DbHelper.KEY_WEB_ID+" ASC");
        Cursor c = database.rawQuery("SELECT * FROM BookMark WHERE del=0 ORDER BY bookmarkid DESC", null);
        if (c != null) {
            c.moveToFirst();
        }
        close();
        return c;
    }
    //Getting Cursor to read data from table
    public Cursor readForAllData() {
        String[] allColumns = new String[] { DbHelper.KEY_ROWID,DbHelper.KEY_BOOKMARK_ID
                };
        open();
        //Cursor c = database.query(DbHelper.DATABASE_TABLE, allColumns, DbHelper.KEY_DEL + "!=" + 1,
        //       null, null, null, DbHelper.KEY_WEB_ID+" ASC");
        Cursor c = database.rawQuery("SELECT * FROM BookMark ORDER BY bookmarkid DESC", null);
        if (c != null) {
            c.moveToFirst();
        }
        close();
        return c;
    }

    public Cursor readFieldData(int webpid) {
        String[] allColumns = new String[] { DbHelper.KEY_ROWID,DbHelper.KEY_BOOKMARK_ID
             };
        open();
        Cursor c = database.query(DbHelper.DATABASE_TABLE, allColumns, DbHelper.KEY_BOOKMARK_ID + "=" + webpid,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        close();
        return c;
    }

    //Updating record data into table by id
    public int updateData(long pid, int webpid,String name,String image,String descp,String msg_from,int isread, int del) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(DbHelper.KEY_BOOKMARK_ID, webpid);
        int i = database.update(DbHelper.DATABASE_TABLE, cvUpdate,
                DbHelper.KEY_ROWID + " = " + pid, null);
        return i;
    }

    // Deleting record data from table by id
    public void deleteData(String BookMarkID) {

       database.delete(DbHelper.DATABASE_TABLE, DbHelper.KEY_BOOKMARK_ID + "="
                + BookMarkID, null);

      //  database.rawQuery("DELETE FROM BookMark WHERE bookmarkid =" + BookMarkID, null);
      //  DELETE FROM COMPANY WHERE ID = 7;
      //  db.delete(TABLE_CONTACTS, KEY_ID + "='" + name + "'", null);
       // return db.delete(DATABASE_TABLE, KEY_NAME + "=" + name, null) > 0;

    }


}
