package exodiasolutions.epos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import exodiasolutions.epos.ReadWrite.ReadWrite;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        final MyHttpClient myHttpClient = new MyHttpClient(Home.this,"https://vintagevow-sunnynarang.c9users.io/pos/getitems.php",null);
        myHttpClient.execute();
        myHttpClient.callback = new MyCallback() {
            @Override
            public void callbackCall() {
                //Toast.makeText(Home.this, ""+myHttpClient.result, Toast.LENGTH_SHORT).show();
                if(myHttpClient.result.charAt(0)=='['||myHttpClient.result.charAt(0)=='{');
               ReadWrite.writeItems(myHttpClient.result,"items",getFilesDir());
            }
        };



    }

    public void billing(View v){
        Intent i = new Intent(Home.this,Billing.class);
        startActivity(i);



    }

}
