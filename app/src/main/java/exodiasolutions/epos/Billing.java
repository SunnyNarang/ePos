package exodiasolutions.epos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import exodiasolutions.epos.CustomViews.TV_Muskan;
import exodiasolutions.epos.Models.Bill_Item;
import exodiasolutions.epos.ReadWrite.ReadWrite;

public class Billing extends AppCompatActivity {
    CustomAdapter3 adapter;
    private IntentIntegrator qrScan;
    int total =0;
    TV_Muskan total_tv;
    ArrayList<Bill_Item> arrayList_hidden = new ArrayList<>();
    ArrayList<Bill_Item> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        getSupportActionBar().hide();
        total_tv = findViewById(R.id.total_tv);
        total_tv.setText("Total = "+total);
        String items_json = ReadWrite.readItems("items",getFilesDir());

        try {
            JSONArray jsonArray = new JSONArray(items_json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                arrayList_hidden.add(new Bill_Item(obj.getString("id"),obj.getString("name"),obj.getString("price")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListView listView = (ListView) findViewById(R.id.item_listview);
        adapter = new CustomAdapter3(Billing.this,arrayList);
        listView.setAdapter(adapter);


    }

    class CustomAdapter3 extends ArrayAdapter<Bill_Item> {
        Context c;
        public CustomAdapter3(Context context, ArrayList<Bill_Item> arrayList) {
            super(context, R.layout.bill_item, arrayList);
            this.c = context;
        }
        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.bill_item, parent, false);
            Bill_Item s = getItem(pos);
            TextView s_no_tv = (TextView) convertView.findViewById(R.id.s_no);
            TextView name_tv = (TextView) convertView.findViewById(R.id.item);
            TextView price_tv = (TextView) convertView.findViewById(R.id.price);
            TextView quantity_tv = (TextView) convertView.findViewById(R.id.quantity);

            s_no_tv.setText(s.getS_no());
            name_tv.setText(s.getName());
            price_tv.setText(s.getPrice());
            quantity_tv.setText("1");

            return convertView;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data).getContents().toString();
        for(int i=0;i<arrayList_hidden.size();i++){
            Bill_Item bill_item = arrayList_hidden.get(i);
            if(bill_item.getS_no().equalsIgnoreCase(result)){
                arrayList.add(bill_item);
                total+=Integer.parseInt(bill_item.getPrice());
                break;
                }
        }
        total_tv.setText("Total = "+total);
        adapter.notifyDataSetChanged();

    }

    public void add_item(View v){
        qrScan = new IntentIntegrator(this);
        // qrScan.setOrientationLocked(true);
        //initiating the qr code scan
        qrScan.initiateScan();
    }


}
