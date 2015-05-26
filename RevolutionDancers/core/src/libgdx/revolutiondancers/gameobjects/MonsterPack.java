package libgdx.revolutiondancers.gameobjects;

import libgdx.revolutiondancers.engine.GameObjectPoolable;
import libgdx.revolutiondancers.gameobjects.ArrowUI.ArrowDirection;
import libgdx.revolutiondancers.screens.GameScreen;
import libgdx.revolutiondancers.screens.ScreenAbstract;

/**
 * This class represents a MonsterPack inside a Dungeon;
 * It contains 4 Monsters inside each pack;
 * It contains their position inside the dungeon and is responsible for moving the Pack inside the Dungeon; [Protecting some area or searching the Player or Randomly];
 * It randomizes its monsters to contain a varied pack of enemies;
 * It controls the end of the Fight! When all 4 monsters are dead, then the fight is over!
 * It also controls everything else about the fight! So the GameScreen only has to talk to this guy to make a fight happen!
 * Since it owns its 4 monsters; Its its responsability to poll each monster and check whats happening;
 * **/
public class MonsterPack extends GameObjectPoolable {

	public short deadOnThePack = 0;
	public final Monster[] monsterPack = new Monster[4];
	public boolean battleEnded; //This is more like a meditative game by now; There isnt really a lose condition, if you miss keys it just takes longer to complete the song and that Monster will attack you; So theres no 'playerHasWon' condition;
								//But we can still think about that later and just put a float playerLife or something; And always show a 'Your Dead' screen when it reaches zero or below zero;
	public boolean isCurrent;
	
	//A monster pack has a Sprite representation inside the 3D world; The correspondent sprite is the 'leader' of the pack;
	//It is always facing the player, like a Wolfeinstein/Doom character;
	@Override
	public void init(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		boundingBox.set(x, z, 4*Wall.width, 4*Wall.depth);
		battleEnded = false;
		isCurrent = true;
		
		for(short i = 0; i < monsterPack.length; i++){
			monsterPack[i] = GameScreen.monsterPool.obtain();
			monsterPack[i].init(x, y, z, ArrowDirection.values()[i]);
		}
			//Creates 4 monsters; Each corresponding to each 4 possible directions!
	}

	@Override //Dont use this! Use the other one instead;
	public void init(float x, float y) {}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getResizeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getResizeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getResizeDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setResetMeTrue() {
		// TODO Auto-generated method stub
		
	}
	
	////////////////////////////////////////////////
	@Override
	public void input() {
		if(GameScreen.isInBattle() && isCurrent)
		{
			for (Monster monster : monsterPack) {
				monster.input();
			}
		}
	}

	@Override
	public void update() {
		if(GameScreen.isInBattle() && isCurrent)
		{
			for (Monster monster : monsterPack) {
				monster.update();
				if(monster.lifeAmount <= 0) deadOnThePack++; 	//Monstros mortos continuam sendo renderizados, soh que mortos; //Fazer um Xiszinho em cima ou sei lah.... Pintar de cinza....
			}
		//Do decisions about the battle;
		//Warn the screen by setting up flags, etc;
		
			if(deadOnThePack >= 4) {
				for (Monster monster : monsterPack) {
					GameScreen.monsterPool.free(monster); 
				}
					GameScreen.monsterPackPool.free(this);
					ScreenAbstract.freePoolableObject3D(this); 		//Uma MonsterPack eh o unico GameObject que eh realmente adicionado na tela de jogo;
				
				battleEnded = true;		//The screen has to take this as a hint to set isInBattle to false;
			}
		}
		
		//Else ainda estamos andando por ai em 3D
	}

	@Override
	public void draw() {
		if(GameScreen.isInBattle() && isCurrent)
		{
			for (Monster monster : monsterPack) {
				monster.draw();
			}
		}
		
		//Else, ainda estamos andando por ai em 3D
		
	}
	///////////////////////////////////////////////
	
}
