package com.adgvcxz.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adgvcxz.rulerrecycleriew.RulerAdapter;
import com.adgvcxz.rulerrecycleriew.RulerSnapHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ac_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new RulerAdapter.Builder().setWidthAndSpacing(recyclerView, 3, 57).build());
//        new RulerSnapHelper().attachToRecyclerView(recyclerView);
    }
}
