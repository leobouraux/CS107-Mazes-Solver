package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Animal;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Monkey A.I. that puts its hand on the left wall and follows it.
 * 
 */

public class Monkey extends Animal {
	
	
	/**
	 * Constructs a monkey with a starting position.
	 * 
	 * @param position
	 *            Starting position of the monkey in the labyrinth
	 */

	public Monkey(Vector2D position) {
		super(position);
		setPreviousChoice(Direction.DOWN);
	}
	
	/**
	 * Moves according to the relative left wall that the monkey has to follow.
	 */

	@Override
	public Direction move(Direction[] choices) {
		
		for (Direction direction : choices) {
			Direction monkeyChoice = getPreviousChoice().relativeDirection(direction);
			if (monkeyChoice==Direction.LEFT) {
				return direction;
			}
		}
		
		for (Direction direction : choices) {
			if (getPreviousChoice().relativeDirection(direction)==Direction.UP) {
				return direction;
			}
		}
		for (Direction direction : choices) {
			if (getPreviousChoice().relativeDirection(direction)==Direction.RIGHT) {
				return direction;
			}
		}
		for (Direction direction : choices) {
			if (getPreviousChoice().relativeDirection(direction)==Direction.DOWN) {
				return direction;
			}
		}
		
		return Direction.NONE;
		
	}

	@Override
	public Animal copy() {
		Monkey monkey = new Monkey(this.getPosition());
		return monkey;
	}
}
