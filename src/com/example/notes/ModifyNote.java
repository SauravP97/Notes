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

public class ModifyNote extends Activity {

	EditText rowid3,notesdata2;
	Button open2,modify;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify);
		rowid3=(EditText)findViewById(R.id.rowid3);
		open2=(Button)findViewById(R.id.open2);
		modify=(Button)findViewById(R.id.modify);
		notesdata2=(EditText)findViewById(R.id.notesdata2);
	
		open2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			 int id2=Integer.parseInt(rowid3.getText().toString());
				
			boolean opened=true;
		    try{
				NotesDatabase modb = new NotesDatabase(ModifyNote.this);
				modb.open();
				
				String modify=modb.getRowId(id2);
				
				modb.close();
				notesdata2.setText(modify);
			}catch(Exception e){
				 opened=false;
				  if(!opened){ 
					 Dialog ds = new Dialog(ModifyNote.this);
		    		 ds.setTitle("OPENING");
		    		 TextView tv = new TextView(ModifyNote.this);
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
	
	   modify.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
             String noted = notesdata2.getText().toString();
             int id3=Integer.parseInt(rowid3.getText().toString());
              boolean modified=true;
             try{
             NotesDatabase ndbs = new NotesDatabase(ModifyNote.this);
             ndbs.open();
             
             ndbs.modified(id3,noted);
             
             ndbs.close();
            }catch(Exception e){
            	 modified=false;
				  if(!modified){ 
					 Dialog ds = new Dialog(ModifyNote.this);
		    		 ds.setTitle("MODIFYING");
		    		 TextView tv = new TextView(ModifyNote.this);
		    		 tv.setText("FAILED \n No note exist with this id");
		    		 ds.setContentView(tv);
		    		 ds.show(); 
            
				  }
            }finally{
            	if(modified){
					Context context = getApplicationContext();
		    		 CharSequence text = "MODIFIED!";
		    		 int duration = Toast.LENGTH_SHORT;
		    		 
		    		 Toast toast = Toast.makeText(context, text, duration);
		    		 toast.show();
				     notesdata2.setText("");
            	}
            }
         }   
	});
	}

	
}
