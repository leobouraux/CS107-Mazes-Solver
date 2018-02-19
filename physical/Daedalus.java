package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;

/**
 * Daedalus in which predators hunt preys. Once a prey has been caught by a
 * predator, it will be removed from the daedalus.
 * 
 */

public final class Daedalus extends World {

	private List<Predator> predators;
	private List<Prey> preys;
	private List<Predator> copiePredators;
	private List<Prey> copiePreys;
	
	/**
	 * Constructs a Daedalus with a labyrinth structure
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */

	public Daedalus(int[][] labyrinth) {
		super(labyrinth);
		this.predators = new  ArrayList <Predator>();
		this.copiePredators = new  ArrayList <Predator>();
		this.preys = new ArrayList<Prey>();
		this.copiePreys = new  ArrayList <Prey>();
	}

	@Override
	public boolean isSolved() {
		return preys.isEmpty();
	}
	
	/**
	 * Adds a predator to the daedalus.
	 * 
	 * @param p
	 *            The predator to add
	 */

	public void addPredator(Predator p) {
		predators.add(p);
		//p.copy() car si on modifie p, p.copy restera l'initial
		copiePredators.add((Predator) p.copy());
	}

	/**
	 * Adds a prey to the daedalus.
	 * 
	 * @param p
	 *            The prey to add
	 */

	public void addPrey(Prey p) {
		preys.add(p);
		copiePreys.add((Prey) p.copy());
	}

	/**
	 * Removes a predator from the daedalus.
	 * 
	 * @param p
	 *            The predator to remove
	 */

	public void removePredator(Predator p) {
		predators.remove(p);
	}

	/**
	 * Removes a prey from the daedalus.
	 * 
	 * @param p
	 *            The prey to remove
	 */

	public void removePrey(Prey p) {
		preys.remove(p);
	}

	@Override
	public List<Animal> getAnimals() {
		ArrayList<Animal> animals = new ArrayList <Animal>();
		for (Predator predator : predators) {
			animals.add(predator);
		} 
		for (Prey prey : preys) {
			animals.add(prey);
		}
		return animals;
	}

	/**
	 * Returns a copy of the list of all current predators in the daedalus.
	 * 
	 * @return A list of all predators in the daedalus
	 */

	public List<Predator> getPredators() {
			return new ArrayList<Predator>(predators);
	}

	/**
	 * Returns a copy of the list of all current preys in the daedalus.
	 * 
	 * @return A list of all preys in the daedalus
	 */

	public List<Prey> getPreys() {
		return new ArrayList<Prey>(preys);
	}

	/**
	 * Determines if the daedalus contains a predator.
	 * 
	 * @param p
	 *            The predator in question
	 * @return <b>true</b> if the predator belongs to the world, <b>false</b>
	 *         otherwise.
	 */

	public boolean hasPredator(Predator p) {
		
		return predators.contains(p);
	}

	/**
	 * Determines if the daedalus contains a prey.
	 * 
	 * @param p
	 *            The prey in question
	 * @return <b>true</b> if the prey belongs to the world, <b>false</b>
	 *         otherwise.
	 */

	public boolean hasPrey(Prey p) {

		return preys.contains(p);
	}

	@Override
	public void reset() {
		predators.clear();
		for (Predator predator : copiePredators) {
			Predator p = (Predator) predator.copy();
			if(p == null){
				p = predator;
			}
			predators.add(p);
		}
		preys.clear();
		for (Prey prey : copiePreys) {
			Prey p = (Prey) prey.copy();
			if(p == null){
				p = prey;
			}
			preys.add(p);
		}
	}
}
