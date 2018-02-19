package MiniProjets.Labyrinthe.src.ch.epfl.maze.main;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.graphics.Display;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Daedalus;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Maze;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman.Blinky;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman.Clyde;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman.Inky;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman.PacMan;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman.Pinky;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo.Bear;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo.Hamster;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo.Monkey;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo.Mouse;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.zoo.Panda;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.simulation.DaedalusSimulation;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.simulation.MazeSimulation;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.simulation.Simulation;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.LabyrinthGenerator;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Mini-project main program that will run the simulations on a {@code Display}.
 * 
 */

public class Program {

	/**
	 * Runs one of the two available simulations
	 * 
	 * @see #getMazeSimulation()
	 * @see #getDaedalusSimulation()
	 */

	public static void main(String[] args) {
		Simulation simulation;

		simulation = getMazeSimulation();
		//simulation = getDaedalusSimulation();

		Display display = new Display(simulation);
		display.run();
	}

	/**
	 * Creates a {@code MazeSimulation} with every animal implementations.
	 * 
	 * @return A {@code MazeSimulation} to display
	 */

	public static Simulation getMazeSimulation() {
		int[][] labyrinth = LabyrinthGenerator.getMedium();
		Maze m = new Maze(labyrinth);
		Simulation simulation = new MazeSimulation(m);

		// adds a Mouse
		m.addAnimal(new Mouse(m.getStart()));

		// adds a Monkey
		m.addAnimal(new Monkey(m.getStart()));

		// adds a Hamster
		m.addAnimal(new Hamster(m.getStart()));

		// adds a Bear
		m.addAnimal(new Bear(m.getStart()));

		// adds a Panda
		m.addAnimal(new Panda(m.getStart()));

		return simulation;
	}

	/**
	 * Creates a {@code DaedalusSimulation} with every ghost implementation and
	 * 3 Pac-Mans.
	 * 
	 * @return A {@code DaedalusSimulation} to display
	 */

	public static Simulation getDaedalusSimulation() {
		int[][] labyrinth = LabyrinthGenerator.getPacMan();
		Daedalus d = new Daedalus(labyrinth);
		Simulation simulation = new DaedalusSimulation(d);

		// adds Pac-Mans
		d.addPrey(new PacMan(new Vector2D(9, 15)));
		d.addPrey(new PacMan(new Vector2D(10, 15)));
		d.addPrey(new PacMan(new Vector2D(8, 15)));

		// adds Blinky
		d.addPredator(new Blinky(new Vector2D(17, 1)));

		// adds Pinky
		d.addPredator(new Pinky(new Vector2D(1, 1)));

		// adds Inky
		d.addPredator(new Inky(new Vector2D(17, 17)));

		// adds Clyde
		d.addPredator(new Clyde(new Vector2D(1, 17)));

		return simulation;
	}
}
