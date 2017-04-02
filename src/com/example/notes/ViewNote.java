package com.example.notes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewNote extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.viewdata);
		TextView data1 = (TextView)findViewById(R.id.data1);
		try{
		NotesDatabase datab=new NotesDatabase(ViewNote.this);
		datab.open();
		String info=datab.getData();
		datab.close();
		data1.setText(info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	  
}
