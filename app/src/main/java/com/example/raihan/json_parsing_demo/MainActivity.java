package com.example.raihan.json_parsing_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


  //  TextView show_data;
  // String t1="";
   // String n1="";
ListView lv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       lv=(ListView)findViewById(R.id.myListview);

        fetchingData();
    }


 void fetchingData(){



String myURL="http://mdomarfaruk.com/api/wiki.php?search=Bangladesh";
    JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(myURL, new Response.Listener<JSONArray>(){

     @Override
         public void onResponse(JSONArray response){


          String[] news_title= new String[response.length()];
         String[] news_p= new String[response.length()];
                for(int i=0;i<response.length();i++){


                 try {
                    JSONObject jsonObject=(JSONObject) response.get(i);
                    //  t1=t1+ jsonObject.getString("title"+"\n\n");
                    // n1=n1+ jsonObject.getString("news"+"\n\n ");
                    // int foo = Integer.parseInt(jsonObject.getString("0"));


                     news_title[i]=jsonObject.getString("title");
                     news_p[i]=jsonObject.getString("p");

                } catch (JSONException e) {
                     e.printStackTrace();
           }


          }
              //show_data.setText(t1) ;
         lv.setAdapter(new ArrayAdapter(getApplicationContext(),R.layout.mylistview,R.id.textviewforlist,news_p));


          }



    },new Response.ErrorListener(){
    @Override

        public  void onErrorResponse(VolleyError error){

        VolleyLog.d("volley Log",error);
    }

    });



    com.example.raihan.json_parsing_demo.AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    Toast.makeText(getApplicationContext(),"Data add",Toast.LENGTH_LONG).show();
}



}
