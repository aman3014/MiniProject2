/*
 *	Author:      Aman Bansal
 *	Date:        17 nov. 2018
 */

package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {

	private Actor actor1;
	private Actor actor2;
	private TextGraphics boum;
	
	private Window window;
	private FileSystem fileSystem;
	
	public boolean begin(Window window, FileSystem fileSystem) {
		
		//Initializing the two actors in the demo game
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(0.2f), null, Color.RED, 0.005f));
		actor2 = new MovingRock(new Vector(0.3f, 0.3f), "Hello, I'm a moving rock !");
		boum = new TextGraphics("BOUM !!!", 0.05f, Color.RED);
		
		//Initializing the game window and the file system used for it to function
		this.window = window;
		this.fileSystem = fileSystem;		
		
		//Allow game to begin
		return true;
	}


	public void end() {
		
	}

	public String getTitle() {
		return "Demo1";
	}

	private static int i = 0;

	public void update(float deltaTime) {
		
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		
		//Update actor2 if the downArrow key is pressed
		if (downArrow.isDown()) {
			actor2.update(deltaTime);
		}
		
		//Display the boum message if actor2 is in range of actor1
		if(actor2.getPosition().sub(actor1.getPosition()).getLength() < 0.2f) {
			if(i == 0) {
				boum.setAnchor(actor2.getPosition());
			}
			boum.draw(window);
			++i;
		}
		
		//Drawing actors onto the game window
		actor1.draw(window);
		actor2.draw(window);
	}

	public int getFrameRate() {
		return 24;
	}
	
}
