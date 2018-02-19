package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Daedalus;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Predator;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Red ghost from the Pac-Man game, chases directly its target.
 * 
 */

public class Blinky extends Predator {

	/**
	 * Constructs a Blinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Blinky in the labyrinth
	 */

	public Blinky(Vector2D position) {
		super(position);
	}



	@Override
	public Predator copy() {
		Blinky blinky = new Blinky (getPosition());
		return blinky;
	}



	@Override
	public Vector2D getTargetPosition(Daedalus daedalus) {
		return getTarget().getPosition();
	}
}
