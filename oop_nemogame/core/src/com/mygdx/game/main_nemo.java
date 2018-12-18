package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import GameManager.GameStateManager;

public class main_nemo extends ApplicationAdapter {
	
	GameStateManager gsm;
	
	@Override
	public void create () {
		
		gsm = new GameStateManager();
		gsm.setState(GameStateManager.MENU);
		
	}

	@Override
	public void render () {
		gsm.render();
		
	}
	
	@Override
	public void dispose () {
		
		gsm.dispose();
		
	}
}
