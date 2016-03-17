package com.nullcognition.sqldelight.hockey.ui;


import android.app.Activity;
import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CursorAdapter;
        import android.widget.ListView;

import com.nullcognition.sqldelight.hockey.R;
import com.nullcognition.sqldelight.hockey.data.HockeyOpenHelper;
import com.nullcognition.sqldelight.hockey.data.Player;
import com.nullcognition.sqldelight.hockey.data.Team;

import butterknife.Bind;
        import butterknife.ButterKnife;

public final class PlayersActivity extends Activity {
    public static final String TEAM_ID = "team_id";

    @Bind(R.id.list) ListView players;

    private Cursor playersCursor;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ButterKnife.bind(this);

        SQLiteDatabase db = HockeyOpenHelper.getInstance(this).getReadableDatabase();
        long teamId = getIntent().getLongExtra(TEAM_ID, -1);
        if (teamId == -1) {
            playersCursor = db.rawQuery(Player.SELECT_ALL, new String[0]);
        } else {
            playersCursor = db.rawQuery(Player.FOR_TEAM, new String[] { String.valueOf(teamId) });
        }
        players.setAdapter(new PlayersAdapter(this, playersCursor));
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        playersCursor.close();
    }

    private static final class PlayersAdapter extends CursorAdapter {
        public PlayersAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return View.inflate(context, R.layout.player_row, null);
        }

        @Override public void bindView(View view, Context context, Cursor cursor) {
            ((PlayerRow) view).populate(Player.MAPPER.map(cursor), Team.MAPPER.map(cursor));
        }
    }
}