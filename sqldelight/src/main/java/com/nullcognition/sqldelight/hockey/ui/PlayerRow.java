package com.nullcognition.sqldelight.hockey.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.nullcognition.sqldelight.hockey.R;
import com.nullcognition.sqldelight.hockey.data.Player;
import com.nullcognition.sqldelight.hockey.data.Team;

import butterknife.Bind;
import butterknife.ButterKnife;

public final class PlayerRow extends LinearLayout {
    @Bind(R.id.player_name) TextView playerName;
    @Bind(R.id.team_name) TextView teamName;
    @Bind(R.id.player_number)
    TextView playerNumber;

    public PlayerRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void populate(Player player, Team team) {
        playerName.setText(player.first_name() + " " + player.last_name());
        playerNumber.setText(String.valueOf(player.number()));
        teamName.setText(team.name());
    }
}