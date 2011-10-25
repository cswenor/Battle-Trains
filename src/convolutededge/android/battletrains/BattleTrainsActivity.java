package convolutededge.android.battletrains;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class BattleTrainsActivity extends Activity {
	private GameView mGameView;
    
    private static String ICICLE_KEY = "game-view";
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No Title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.game_layout);
        
        mGameView = (GameView) findViewById(R.id.train);
        mGameView.setTextView((TextView) findViewById(R.id.text));

        if (savedInstanceState == null) {
            // We were just launched -- set up a new game
            mGameView.setMode(GameView.READY);
        } else {
            // We are being restored
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                mGameView.restoreState(map);
            } else {
                mGameView.setMode(GameView.PAUSE);
            }
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        // Pause the game along with the activity
        mGameView.setMode(GameView.PAUSE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Store the game state
        outState.putBundle(ICICLE_KEY, mGameView.saveState());
    }
}