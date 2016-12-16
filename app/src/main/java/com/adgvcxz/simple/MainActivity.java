package com.adgvcxz.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adgvcxz.rulerrecycleriew.RulerAdapter;
import com.adgvcxz.rulerrecycleriew.RulerRecyclerView;
import com.adgvcxz.rulerrecycleriew.RulerSnapHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RulerRecyclerView recyclerView = (RulerRecyclerView) findViewById(R.id.ac_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLineWidthAndSpacing(3, 57);
//        new RulerSnapHelper().attachToRecyclerView(recyclerView);
    }
}
