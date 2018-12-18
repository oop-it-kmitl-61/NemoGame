package GameManager;

import com.mygdx.game.FinishState;
import com.mygdx.game.MenuStage;
import com.mygdx.game.PlayState;
import com.mygdx.game.ScoreState;
import com.mygdx.game.TutorialState;

public class GameStateManager {

	public static GameState state;
	
	public static final int END = 3;
	public static final int PLAY = 1;
	public static final int MENU = 0;
	public static final int HIGH = 4;
	public static final int TUTOR = 5;
	
	public void setState(int state) {
		
		if (this.state != null) {
			this.state.dispose();
		}
		
		if(state == MENU) {
			this.state = new MenuStage(this);
		}
		
		if(state == PLAY) {
			this.state = new PlayState(this);
		}
		
		if(state == END) {
			this.state = new FinishState(this);
		}
		if(state == HIGH) {
			this.state = new ScoreState(this);
		}
		if(state == TUTOR) {
			this.state = new TutorialState(this);
		}
		
		this.state.create();
		
	}
	
	
	public void render() {
		state.render();
	}
	
	public void dispose() {
		state.dispose();
	}
	
	
}
