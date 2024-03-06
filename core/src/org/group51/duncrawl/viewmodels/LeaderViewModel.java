package org.group51.duncrawl.viewmodels;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import org.group51.duncrawl.abstracts.ScreenBase;
import org.group51.duncrawl.models.Leaderboard;

public class LeaderViewModel extends MasterViewModel {
    private ScreenBase linkedView;
    public LeaderViewModel(ScreenBase linkedView) {
        this.linkedView = linkedView;
    }

    public void handleLeaderBoard(Table leaderEntries) {
        Leaderboard leaderInfo = Leaderboard.getBoard();
        Label entry = new Label("Last Attempt: "
                + leaderInfo.getLastAttempt().toString(), theme);
        entry.setFontScale(2);
        leaderEntries.add(entry);
        leaderEntries.row();
        int counter = 0;
        for (Leaderboard.PlayerScore score : leaderInfo.getTopK(10)) {
            if (score != null) {
                counter++;
                entry = new Label(counter + ". " + score.toString(), theme);
                entry.setFontScale(2);
                leaderEntries.add(entry);
                leaderEntries.row();
            }
        }
    }
}
