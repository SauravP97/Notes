package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabase {

	  public static final String KEY_ROWID = "_id";
	  public static final String KEY_NOTE = "notes_data";
	  
	  public static final String DATABASE_NAME ="NotesDatabase";
	  public static final String DATABASE_TABLE="notesdata";
	  public static final int DATABASE_VERSION = 1;
	  
	  private DbHelper ourHelper;
	  private final Context ourContext;
	  private SQLiteDatabase ourDatabase;
	  
	  private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					     + KEY_NOTE + " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		  
	  }
	  public NotesDatabase(Context c){
		  ourContext=c;
	  }
	  public NotesDatabase open() throws SQLiteException{
		  ourHelper = new DbHelper(ourContext);
		  ourDatabase=ourHelper.getWritableDatabase();
		  return this;
		  
	  }
	  public void close(){
		  ourHelper.close();
	  }
	  public long create(String data){
		  ContentValues cv = new ContentValues();
		  cv.put(KEY_NOTE, data);
		  return  ourDatabase.insert(DATABASE_TABLE, null, cv);
	  }
	public String getData() {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID,KEY_NOTE};
		Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
		String result="";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iNotes = c.getColumnIndex(KEY_NOTE);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result = result + c.getString(iRow) + "           " + c.getString(iNotes) + "\n" ;
		}
		return result;
	}
	public String getRowId(int rowid) {
		// TODO Auto-generated method stub
		String[] column = new String[]{KEY_ROWID,KEY_NOTE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, column, KEY_ROWID + "=" + rowid, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String notes1 = c.getString(1);
			return notes1;
		}
		return null;
	}
	public void delete(int id2) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + id2, null);
		
		
	}
	public void modified(int id,String mod) {
		// TODO Auto-generated method stub
		ContentValues ucv = new ContentValues();
		ucv.put(KEY_NOTE, mod);
		
		ourDatabase.update(DATABASE_TABLE, ucv, KEY_ROWID + "=" + id , null);
		
		
		
	}
}

