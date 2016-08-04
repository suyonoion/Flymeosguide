package com.suyonoion.flymeosguide;

/**
 * Created by Suyono on 4/11/2015.
 */
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class utama extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utama);

        TabHost tabHost = getTabHost();

        TabSpec tentangspec = tabHost.newTabSpec("Info");
        tentangspec.setIndicator("Info", getResources().getDrawable(R.drawable.icon_tentang_tab));
        Intent tentangIntent = new Intent(this, TentangTab.class);
        tentangspec.setContent(tentangIntent);

        TabSpec kodespec = tabHost.newTabSpec("Kode");
        kodespec.setIndicator("Kode", getResources().getDrawable(R.drawable.icon_kode_tab));
        Intent kodeIntent = new Intent(this, home.class);
        kodespec.setContent(kodeIntent);

        TabSpec previewspec = tabHost.newTabSpec("Preview");
        previewspec.setIndicator("Preview", getResources().getDrawable(R.drawable.ic_launcher));
        Intent previewIntent = new Intent(this, aktiviti_pertama.class);
        previewspec.setContent(previewIntent);

        TabSpec guidespec = tabHost.newTabSpec("Guide");

        guidespec.setIndicator("Guide", getResources().getDrawable(R.drawable.icon_guide_tab));
        Intent guideIntent = new Intent(this, GuideTab.class);
        guidespec.setContent(guideIntent);

        TabSpec fiturspec = tabHost.newTabSpec("Fitur");
        fiturspec.setIndicator("Fitur", getResources().getDrawable(R.drawable.icon_fitur_tab));
        Intent fiturIntent = new Intent(this, fitur.class);
        fiturspec.setContent(fiturIntent);

        tabHost.addTab(tentangspec);
        tabHost.addTab(kodespec);
        tabHost.addTab(previewspec);
        tabHost.addTab(guidespec);
        tabHost.addTab(fiturspec);
    }
}
