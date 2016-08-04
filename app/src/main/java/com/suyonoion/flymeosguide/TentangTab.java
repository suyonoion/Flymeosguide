package com.suyonoion.flymeosguide;

/**
 * Created by Suyono on 4/11/2015.
 */

import android.app.Activity;
import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

public class TentangTab extends Activity {

    public  ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
    CustomAdapter adapter;
    TentangTab activity = null;
    TextView input1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentangtab);

        activity  = this;


        Spinner  SpinnerExample = (Spinner)findViewById(R.id.spinner);


        setListData();
        Resources res = getResources();

        adapter = new CustomAdapter(activity, R.layout.spinner_rows, CustomListViewValuesArr,res);
        SpinnerExample.setAdapter(adapter);

        SpinnerExample.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here
                switch (position) {

                    case 1:

                        goToUrl ( "https://www.dropbox.com/s/lt7z2syoc1mvy02/GUIDE_FlymeOS%2BGuide_in_APK%2BBONUS.zip?dl=0");
                        break;
                    case 2:

                        goToUrl ( "https://www.dropbox.com/s/ja7um8ibq8n01rl/flymeOSGuide_zip_v2.zip?dl=0");
                        break;

                    case 3:

                        goToUrl ( "https://www.dropbox.com/s/gem0cs0bxkgill2/1.%20FlymeOS_Garuda_Style_by-ion.zip?dl=0");
                        break;
                    case 4:

                        goToUrl ( "https://www.dropbox.com/s/6ynrd9yzbqhyw0a/2.%20FlymeOS_3Jari_Style_by-ion.zip?dl=0");
                        break;
                    case 5:

                        goToUrl ( "https://www.dropbox.com/s/403d3p1chwvjgvh/3.%20FlymeOS_ANU_Style_by-ion.zip?dl=0");
                        break;
                    case 6:

                        goToUrl ( "https://www.dropbox.com/s/i7jt265521daift/%5BTESTED%5DFlymeOS_Garuda_Style_by-ion.zip?dl=0");
                        break;
                    case 7:

                        goToUrl ( "https://www.dropbox.com/s/jfrow0tmcun9fjj/%5BTESTED2%5DFlymeOS_3Jari_Style_by-ion.zip?dl=0");
                        break;
                    case 8:

                        goToUrl ( "https://www.dropbox.com/s/4rb1gre4bilt37x/%5BTESTED%5DFlymeOS_ANU_Style_by-ion.zip?dl=0");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    /****** Function to set data in ArrayList *************/
    public void setListData()
    {
        String nama0="Suyonoion";
        String nama1="Bahan FlymeOS v1.0 ALL";
        String nama2="Bahan FlymeOS v2.0 ALL";
        String nama3="Bahan Garuda Style";
        String nama4="Bahan 3 Jari";
        String nama5="Bahan Anu";
        String nama6="Bonus - Garuda SystemUI.apk FlashableZip";
        String nama7="Bonus - 3Jari SystemUI.apk FlashableZip";
        String nama8="Bonus - Anu SystemUI.apk FlashableZip";

        String imgion0="suyono";
        String imgion1="v1";
        String imgion2="v2";
        String imgion3="garuda1";
        String imgion4="tigajari";
        String imgion5="anu";
        String imgion6="suyono";
        String imgion7="suyono";
        String imgion8="suyono";

        String url0="Dropbox";
        String url1="Dropbox";
        String url2="Dropbox";
        String url3="Dropbox";
        String url4="Dropbox";
        String url5="Dropbox";
        String url6="Xperia M Single Only";
        String url7="Xperia M Single Only";
        String url8="Xperia M Single Only";

        /* 0 */

        final SpinnerModel ion = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion.setCompanyName(nama0);
        ion.setImage(imgion0);
        ion.setUrl(url0);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion);
        /* 1 */

        final SpinnerModel ion1 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion1.setCompanyName(nama1);
        ion1.setImage(imgion1);
        ion1.setUrl(url1);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion1);

        /* 2 */

        final SpinnerModel ion2 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion2.setCompanyName(nama2);
        ion2.setImage(imgion2);
        ion2.setUrl(url2);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion2);
        /* 3 */

        final SpinnerModel ion3 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion3.setCompanyName(nama3);
        ion3.setImage(imgion3);
        ion3.setUrl(url3);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion3);

        /* 4 */

        final SpinnerModel ion4 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion4.setCompanyName(nama4);
        ion4.setImage(imgion4);
        ion4.setUrl(url4);
        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion4);

        /* 5 */

        final SpinnerModel ion5 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion5.setCompanyName(nama5);
        ion5.setImage(imgion5);
        ion5.setUrl(url5);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion5);

        /* Flasahble Zip */

                /* 6 */

        final SpinnerModel ion6 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion6.setCompanyName(nama6);
        ion6.setImage(imgion6);
        ion6.setUrl(url6);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion6);

        /* 7 */

        final SpinnerModel ion7 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion7.setCompanyName(nama7);
        ion7.setImage(imgion7);
        ion7.setUrl(url7);
        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion7);

        /* 8 */

        final SpinnerModel ion8 = new SpinnerModel();

        /******* Firstly take data in model object ******/
        ion8.setCompanyName(nama8);
        ion8.setImage(imgion8);
        ion8.setUrl(url8);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(ion8);
    }

}
