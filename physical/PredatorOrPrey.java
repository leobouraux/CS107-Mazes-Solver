

package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

abstract public class PredatorOrPrey extends Animal {

	public PredatorOrPrey(Vector2D position) {
		super(position);
	}
	
	
	// LA MÉTHODE QUI ÉTAIT INITIALEMENT DANS LES CLASSES PREDATOR ET PREY.	
	/**
	 * Moves according to a <i>random walk</i>, used while not hunting in a
	 * {@code MazeSimulation}.
	 * 
	 */
	
	@Override
	public final Direction move(Direction[] choices) {
		return this.standardMove(choices);
	}
	
	abstract public Direction move(Direction[] choices, Daedalus daedalus);
}
