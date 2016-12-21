package com.adgvcxz.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.adgvcxz.rulerrecycleriew.OnRulerScrollListener;
import com.adgvcxz.rulerrecycleriew.RulerAdapter;
import com.adgvcxz.rulerrecycleriew.RulerSnapHelper;

public class MainActivity extends AppCompatActivity implements OnRulerScrollListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ac_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        int lineWidth = getResources().getDimensionPixelSize(R.dimen.line_width);
        int scaleWidth = getResources().getDimensionPixelSize(R.dimen.scale_width);
        recyclerView.setAdapter(new RulerAdapter.Builder(recyclerView).setLineAndScale(lineWidth, scaleWidth)
                .setNumberAndGroup(100, 10)
                .setOnRulerScrollListener(this)
                .setLineLength(1.0f, 0.5f)
                .setTextSizeAndColor(12, Color.GRAY)
                .setEdge(true)
                .setScaleLineColor(Color.GRAY)
                .setStartScaleLine(0).build());
//        new RulerSnapHelper().attachToRecyclerView(recyclerView);
    }

    @Override
    public String getScaleValue(int scale) {
        return "" + scale;
    }


    @Override
    public void onScaleChange(int scale) {
//        Log.e("zhaow", "scale" + scale);
    }
}
