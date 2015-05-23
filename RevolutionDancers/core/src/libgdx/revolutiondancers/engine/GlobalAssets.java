package libgdx.revolutiondancers.engine;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * Class where we'll keep globally loaded assets;
 * */
public abstract class GlobalAssets {

	Mesh cube;
	Mesh mojoGem;
	Mesh key;
	
	public static enum AssetType{Texture, Sound, Music}; //Etc

	
	public static int group1, group2, group3, group4, group5;  //Values can range from 0 to 9
	public static int group1Floor, group2Floor, group3Floor, group4Floor, group5Floor; //Values can range from 0 to 13 
	public static int group1Ceiling, group2Ceiling, group3Ceiling, group4Ceiling, group5Ceiling; //Values range from 0 to 13
	
	public static int group1WallsBoundLimit, group2WallsBoundLimit, group3WallsBoundLimit, group4WallsBoundLimit, group5WallsBoundLimit;  //These terribly named variables tell us our sortition Bound limit according to the group value;
	public static int group1WallDecorationsBoundLimit, group2WallDecorationsBoundLimit, group3WallDecorationsBoundLimit, group4WallDecorationsBoundLimit; 
	
	
	public static void loadGlobalAssets(){
		/*-Textures-*/
		//2D
		//DDR Gameplay
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/TemporarySplashScreen.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/UILayout.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/DanceDanceUILayout.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/DOWN.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/LEFT.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/RIGHT.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/UP.png", Texture.class);
		//Dungeon Crawling

		//Floor
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/5.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/6.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/7.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/8.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/9.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/10.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/11.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/12.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Floor/13.png", Texture.class);
		//Ceiling
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/5.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/6.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/7.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/8.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/9.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/10.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/11.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/12.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Ceiling/13.png", Texture.class);
		//Doors
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/5.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/6.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Doors/7.png", Texture.class);
		
		//Walls//
		//Global Decorations
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/5.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/6.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/7.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/8.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/9.png", Texture.class);   
		
		
		//GROUP 0:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/5.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/6.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/7.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/8.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/9.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/10.png", Texture.class);
		//GROUP 0 DECORATIONS:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/Decorations/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/Decorations/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/Decorations/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/Decorations/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/0/Decorations/4.png", Texture.class);
		
		//GROUP 1:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/5.png", Texture.class);
		//GROUP 1 DECORATIONS:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/Decorations/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/Decorations/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/Decorations/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/1/Decorations/3.png", Texture.class);
		
		//GROUP 2:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/2/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/2/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/2/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/2/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/2/4.png", Texture.class);
		//GROUP 2 DECORATIONS:	  
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/2/Decorations/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/2/Decorations/1.png", Texture.class);
		
		//GROUP 3:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/3/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/3/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/3/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/3/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/3/4.png", Texture.class);
		
		//GROUP 4:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/4/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/4/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/4/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/4/3.png", Texture.class);

		//GROUP 5:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/3.png", Texture.class);
		//GROUP 5 DECORATIONS:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/Decorations/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/Decorations/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/Decorations/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/Decorations/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/5/Decorations/4.png", Texture.class);
		
		//GROUP 6:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/6/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/6/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/6/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/6/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/6/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/6/5.png", Texture.class);
		
		//GROUP 7:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/7/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/7/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/7/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/7/3.png", Texture.class);
		//GROUP 7 DECORATIONS:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/7/Decorations/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/7/Decorations/1.png", Texture.class);
		
		//GROUP 8:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/5.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/6.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/8/7.png", Texture.class);
		
		//GROUP 9:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/5.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/6.png", Texture.class);
		//GROUP 9 DECORATIONS:
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/Decorations/0.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/Decorations/1.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/Decorations/2.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/Decorations/3.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/Decorations/4.png", Texture.class);
		Globals.assetManager.load("RevolutionDancersAssets/Graphics/2D/Walls/9/Decorations/5.png", Texture.class);
		
		
		/*-Textures-*/
		/*-Audio-*/
		//Music
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/tittleScreen.wav", Music.class);
		//notSoRandomEncounter
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/randomEncounterMusic.mp3", Music.class);
		//Dungeon
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Dungeon/0.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Dungeon/1.mp3", Music.class);
		//Battle
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/0.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/1.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/2.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/3.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/4.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/5.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/6.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/7.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/8.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/9.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/10.mp3", Music.class);
		Globals.assetManager.load("RevolutionDancersAssets/Audio/Music/Battle/11.mp3", Music.class);
		
		
		nextDungeonTextureGroups();
		
		
		Globals.assetManager.finishLoading();
	}
	
