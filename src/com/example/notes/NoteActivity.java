package com.example.notes;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class NoteActivity extends ListActivity {
    /** Called when the activity is first created. */
    
    String menu1[]={"CreateNew","OpenNote","ViewNote","DeleteNote","ModifyNote"};
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(new ArrayAdapter<String>(NoteActivity.this,android.R.layout.simple_list_item_1,menu1));
        
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String selected=menu1[position];
		try{
			Class ourClass=Class.forName("com.example.notes." + selected);
			Intent ourIntent=new Intent(NoteActivity.this,ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
}