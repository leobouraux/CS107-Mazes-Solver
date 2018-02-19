package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Maze in which an animal starts from a starting point and must find the exit.
 * Every animal added will have its position set to the starting point. The
 * animal is removed from the maze when it finds the exit.
 * 
 */

public final class Maze extends World {
	
	private List<Animal> animals;
	private List<Animal> copie;
	
	/**
	 * Constructs a Maze with a labyrinth structure.
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */

	public Maze(int[][] labyrinth) {
		super(labyrinth);
		animals = new ArrayList<Animal>();
		copie = new ArrayList<Animal>();
		
	}

	@Override
	public boolean isSolved() {
			return animals.isEmpty();	
	}

	@Override
	public List<Animal> getAnimals() {
		return new ArrayList<Animal>(animals);
	}

	/**
	 * Determines if the maze contains an animal.
	 * 
	 * @param a
	 *            The animal in question
	 * @return <b>true</b> if the animal belongs to the world, <b>false</b>
	 *         otherwise.
	 */

	public boolean hasAnimal(Animal a) {
		return animals.contains(a);
	}

	/**
	 * Adds an animal to the maze.
	 * 
	 * @param a
	 *            The animal to add
	 */

	public void addAnimal(Animal a) {
		copie.add(a);
		animals.add(a.copy());
		a.setPosition(this.getStart());
	}

	/**
	 * Removes an animal from the maze.
	 * 
	 * @param a
	 *            The animal to remove
	 */

	public void removeAnimal(Animal a) {
		animals.remove(a);
	}

	@Override
	public void reset() {
		animals.clear();
		for (Animal animal : copie) {
			Animal a = animal.copy();
			if(a == null){
				a = animal;
			}
			animals.add(a);
			Vector2D v = this.getStart();
			a.setPosition(v);
		}
	}	
}
