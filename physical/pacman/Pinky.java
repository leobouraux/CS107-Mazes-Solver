package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Daedalus;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Predator;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Pink ghost from the Pac-Man game, targets 4 squares in front of its target.
 * 
 */

public class Pinky extends Predator {
	private final static int STEP_AHEAD = 4;

	/**
	 * Constructs a Pinky with a starting position.
	 * 
	 * @param position
	 *            Starting position of Pinky in the labyrinth
	 */

	public Pinky(Vector2D position) {
		super(position);
		
	}


	@Override
	public Predator copy() {
		Pinky pinky = new Pinky (getPosition());
		return pinky;
	}


	@Override
	//getTarget().getPosition().addDirectionTo(getTarget().getPreviousChoice())
	//ajoute à la position de la cible, la dernière direction qu'il a choisi 
	
	public Vector2D getTargetPosition(Daedalus daedalus) {
		Vector2D pos = getTarget().getPosition();
		Direction dir = getTarget().getPreviousChoice();
		int n = STEP_AHEAD;
		while(n-->0){
			pos = pos.addDirectionTo(dir);
		}
		return pos;
	}
}