	public static void nextDungeonTextureGroups(){
		group1 = Math.abs(Globals.randomGenerator2.nextInt(9));
		group2 = Math.abs(Globals.randomGenerator2.nextInt(9));
		group3 = Math.abs(Globals.randomGenerator2.nextInt(9));		
		group4 = Math.abs(Globals.randomGenerator2.nextInt(9));
		group5 = Math.abs(Globals.randomGenerator2.nextInt(9));

		/*group2 = findDifferentGroupIfNecessary(group2);
		group3 = findDifferentGroupIfNecessary(group3);
		group4 = findDifferentGroupIfNecessary(group4);
		group5 = findDifferentGroupIfNecessary(group5);*/
		
		
		group1Floor = Math.abs(Globals.randomGenerator2.nextInt(13));
		group2Floor = Math.abs(Globals.randomGenerator2.nextInt(13));
		group3Floor = Math.abs(Globals.randomGenerator2.nextInt(13));
		group4Floor = Math.abs(Globals.randomGenerator2.nextInt(13));
		group5Floor = Math.abs(Globals.randomGenerator2.nextInt(13));
		
		group1Ceiling = Math.abs(Globals.randomGenerator2.nextInt(13));
		group2Ceiling = Math.abs(Globals.randomGenerator2.nextInt(13));
		group3Ceiling = Math.abs(Globals.randomGenerator2.nextInt(13));
		group4Ceiling = Math.abs(Globals.randomGenerator2.nextInt(13));
		group5Ceiling = Math.abs(Globals.randomGenerator2.nextInt(13));
		
		
		/*group2Floor = findDifferentGroupIfNecessaryFloor(group2Floor);
		group3Floor = findDifferentGroupIfNecessaryFloor(group3Floor);
		group4Floor = findDifferentGroupIfNecessaryFloor(group4Floor);
		group5Floor = findDifferentGroupIfNecessaryFloor(group5Floor);*/
		
		/*group2Ceiling = findDifferentGroupIfNecessaryCeiling(group4Floor);
		group3Ceiling = findDifferentGroupIfNecessaryCeiling(group3);
		group4Ceiling = findDifferentGroupIfNecessaryCeiling(group4Ceiling);
		group5Ceiling = findDifferentGroupIfNecessaryCeiling(group5Ceiling);*/
		
		
		group1WallsBoundLimit = setBoundSizeAccordingToGroupValueWalls(group1);
		group1WallDecorationsBoundLimit = setBoundSizeAccordingToGroupValueWallDecorations(group1);
		
		group2WallsBoundLimit = setBoundSizeAccordingToGroupValueWalls(group2);
		group2WallDecorationsBoundLimit = setBoundSizeAccordingToGroupValueWallDecorations(group2);
		
		group3WallsBoundLimit = setBoundSizeAccordingToGroupValueWalls(group3);
		group3WallDecorationsBoundLimit = setBoundSizeAccordingToGroupValueWallDecorations(group3);
		
		group4WallsBoundLimit = setBoundSizeAccordingToGroupValueWalls(group4);
		group4WallDecorationsBoundLimit = setBoundSizeAccordingToGroupValueWallDecorations(group4);
		
		group5WallsBoundLimit = setBoundSizeAccordingToGroupValueWalls(group5);  //Hallways possuem Global Decorations [nao possuem Decoration propria]
		
		
		System.out.println("Group1: "+ group1 + "  Group2: " + group2 + "  Group3 :" + group3 + "  Group4: " + group4 + "  Group5: " + group5);
	}
	
	public static Music getRandomDungeonMusic(){
		if(Globals.randomGenerator2.nextBoolean()){
			return Globals.assetManager.get("RevolutionDancersAssets/Audio/Music/Dungeon/0.mp3", Music.class);
		}
		return Globals.assetManager.get("RevolutionDancersAssets/Audio/Music/Dungeon/1.mp3", Music.class);
	}
	
	public static Music getRandomBattleMusic(){
		return Globals.assetManager.get("RevolutionDancersAssets/Audio/Music/Battle/"+Math.abs(Globals.randomGenerator2.nextInt(11))+".mp3", Music.class);
	}
	
	//These terribly named methods get values be setted into their respective badly named variables that tell us our sortition Bound limit according to the group value;
	private static int setBoundSizeAccordingToGroupValueWalls(int myGroupValue){
		switch(myGroupValue){
		case 0: return 11 -1;
		case 1: return 6 -1;
		case 2: return 5 -1;
		case 3: return 5 -1;
		case 4: return 4 -1;
		case 5: return 4 -1;
		case 6: return 6 -1;
		case 7: return 4 -1;
		case 8: return 8 -1;
		case 9: return 7 -1;
		}
		return 0;
	}

