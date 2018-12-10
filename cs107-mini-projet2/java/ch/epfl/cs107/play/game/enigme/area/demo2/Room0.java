package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * Class Room0 represents the level selector room in the Demo2 game
 * extends Demo2Area
 * @author Julian Blackwell, Aman Bansal
 */
public class Room0 extends Demo2Area {
	
	@Override
	public String getTitle() {
		return "LevelSelector";
	}

	@Override
	public float getCameraScaleFactor() {
		return super.getCameraScaleFactor();
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		return super.begin(window, fileSystem);
	}
}