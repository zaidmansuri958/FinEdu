package com.example.finedu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar tool;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    MeowBottomNavigation bnv;
    FirebaseAuth mAuth;
    TextView name,email,number;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new module()).commit();
        setContentView(R.layout.activity_main);

//        Chek user is connected to the internet or not
        if(!internet(MainActivity.this)){
            showDialog();
        }

//        SET THE NAVIGATION DRAWER

        tool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        nav = (NavigationView) findViewById(R.id.nav);
        View headerView=nav.getHeaderView(0);

        toggle=new ActionBarDrawerToggle(this,drawer,tool,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.yellow));
        toggle.syncState();

//        CREATE FIREBASE AUTHENTICATION REFRENCE

        mAuth=FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("User");
        String user_id=mAuth.getUid().toString();
        name=(TextView)headerView.findViewById(R.id.user_name);
        number=(TextView)headerView.findViewById(R.id.user_number);
        email=(TextView)headerView.findViewById(R.id.user_email);

//        SET CLICKONLISTNER ON MENU ITEMS

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.nav_stockMarket:
                        Intent stock=new Intent(MainActivity.this,StockMarket.class);
                        startActivity(stock);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_fundamental:
                        Intent fundamental=new Intent(MainActivity.this,Fundamental.class);
                        startActivity(fundamental);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_technical:
                        Intent technical=new Intent(MainActivity.this,Technical.class);
                        startActivity(technical);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_future:
                        Intent future=new Intent(MainActivity.this,Futures.class);
                        startActivity(future);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_option:
                        Intent option=new Intent(MainActivity.this,Options.class);
                        startActivity(option);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_forex:
                        Intent forex=new Intent(MainActivity.this,Forex.class);
                        startActivity(forex);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_taxation:
                        Intent taxation=new Intent(MainActivity.this,Taxation.class);
                        startActivity(taxation);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_logout:
                        mAuth.signOut();
                        Toast.makeText(MainActivity.this,"Successfully Logout!!",Toast.LENGTH_SHORT).show();
                        Intent logout=new Intent(MainActivity.this,Login.class);
                        startActivity(logout);
                        finish();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });



//        get data from the Firebase and set into headert section


        reference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText("Hello,"+" "+snapshot.child("name").getValue().toString());
                number.setText(snapshot.child("number").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "does not fetch user data", Toast.LENGTH_SHORT).show();

            }
        });

//        create a bottom navigation bar

        bnv = findViewById(R.id.bottomNav);
        bnv.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        bnv.add(new MeowBottomNavigation.Model(2, R.drawable.book));

        bnv.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bnv.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment temp = null;
                switch (item.getId()) {
                    case 1:
                        temp = new module();
                        break;
                    case 2:
                        temp = new books();
                        break;

                }
                loadFragment(temp);
            }

            private void loadFragment(Fragment temp) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,temp);
                transaction.addToBackStack(null);
                transaction.commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame, temp).commit();
            }
        });

        bnv.show(1, true);

    }

//    if internet is not on then show the dialog box function

    private void showDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Please connect to the internet to proceed further").
                setCancelable(false).
                setPositiveButton("connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(intent);
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog= dialog.create();
        alertDialog.show();

    }

//    chek that internet is on or not function

    private boolean internet(MainActivity mainActivity) {
        ConnectivityManager connectivityManager= (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifi!=null&&wifi.isConnected())||(mobile!=null&&mobile.isConnected())){
            return true;
        }
        else{
            return false;
        }
    }
}