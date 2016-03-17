package com.nullcognition.sqldelight.hockey.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.nullcognition.sqldelight.hockey.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.list) public void showPlayers(){
        startActivity(new Intent(this, PlayersActivity.class));
    }
    @OnClick(R.id.teams) public void showTeams(){
        startActivity(new Intent(this, TeamsActivity.class));
    }
}
