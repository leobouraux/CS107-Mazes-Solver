package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo;


import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Animal;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Mouse A.I. that remembers only the previous choice it has made.
 * 
 */

public class Mouse extends Animal {
	
	
	/**
	 * Constructs a mouse with a starting position.
	 * 
	 * @param position
	 *            Starting position of the mouse in the labyrinth
	 */

	public Mouse(Vector2D position) {
		super(position);
	}

	/**
	 * Moves according to an improved version of a <i>random walk</i> : the
	 * mouse does not directly retrace its steps.
	 */

	@Override
	public Direction move(Direction[] choices) {
		return this.standardMove(choices);
	}

	@Override
	public Animal copy() {
		Mouse mouse = new Mouse(this.getPosition());
		return mouse;
	}
}
