package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Daedalus;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Prey;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Pac-Man character, from the famous game of the same name.
 * 
 */

public class PacMan extends Prey {

	public PacMan(Vector2D position) {
		super(position);
	}

	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		return this.move(choices);
	}

	@Override
	public Prey copy() {
		PacMan pacman = new PacMan(this.getPosition());
		return pacman;
	}
}
