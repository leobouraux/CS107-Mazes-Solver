package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Daedalus;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Predator;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Blue ghost from the Pac-Man game, targets the result of two times the vector
 * from Blinky to its target.
 * 
 */

public class Inky extends Predator {

	/**
	 * Constructs a Inky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Inky in the labyrinth
	 */

	public Inky(Vector2D position) {
		super(position);
	}


	@Override
	public Predator copy() {
		Inky inky = new Inky (getPosition());
		return inky;
	}


	@Override
	public Vector2D getTargetPosition(Daedalus daedalus) {
		Predator blinky = this.getBlinky(daedalus);
		// si Blinky n'existe pas
		if(blinky == null){
			return getTarget().getPosition();
		}
		return getTarget().getPosition().mul(2).sub(blinky.getPosition());
	}


	private Predator getBlinky(Daedalus daedalus) {
		// on passe revue tous les prédateurs du daedalus, jusqu'à ce qu'on tombe sur Blinky
		for (Predator pred : daedalus.getPredators()) {
			if(pred instanceof Blinky){
				return pred;
			}
		}
		return null;
	}
}
