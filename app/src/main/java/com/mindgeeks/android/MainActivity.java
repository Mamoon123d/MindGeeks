package com.mindgeeks.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mindgeeks.offerwall.OfferActivity;
import com.mindgeeks.offerwall.OffersWall;
import com.mindgeeks.offerwall.offersList;
import com.mindgeeks.offerwall.utils.CreateRequest;

public class MainActivity extends AppCompatActivity {
    private static final int CONTENT_VIEW_ID = 10101010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this, OfferActivity.class);
        Bundle arg=new Bundle();
        arg.putString("securityToken_key","3837ca03-41dd-402f-8ce5-ea6d74ac0127");
        intent.putExtras(arg);
        startActivity(intent);

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