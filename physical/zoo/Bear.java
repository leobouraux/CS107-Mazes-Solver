package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo;

import java.util.Random;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Animal;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Bear A.I. that implements the Pledge Algorithm.
 * 
 */

public class Bear extends Animal {

	private int counter;
	private Direction favoriteDirection;
	private Random random;
	private boolean wall;
	private boolean choiceDirection;
	/**
	 * Constructs a bear with a starting position.
	 * 
	 * @param position
	 *            Starting position of the bear in the labyrinth
	 */

	public Bear(Vector2D position) {
		super(position);
		this.counter=0;
		this.favoriteDirection = Direction.NONE;
		this.random= new Random();
		this.wall = false; 
		this.choiceDirection = true;

		}

	/**
	 * Moves according to the <i>Pledge Algorithm</i> : the bear tries to move
	 * towards a favorite direction until it hits a wall. In this case, it will
	 * turn right, put its paw on the left wall, count the number of times it
	 * turns right, and subtract to this the number of times it turns left. It
	 * will repeat the procedure when the counter comes to zero, or until it
	 * leaves the maze.
	 */
	
	// choisit la direction favorite
	private void choseDirection(Direction [] choices) {
		if (choiceDirection) {
			int a = random.nextInt(choices.length);
			favoriteDirection = choices[a];
			choiceDirection=false;
		}
	}
	
	@Override
	public Direction move(Direction[] choices) {
	
		// la direction favorite est initialisée au début du labyrinthe puis ne change plus
		if(Direction.NONE.equals(this.favoriteDirection)){
			choseDirection(choices);
		}
		
		if(counter==0){
			wall=false;
		}
		
		if (!wall) {
			for (Direction direction : choices) {
				if (direction.equals(favoriteDirection) && counter==0) {
					return direction;
				}
			}
			wall=true;

			for (Direction direction : choices) {
				// si possibilité de tourner à doite
				if (direction.equals(getPreviousChoice().unRelativeDirection(Direction.RIGHT))) {
					counter=1;
					return direction;
				}
			}
			// sinon fait demi-tour
			counter=2;
			return getPreviousChoice().unRelativeDirection(Direction.DOWN);
			
		} else {
			wall = true;

			for (Direction direction : choices) {
				if (getPreviousChoice().relativeDirection(direction)==Direction.LEFT) {
					counter-=1;
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
					counter+=1;
					return direction;
				}
			}
			for (Direction direction : choices) {
				if (getPreviousChoice().relativeDirection(direction)==Direction.DOWN) {
					counter+=2;
					return direction;
				}
			}
		}
		
		return Direction.NONE;

	}

	@Override
	public Animal copy() {
		Bear bear = new Bear(this.getPosition());
		return bear;
	}
}
