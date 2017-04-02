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

public class DeleteNote extends Activity{

	EditText deletion;
	Button delete;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
		deletion=(EditText)findViewById(R.id.rowid2);
		delete=(Button)findViewById(R.id.delete);
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int rowid2=Integer.parseInt(deletion.getText().toString());
				
		   boolean deleted=true;
		   try{
				NotesDatabase ndb2 = new NotesDatabase(DeleteNote.this);
				ndb2.open();
				 
				ndb2.delete(rowid2);
				
				ndb2.close();
			 }catch(Exception e){
				deleted=false;
			  if(!deleted){ 
				 Dialog ds = new Dialog(DeleteNote.this);
	    		 ds.setTitle("DELETION");
	    		 TextView tv = new TextView(DeleteNote.this);
	    		 tv.setText("FAILED");
	    		 ds.setContentView(tv);
	    		 ds.show(); 
			  }
			 }
			 finally{
				if(deleted){
					Context context = getApplicationContext();
		    		 CharSequence text = "DELETED!";
		    		 int duration = Toast.LENGTH_SHORT;
		    		 
		    		 Toast toast = Toast.makeText(context, text, duration);
		    		 toast.show();
		    		 deletion.setText("");
				}
			 }
			 
			}
		});
	}
	
	

}
