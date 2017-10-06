package com.example.andres.rioloro;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class IndexEspecies extends AppCompatActivity {

    private RecyclerView recyclerView;
    private viewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_especies);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View layout=inflater.inflate(R.layout.activity_index_especies,container,false);
        recyclerView=(RecyclerView) layout.findViewById(R.id.drawerList);
        adapter=new viewAdapter(getApplicationContext(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        return layout;
    }

    public static List<informationx> getData(){
        List<informationx> data=new ArrayList<>();
        int[] icons={R.drawable.ic_dashboard_black_24dp,R.drawable.ic_home_black_24dp,
                R.drawable.ic_notifications_black_24dp,R.mipmap.ic_launcher};
        String[] titles={"Mariposa","Lombriz","Árbol Guanacaste","Jilgero"};
        for (int i=0;i<titles.length && i<icons.length;i++){
            informationx current=new informationx();
            current.iconId=icons[i];
            current.title=titles[i];
            data.add(current);
        }
        return data;
    }
}
