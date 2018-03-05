package com.example.homepc.webservicemoviedb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.example.homepc.webservicemoviedb.Movie.*;
import static com.example.homepc.webservicemoviedb.Movie.movieList;

/**
 * In this c;ass we create a View Holder and bind data to it.
 */

public class CustomListViewAdapter extends RecyclerView.Adapter<CustomListViewAdapter.MyViewHolder> {

    private List<Movie> movieList;

    //Initializing the constructor with the movieList
    public CustomListViewAdapter(List<Movie> movieList) {
        this.movieList=Movie.movieList  ;
        Log.i("MYRV","List passed, size :  "+this.movieList.size());
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_view,parent,false);
        Log.i("MYRV","Holder created");
        return new MyViewHolder(itemView); // this is an important step, without it view is not returned to holder
    }


    //below we have used two different ways to setText for our views :-
    // 1) using Movie class's method, after it is made to point to a specific List item in previous step
    //2) using List directly followed by 'position', followed by the method from Movie class
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
                Movie movie= movieList.get(position);
                holder.name.setText(movie.getmName());
                holder.vote.setText(movieList.get(position).getmVotes());
                holder.id.setText(movieList.get(position).getmId());
                Log.i("MYRV","Holder binded");
    }

    @Override
    public int getItemCount() {
        Log.i("MYRV","item count "+this.movieList.size());
        return this.movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,vote,id;

        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.movieNameTV);
            vote=(TextView)itemView.findViewById(R.id.votesTV);
            id=(TextView)itemView.findViewById(R.id.idTV);
            Log.i("MYRV","Holder initialized");
        }
    }
}
