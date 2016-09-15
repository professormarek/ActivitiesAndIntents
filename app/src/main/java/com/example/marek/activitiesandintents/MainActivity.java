package com.example.marek.activitiesandintents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //once the activity is created let's assign some code to the Button when it is clicked
        Button msgButton = (Button)findViewById(R.id.a2_button);
        //assign code to the onClickListener by defining
        //an anonymous subclass of OnClickListener and overriding its onClickMethod
        //notice here we are calling new and passing the created OnClickListener to the "setOnClickListener" method instead of storing the reference as a variable
        //while at the same time overriding the onClick method. This is a common pattern seen in Java "in the wild"
        msgButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //in this example we will use the button to send a message
                //let's create an intent
                Intent msg_intent = new Intent("com.seneca.lab2b.marek");
                //add some extra information to the intent
                //fire off the intent
                //startActivityForResult expects both an intent and a request code so that you can match up the request with the reply
                //for now, keep things simple with a literal number for our request code
                startActivityForResult(msg_intent, 1);
            }
        });

        Button a3_button = (Button)findViewById(R.id.a3_button);
        a3_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //when sending an explicit intent use the Intent(Context, Intent) constructor
                Intent msg_intent = new Intent(v.getContext(), ThirdActivity.class);
                startActivity(msg_intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = "RESULT NOT SET";
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                result = data.getStringExtra("message");
            }else if(resultCode == Activity.RESULT_CANCELED){
                result = "user cancelled";
            }
        }else{
            Log.e("lab2", "SOMETHNG WENT REALLY WRONG");
        }

        TextView text = (TextView)findViewById(R.id.textView);
        text.setText(result);

        Context context = getApplicationContext();
        String toast_text = "You are cool " + result;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, toast_text, duration);
        toast.show();



    }
}
