package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo;

import java.util.ArrayList;
import java.util.Random;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Animal;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;
/**
 * Hamster A.I. that remembers the previous choice it has made and the dead ends
 * it has already met.
 * 
 */

public class Hamster extends Animal {

	private Random random;
	private ArrayList<Vector2D> alreadyDone;
	
	/**
	 * Constructs a hamster with a starting position.
	 * 
	 * @param position
	 *            Starting position of the hamster in the labyrinth
	 */

	public Hamster(Vector2D position) {
		super(position);
		this.random = new Random();
		this.alreadyDone=new ArrayList<Vector2D>();
	}

	/**
	 * Moves without retracing directly its steps and by avoiding the dead-ends
	 * it learns during its journey.
	 */

	@Override
	public Direction move(Direction[] choices) {
		
		//Liste des choix de direcions possibles sans impasses
		ArrayList<Direction> availableChoices = new ArrayList<Direction>();	
		
		for (Direction direction : choices) {
			if (!alreadyDone.contains(getPosition().addDirectionTo(direction))) {
				availableChoices.add(direction);
			}
		}
		if (availableChoices.size()==0) {
			return Direction.NONE;
		}
		//Les cases du couloir après une impasse deviennent impasses
		else if (availableChoices.size()==1) {
			alreadyDone.add(getPosition()); 
			return availableChoices.get(0);
		}
		else { 
			availableChoices.remove(getPreviousChoice().reverse());
			int a = random.nextInt(availableChoices.size());
			return availableChoices.get(a);
		}

	}
	

	@Override
	public Animal copy() {
		Hamster hamster = new Hamster(this.getPosition());
		return hamster;
	}
}
