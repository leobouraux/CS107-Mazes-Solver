package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Daedalus;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Predator;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Orange ghost from the Pac-Man game, alternates between direct chase if far
 * from its target and SCATTER if close.
 * 
 */

public class Clyde extends Predator {

	private Vector2D home;
	/**
	 * Constructs a Clyde with a starting position.
	 * 
	 * @param position
	 *            Starting position of Clyde in the labyrinth
	 */

	public Clyde(Vector2D position) {
		super(position);
		this.home=position;
	}



	@Override
	public Predator copy() {
		Clyde clyde = new Clyde (getPosition());
		return clyde;
	}



	@Override
	public Vector2D getTargetPosition(Daedalus daedalus) {
	
		if (getPosition().sub(getTarget().getPosition()).dist()>4){
			return getTarget().getPosition();
		}
		else {
			return home;
		}
	}
}
