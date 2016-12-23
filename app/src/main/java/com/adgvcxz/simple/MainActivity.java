package com.adgvcxz.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.adgvcxz.rulerrecycleriew.OnRulerScrollListener;
import com.adgvcxz.rulerrecycleriew.RulerAdapter;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnRulerScrollListener {

    private static final DecimalFormat DF0 = new DecimalFormat("#.0");

    private TextView textView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ac_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        int lineWidth = getResources().getDimensionPixelSize(R.dimen.line_width);
        int scaleWidth = getResources().getDimensionPixelSize(R.dimen.scale_width);
        recyclerView.setAdapter(new RulerAdapter.Builder(recyclerView).setLineAndScale(lineWidth, scaleWidth)
                .setNumberAndGroup(2700, 10)
                .setOnRulerScrollListener(this)
                .setLineLength(0.9f, 0.5f)
                .setTextSizeAndColor(12, Color.GRAY)
                .setEdge(true)
                .setScaleLineColor(Color.GRAY)
                .setStartScaleLine(300).build());
        textView0 = (TextView) findViewById(R.id.ac_text_0);
        textView0.setText("30.0 kg");
    }

    @Override
    public String getScaleValue(int scale) {
        return "" + (scale / 10 + 30);
    }


    @Override
    public void onScaleChange(int scale) {
        textView0.setText(String.format(Locale.getDefault(), "%s kg", DF0.format((float) (300 + scale) / 10)));
    }
}
