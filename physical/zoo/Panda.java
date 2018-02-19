package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo;

import java.util.ArrayList;
import java.util.Random;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Animal;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Panda A.I. that implements Trémeaux's Algorithm.
 * 
 */
public class Panda extends Animal {

	private Random random;
	private ArrayList<Vector2D> color1;
	private ArrayList<Vector2D> color2;
	/**
	 * Constructs a panda with a starting position.
	 * 
	 * @param position
	 *            Starting position of the panda in the labyrinth
	 */

	public Panda(Vector2D position) {
		super(position);
		this.color1 = new ArrayList<Vector2D>();
		this.color2 = new ArrayList<Vector2D>();
		this.random= new Random();
	}

	/**
	 * Moves according to <i>Trémeaux's Algorithm</i>: when the panda
	 * moves, it will mark the ground at most two times (with two different
	 * colors). It will prefer taking the least marked paths. Special cases
	 * have to be handled, especially when the panda is at an intersection.
	 */

	@Override
	public Direction move(Direction[] choices) {
		ArrayList<Direction> choicesEmptyCase = new ArrayList<Direction>();
		ArrayList<Direction> choicesColor1Case = new ArrayList<Direction>();
		ArrayList<Direction> choicesColor2Case = new ArrayList<Direction>();

		// on trie les choix présents
		for (Direction direction : choices) {
			Vector2D nextPossibleChoice = getPosition().addDirectionTo(direction);
			if (color2.contains(nextPossibleChoice)) {
				choicesColor2Case.add(direction);
			}
			else if (color1.contains(nextPossibleChoice)) {
				choicesColor1Case.add(direction);
			}
			else {
				choicesEmptyCase.add(direction);
			}
		}

		// on regarde le marquage de la case courante
		int currentColor = 0;
		if (color2.contains(getPosition())) {
			currentColor = 2;
		}
		else if (color1.contains(getPosition())) {
			currentColor = 1; 
		}


		// exceptions
		if (!(choices.length>2 && currentColor==1 && (choicesEmptyCase.size()+choicesColor1Case.size())>1)) {
			if (choicesEmptyCase.size()==0 && choicesColor1Case.size()==1) {
				color2.add(getPosition());
			}
			else{
				if(currentColor == 0) {
					color1.add(getPosition());
				}
				else if (currentColor == 1) {
					color2.add(getPosition());
				}
			}
		}



		// on choisit lequel des choix est le bon

		// Règle 1
		if (!choicesEmptyCase.isEmpty()) {
			int a = random.nextInt(choicesEmptyCase.size());
			return choicesEmptyCase.get(a);
		}

		// Règle 2
		else if (!choicesColor1Case.isEmpty()) {

			// exception n°2
			if(choicesColor2Case.isEmpty() && choices.length>2){
				return getPreviousChoice().reverse();
			}

			// cas normal
			if (choicesColor1Case.size()>1 && choicesColor1Case.contains(getPreviousChoice().reverse())) {
				int a = random.nextInt(choicesColor1Case.size()-1);
				if (getPreviousChoice().isOpposite(choicesColor1Case.get(a))) {

					return choicesColor1Case.get(choicesColor1Case.size()-1);
				} else {
					return choicesColor1Case.get(a);
				}
			} else {
				int a = random.nextInt(choicesColor1Case.size());
				return choicesColor1Case.get(a);
			}
		}

		// Règle 3
		else {
			// ne pas revenir en arriere sauf si on a pas le choix
			if (choicesColor2Case.size()>1  && choicesColor2Case.contains(getPreviousChoice().reverse())) {
				int a = random.nextInt(choicesColor2Case.size()-1);
				if (getPreviousChoice().isOpposite(choicesColor2Case.get(a))) {
					return choicesColor2Case.get(choicesColor2Case.size()-1);
				} else {
					return choicesColor2Case.get(a);
				}
			} else {
				int a = random.nextInt(choicesColor2Case.size());
				return choicesColor2Case.get(a);
			}
		}	
	}

	@Override
	public Animal copy() {
		Panda panda = new Panda(this.getPosition());
		return panda;
	}
}
