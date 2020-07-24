package com.example.nitishchandra.dyslexia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String email = getIntent().getStringExtra("Useremail");
        int id = item.getItemId();
        switch (id)
        {
            case R.id.profile:
                Intent p=new Intent(Home.this,profile.class);
                p.putExtra("Useremail", email);
                startActivity(p);
                break;
            case R.id.about:
                Intent a=new Intent(Home.this,About.class);
                startActivity(a);
                break;
            case R.id.map:
            //    Intent m=new Intent(Home.this,map.class);
              //  startActivity(m);
                break;
            case R.id.previousresult:
                Intent pr=new Intent(Home.this,checkPreviousHistory.class);
                startActivity(pr);
                break;
            case R.id.contact:
                Intent c=new Intent(Home.this,Contact.class);
                startActivity(c);
                break;
            case R.id.logout:
               // SharedPrefs.saveSharedSetting(Home.this,"StateSave","true");
                Intent l=new Intent(Home.this,login.class);
                l.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(l);
              //  finish();
              //  checkSession();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void checkSession()
    {
        Boolean check = Boolean.valueOf(SharedPrefs.readSharedSetting(Home.this,"StateSave","true"));

        Intent in= new Intent(Home.this,login.class);
        in.putExtra("StateSave",check);

        if(check)
        {
          startActivity(in);
          finish();
        }
    }

    public void onGeneralStart(View v)
    {
        if (v.getId() == R.id.generalTestButton)
        {
            startActivity(new Intent(Home.this,GeneralTestStart.class));
        }
    }
    public void onPhonoStart(View v)
    {
        if(v.getId()==R.id.phono)
        {
             Intent i=new Intent(this,Phono_test_start.class);
             startActivity(i);
        }
    }
    public void onVisual(View v)
    {
        if(v.getId()==R.id.visual)
        {
            Intent i=new Intent(this,visualLevel1.class);
            startActivity(i);
        }
    }
    public void onCalculiaStart(View v)
    {
        if(v.getId()==R.id.dyscalculia)
        {
            Intent i=new Intent(this,Dyscalculia_start.class);
            startActivity(i);
        }
    }
}
