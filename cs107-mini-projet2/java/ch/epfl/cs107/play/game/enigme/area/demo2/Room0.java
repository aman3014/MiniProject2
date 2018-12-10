/*
 *	Author:      Aman Bansal
 *	Date:        23 nov. 2018
 */

package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Room0 extends Demo2Area {
	
	@Override
	public String getTitle() {
		return "LevelSelector";
	}

	@Override
	public float getCameraScaleFactor() {
		return super.getCameraScaleFactor();
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		return super.begin(window, fileSystem);
	}
}