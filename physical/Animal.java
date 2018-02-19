package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical;

import java.util.Random;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Animal inside a {@code World} that can move depending on the available
 * choices it has at its position.
 * 
 */

abstract public class Animal {

	private Vector2D position;
	private Direction previousChoice;
	private int stepCounter;
	private Random random;

	/**
	 * Constructs an animal with a specified position.
	 * 
	 * @param position
	 *            Position of the animal in the labyrinth
	 */

	public Animal(Vector2D position) {
		this.position=position;
		this.previousChoice=Direction.NONE;
		this.stepCounter=0;
		this.random = new Random();
	}

	// compte les mouvements des animaux (utilisé que dans Pacman)
	protected int getStepCounter() {
		return stepCounter;
	}

	public Direction getPreviousChoice () {
		return previousChoice;
	}

	protected void setPreviousChoice(Direction direction) {
		previousChoice=direction;
	}
	/**
	 * Retrieves the next direction of the animal, by selecting one choice among
	 * the ones available from its position.
	 * 
	 * @param choices
	 *            The choices left to the animal at its current position (see
	 *            {@link ch.epfl.maze.physical.World#getChoices(Vector2D)
	 *            World.getChoices(Vector2D)})
	 * @return The next direction of the animal, chosen in {@code choices}
	 */

	abstract public Direction move(Direction[] choices);

	/**
	 * Updates the animal position with a direction.
	 * <p>
	 * <b>Note</b> : Do not call this function in {@code move(Direction[]
	 * choices)} !
	 * 
	 * @param dir
	 *            Direction that the animal has taken
	 */

	public final void update(Direction dir) {
		position=position.add(dir.toVector());
		previousChoice=dir;
		//ajoute un pas à chaque mouvement (non utilisé par les animaux)
		stepCounter++;
	}

	/**
	 * Sets new position for Animal.
	 * <p>
	 * <b>Note</b> : Do not call this function in {@code move(Direction[]
	 * choices)} !
	 * 
	 * @param position
	 */

	public final void setPosition(Vector2D position) {
		this.position=position;
	}

	/**
	 * Returns position vector of animal.
	 * 
	 * @return Current position of animal.
	 */

	public final Vector2D getPosition() {

		return this.position;
	}

	abstract public Animal copy();




	// Méthode de mouvement aléatoire, servant aux souris (et aux proies et prédateurs dans certains cas) de se déplacer aléatoirement 
	// évite la duplication de code

	protected Direction standardMove(Direction[] choices) {
		//arrival in the labyrinth
		if (getPreviousChoice()==Direction.NONE) {
			int a = random.nextInt(choices.length);
			return choices[a];
		}

		//impasse
		if (choices.length==1) {
			return choices[0];
		}

		//corridor
		if (choices.length==2) {
			if (getPreviousChoice().isOpposite(choices[0])) {
				return choices[1];
			}
			else { return choices[0]; }
		} 

		//intersection
		else {
			int a = random.nextInt(choices.length-1);
			if (getPreviousChoice().isOpposite(choices[a])) {
				return choices[choices.length-1];
			} else {
				return choices[a];
			}
		}
	}


}
