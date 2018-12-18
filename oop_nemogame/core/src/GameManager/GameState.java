package GameManager;

public abstract class GameState {

	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public abstract void create();
	
	public abstract void render();
	
	public abstract void dispose();
	
}
