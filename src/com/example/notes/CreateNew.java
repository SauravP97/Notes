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

public class CreateNew extends Activity {
    String notes;
	Button save;
	EditText data;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    
		data=(EditText)findViewById(R.id.notesData);
	    save=(Button)findViewById(R.id.saveBtn);
	    
	    save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		      boolean diditwork=true;
			  try{
				notes=data.getText().toString();
				
				NotesDatabase createNew= new NotesDatabase(CreateNew.this);
				createNew.open();
				
				createNew.create(notes);
				
				createNew.close();
		     }catch(Exception e){
		    	 diditwork=false;
		    	 if(!diditwork){
		    		 Dialog d = new Dialog(CreateNew.this);
		    		 d.setTitle("SUBMISSION");
		    		 TextView tv = new TextView(CreateNew.this);
		    		 tv.setText("FAILED");
		    		 d.setContentView(tv);
		    		 d.show();
		    	 }
		     }
		     finally{
		    	 if(diditwork){
		    		 Context context = getApplicationContext();
		    		 CharSequence text = "SAVED!";
		    		 int duration = Toast.LENGTH_SHORT;
		    		 
		    		 Toast toast = Toast.makeText(context, text, duration);
		    		 toast.show();
		    		 data.setText("");
		    	 }
		     }
			}
		});
	}

}