	private static int setBoundSizeAccordingToGroupValueWallDecorations(int myGroupValue){
		switch(myGroupValue){
		case 0: return 5 -1;
		case 1: return 4 -1;
		case 2: return 2 -1;
		case 3: return -1;
		case 4: return -1;
		case 5: return 5 -1;
		case 6: return -1;
		case 7: return 2 -1;
		case 8: return -1;  //There are no decorations for this group;
		case 9: return 6 -1;
		}
		return 0;
	}
	
	public static Texture getWallTexture(int myGroup) {
		//Low chance of this Wall being a decoration
		if((Globals.randomGenerator.nextBoolean() && Globals.randomGenerator.nextBoolean() &&  Globals.randomGenerator2.nextBoolean() && Globals.randomGenerator2.nextBoolean() && Globals.randomGenerator3.nextBoolean() && Globals.randomGenerator3.nextBoolean())){
			return getWallDecorationTexture(myGroup);
		}
		switch (myGroup) {
		case 1: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group1+"/"+Math.abs(Globals.getRandomGenerator().nextInt(group1WallsBoundLimit))+".png", Texture.class);
		case 2: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group2+"/"+Math.abs(Globals.getRandomGenerator().nextInt(group2WallsBoundLimit))+".png", Texture.class);
		case 3: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group3+"/"+Math.abs(Globals.getRandomGenerator().nextInt(group3WallsBoundLimit))+".png", Texture.class);
		case 4: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group4+"/"+Math.abs(Globals.getRandomGenerator().nextInt(group4WallsBoundLimit))+".png", Texture.class);
		case 5: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group5+"/"+Math.abs(Globals.getRandomGenerator().nextInt(group5WallsBoundLimit))+".png", Texture.class);
		}
		return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/9.jpg", Texture.class);  //Dopefish lives!!!
	}
	
	//Used by getWallTexture
	private static Texture getWallDecorationTexture(int myGroup) {
		switch (myGroup) { 
		//Revisar!! As vezes da falso positivo!!! 
		case 1: if(group1WallDecorationsBoundLimit == -1) return getGlobalDecorationTexture(); //if(group1WallDecorationsBoundLimit == 3 || group1WallDecorationsBoundLimit == 4 || group1WallDecorationsBoundLimit == 6 || group1WallDecorationsBoundLimit == 8) return getGlobalDecorationTexture();
		case 2: if(group2WallDecorationsBoundLimit == -1) return getGlobalDecorationTexture(); //if(group2WallDecorationsBoundLimit == 3 || group2WallDecorationsBoundLimit == 4 || group2WallDecorationsBoundLimit == 6 || group2WallDecorationsBoundLimit == 8) return getGlobalDecorationTexture();
		case 3: if(group3WallDecorationsBoundLimit == -1) return getGlobalDecorationTexture(); //if(group3WallDecorationsBoundLimit == 3 || group3WallDecorationsBoundLimit == 4 || group3WallDecorationsBoundLimit == 6 || group3WallDecorationsBoundLimit == 8) return getGlobalDecorationTexture();
		case 4: if(group4WallDecorationsBoundLimit == -1) return getGlobalDecorationTexture(); //if(group4WallDecorationsBoundLimit == 3 || group4WallDecorationsBoundLimit == 4 || group4WallDecorationsBoundLimit == 6 || group4WallDecorationsBoundLimit == 8) return getGlobalDecorationTexture();
		}
		switch (myGroup) { 
		case 1: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group1+"/Decorations/"+Math.abs(Globals.getRandomGenerator().nextInt(group1WallDecorationsBoundLimit))+".png", Texture.class);
		case 2: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group2+"/Decorations/"+Math.abs(Globals.getRandomGenerator().nextInt(group2WallDecorationsBoundLimit))+".png", Texture.class);
		case 3: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group3+"/Decorations/"+Math.abs(Globals.getRandomGenerator().nextInt(group3WallDecorationsBoundLimit))+".png", Texture.class);
		case 4: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/"+group4+"/Decorations/"+Math.abs(Globals.getRandomGenerator().nextInt(group4WallDecorationsBoundLimit))+".png", Texture.class);
		case 5: return getGlobalDecorationTexture();  //Hallways possuem Global Decorations [nao possuem Decoration propria]
		}
		return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/9.jpg", Texture.class);  //Dopefish lives!!!
	}
	
	public static Texture getFloorTexture(int myGroup) {
		switch (myGroup) {
		case 1: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Floor/"+group1Floor+".png", Texture.class);
		case 2: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Floor/"+group2Floor+".png", Texture.class);  
		case 3: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Floor/"+group3Floor+".png", Texture.class);  
		case 4: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Floor/"+group4Floor+".png", Texture.class);  
		case 5: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Floor/"+group5Floor+".png", Texture.class);  
		}
		return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/9.jpg", Texture.class);  //Dopefish lives!!!
	}
	
	public static Texture getCeilingTexture(int myGroup) {
		switch (myGroup) {
		case 1: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Ceiling/"+group1Ceiling+".png", Texture.class);
		case 2: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Ceiling/"+group2Ceiling+".png", Texture.class);  
		case 3: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Ceiling/"+group3Ceiling+".png", Texture.class);  
		case 4: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Ceiling/"+group4Ceiling+".png", Texture.class);  
		case 5: return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Ceiling/"+group5Ceiling+".png", Texture.class);  
		}
		return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/9.jpg", Texture.class);  //Dopefish lives!!!
	}
	
	public static Texture getGlobalDecorationTexture() {  //A priori o grupo eh sempre o grupo 5
		return Globals.assetManager.get("RevolutionDancersAssets/Graphics/2D/Walls/Decorations/"+Math.abs(Globals.randomGenerator2.nextInt(9))+".png", Texture.class); 
	}
	
	 //public static int  aux = 0;  //testes
	private static int findDifferentGroupIfNecessary(int myGroupValue){
		while(!checkIfDifferentGroup(myGroupValue)) {   //Ainda nao eh diferente
			 							//System.out.println("Knowledge! " + myGroup + "    Aux: "+aux);
				if(myGroupValue != 9) {
					myGroupValue++;			//Incrementa
				}	
				else myGroupValue = 0;		//Ou taca pra zero;
	    }
															//aux++;
		return myGroupValue;
	}
	
	private static int findDifferentGroupIfNecessaryFloor(int myGroupValue){
		
		while(!checkIfDifferentGroup(myGroupValue)) {   //Ainda nao eh diferente
			 
			if(myGroupValue != 13) {
				myGroupValue++;			//Incrementa
			}	
			else myGroupValue = 0;		//Ou taca pra zero;
		}
	
		return myGroupValue;
	}
	
	private static int findDifferentGroupIfNecessaryCeiling(int myGroupValue){
		
		while(!checkIfDifferentGroup(myGroupValue)) {   //Ainda nao eh diferente
			 
			if(myGroupValue != 13) {
				myGroupValue++;			//Incrementa
			}	
			else myGroupValue = 0;		//Ou taca pra zero;
		}
	
		return myGroupValue;
	}
	
	//Is my group different and unique then the others?
	private static boolean checkIfDifferentGroup(int myGroup){
		switch(myGroup){
		 case 2: return !(myGroup == group1 || myGroup == group3 || myGroup == group4 || myGroup == group4);
		 case 3: return !(myGroup == group1 || myGroup == group2 || myGroup == group4 || myGroup == group5);
		 case 4: return !(myGroup == group1 || myGroup == group2 || myGroup == group3 || myGroup == group5);
		 case 5: return !(myGroup == group1 || myGroup == group2 || myGroup == group3 || myGroup == group4);
		}
		return false;
	}
	
	private static boolean checkIfDifferentGroupFloor(int myGroup){
		switch(myGroup){
		 case 2: return !(myGroup == group1Floor || myGroup == group3Floor || myGroup == group4Floor || myGroup == group4Floor);
		 case 3: return !(myGroup == group1Floor || myGroup == group2Floor || myGroup == group4Floor || myGroup == group5Floor);
		 case 4: return !(myGroup == group1Floor || myGroup == group2Floor || myGroup == group3Floor || myGroup == group5Floor);
		 case 5: return !(myGroup == group1Floor || myGroup == group2Floor || myGroup == group3Floor || myGroup == group4Floor);
		}
		return false;
	}
	
	private static boolean checkIfDifferentGroupCeiling(int myGroup){
		switch(myGroup){
		 case 2: return !(myGroup == group1Ceiling || myGroup == group3Ceiling || myGroup == group4Ceiling || myGroup == group4Ceiling);
		 case 3: return !(myGroup == group1Ceiling || myGroup == group2Ceiling || myGroup == group4Ceiling || myGroup == group5Ceiling);
		 case 4: return !(myGroup == group1Ceiling || myGroup == group2Ceiling || myGroup == group3Ceiling || myGroup == group5Ceiling);
		 case 5: return !(myGroup == group1Ceiling || myGroup == group2Ceiling || myGroup == group3Ceiling || myGroup == group4Ceiling);
		}
		return false;
	}
	
	//Use whenever the program has reached a point where a certain asset is now certain to have global relevance
	//Understand that there is no unloading of globally relevant loaded assets
	public static void loadGlobalAsset(String filePath, AssetType type){
		switch(type){
		case Music:
			Globals.assetManager.load(filePath, Music.class);
			break;
		case Sound:
			Globals.assetManager.load(filePath, Sound.class);
			break;
		case Texture:
			Globals.assetManager.load(filePath, Texture.class);
			break;
		default:
			Globals.assetManager.finishLoading();
			break;
		}
	}
	
}
