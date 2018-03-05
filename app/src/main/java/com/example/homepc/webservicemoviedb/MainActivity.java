package com.example.homepc.webservicemoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    //creating necessary objects
    static RecyclerView rv;
    static CustomListViewAdapter customListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding recycler view
        rv=findViewById(R.id.recylerView1);

        //initializing webservice class
        MyWebService myWebService= (MyWebService) new MyWebService();

        //calling the method which  does ther webservice calls
        myWebService.CallMovieDB();

    }
}
