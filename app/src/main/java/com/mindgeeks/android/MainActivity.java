package com.mindgeeks.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.mindgeeks.offerwall.offersList;
import com.mindgeeks.offerwall.utils.Constans;
import com.mindgeeks.offerwall.utils.OfferWallHandler;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final int CONTENT_VIEW_ID = 10101010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // new OfferActivity().getData("3837ca03-41dd-402f-8ce5-ea6d74ac0127");

        Map<String, Object> properties=OfferWallHandler.getOfferWallProperties("3837ca03-41dd-402f-8ce5-ea6d74ac0127","var1");
        properties.put(Constans.PROPERTY_SCREEN_BACKGROUND_COLOR,"#3700B3");
        properties.put(Constans.PROPERTY_TASK_BAR_BACKGROUND_COLOR,"#3700B3");
        properties.put(Constans.PROPERTY_ACTION_BAR_BACKGROUND_COLOR,"#8B8E94");
        properties.put(Constans.PROPERTY_ACTION_BAR_TITLE_COLOR,"#ffffff");
        properties.put(Constans.PROPERTY_ACTION_BAR_TITLE_TEXT,"My App");
        properties.put(Constans.PROPERTY_ACTION_BAR_BACK_BUTTON_COLOR,"#3700B3");


        Log.d("TAG", "properties: "+properties);
        OfferWallHandler wallHandler=new OfferWallHandler(properties,this);
        wallHandler.startOfferWall();


       /* Intent intent=new Intent(this, OfferActivity.class);
        Bundle arg=new Bundle();
        arg.putString("securityToken_key","3837ca03-41dd-402f-8ce5-ea6d74ac0127");
        intent.putExtras(arg);
        startActivity(intent);*/

       // loadFragment();
        //getOfferWall();
//       request.requestData("2","1.0","1","462ed61f-5363-495d-b73e-f50f54af7650");
       // OffersWall offersWall = new OffersWall(MainActivity.this,"2");

      // OffersWall.getOfferWall();
        // offersWall.getOfferWall("2","1.0","1","462ed61f-5363-495d-b73e-f50f54af7650");

    }

    private void loadFragment() {
        Fragment fragment;
        fragment = new offersList();
        offersList.newInstance("3837ca03-41dd-402f-8ce5-ea6d74ac0127");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//frame_container is your layout name in xml file
        transaction.replace(R.id.frame_container, fragment);
       // transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        request.requestData("2","1.0","1","462ed61f-5363-495d-b73e-f50f54af7650");

    }

    @Override
    protected void onResume() {
        super.onResume();
      //  request.requestData("2","1.0","1","462ed61f-5363-495d-b73e-f50f54af7650");

    }
}