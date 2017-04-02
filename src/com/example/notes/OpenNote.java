package com.example.notes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OpenNote extends Activity {

	EditText rowid;
	Button open;
	TextView notesbar;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open);
		rowid=(EditText)findViewById(R.id.rowid);
		open=(Button)findViewById(R.id.open);
		notesbar=(TextView)findViewById(R.id.notesbar);
		
		open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String id=rowid.getText().toString();
				int rowid=Integer.parseInt(id);
			  boolean opened=true;
			  try{	
				NotesDatabase ndb = new NotesDatabase(OpenNote.this);
				
				ndb.open();
				
				String data2=ndb.getRowId(rowid);
				
				ndb.close();
				notesbar.setText(data2);
			 }catch(Exception e){
				 opened=false;
				  if(!opened){ 
					 Dialog ds = new Dialog(OpenNote.this);
		    		 ds.setTitle("OPENING");
		    		 TextView tv = new TextView(OpenNote.this);
		    		 tv.setText("FAILED \n No note exist with this id");
		    		 ds.setContentView(tv);
		    		 ds.show(); 
			 
				  }
			 }finally{
				 if(opened){
						Context context = getApplicationContext();
			    		 CharSequence text = "OPENED!";
			    		 int duration = Toast.LENGTH_SHORT;
			    		 
			    		 Toast toast = Toast.makeText(context, text, duration);
			    		 toast.show();
					}
			 }
			 }
		});
	}
      
	
}
