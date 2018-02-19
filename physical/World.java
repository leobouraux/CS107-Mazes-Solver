package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical;

import java.util.ArrayList;
import java.util.List;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * World that is represented by a labyrinth of tiles in which an {@code Animal}
 * can move.
 * 
 */

public abstract class World {

	/* tiles constants */
	public static final int FREE = 0;
	public static final int WALL = 1;
	public static final int START = 2;
	public static final int EXIT = 3;
	public static final int NOTHING = -1;

	private int[][]labyrinth;
	/**
	 * Constructs a new world with a labyrinth. The labyrinth must be rectangle.
	 * 
	 * @param labyrinth
	 *            Structure of the labyrinth, an NxM array of tiles
	 */


	public World(int[][] labyrinth) {
		this.labyrinth = new int[labyrinth.length][labyrinth[0].length];

		for (int i = 0; i < labyrinth.length; i++) {
			for (int j = 0; j < labyrinth[0].length; j++) {
				this.labyrinth[i][j]=labyrinth[i][j];
			}	
		}
	}

	/**
	 * Determines whether the labyrinth has been solved by every animal.
	 * 
	 * @return <b>true</b> if no more moves can be made, <b>false</b> otherwise
	 */

	abstract public boolean isSolved();

	/**
	 * Resets the world as when it was instantiated.
	 */

	abstract public void reset();

	/**
	 * Returns a copy of the list of all current animals in the world.
	 * 
	 * @return A list of all animals in the world
	 */

	abstract public List<Animal> getAnimals();

	/**
	 * Checks in a safe way the tile number at position (x, y) in the labyrinth.
	 * 
	 * @param x
	 *            Horizontal coordinate
	 * @param y
	 *            Vertical coordinate
	 * @return The tile number at position (x, y), or the NONE tile if x or y is
	 *         incorrect.
	 */

	public final int getTile(int x, int y) {
		if (x<0 || x>=labyrinth[0].length || y<0 || y>=labyrinth.length) {
			return NOTHING;
		} else {
			return labyrinth[y][x];
		}

	}

	/**
	 * Determines if coordinates are free to walk on.
	 * 
	 * @param x
	 *            Horizontal coordinate
	 * @param y
	 *            Vertical coordinate
	 * @return <b>true</b> if an animal can walk on tile, <b>false</b> otherwise
	 */

	public final boolean isFree(int x, int y) {
		if (getTile(x,y)==NOTHING || getTile(x,y)==WALL) {
			return false;
		} else { 
			return true;
		}
	}

	/**
	 * Computes and returns the available choices for a position in the
	 * labyrinth. The result will be typically used by {@code Animal} in
	 * {@link ch.epfl.maze.physical.Animal#move(Direction[]) move(Direction[])}
	 * 
	 * @param position
	 *            A position in the maze
	 * @return An array of all available choices at a position
	 */

	public final Direction[] getChoices(Vector2D position) {
		ArrayList<Direction> possibleDirections=new ArrayList<Direction>();
		int positionX=position.getX();
		int positionY=position.getY();

		if(isFree(positionX,positionY-1)){
			possibleDirections.add(Direction.UP);
		}
		if(isFree(positionX,positionY+1)){
			possibleDirections.add(Direction.DOWN);
		}
		if(isFree(positionX-1,positionY)){
			possibleDirections.add(Direction.LEFT);
		}
		if(isFree(positionX+1,positionY)){
			possibleDirections.add(Direction.RIGHT);
		}
		if(possibleDirections.isEmpty()){
			possibleDirections.add(Direction.NONE);
		}
		Direction[] selectedDirections = possibleDirections.toArray(new Direction[possibleDirections.size()]);
		return selectedDirections;
	}

	/**
	 * Returns horizontal length of labyrinth.
	 * 
	 * @return The horizontal length of the labyrinth
	 */

	public final int getWidth() {
		return labyrinth[0].length;
	}

	/**
	 * Returns vertical length of labyrinth.
	 * 
	 * @return The vertical length of the labyrinth
	 */

	public final int getHeight() {
		return labyrinth.length;
	}

	/**
	 * Returns the entrance of the labyrinth at which animals should begin when
	 * added.
	 * 
	 * @return Start position of the labyrinth, null if none.
	 */

	public final Vector2D getStart() {
		for (int i = 0; i < labyrinth.length; i++) {
			for (int j = 0; j < labyrinth[i].length; j++) {
				if(labyrinth[i][j]==START) {
					return new Vector2D (j,i);
				}
			}
		}
		return null;
	}

	/**
	 * Returns the exit of the labyrinth at which animals should be removed.
	 * 
	 * @return Exit position of the labyrinth, null if none.
	 */

	public final Vector2D getExit() {
		for (int i = 0; i < labyrinth.length; i++) {
			for (int j = 0; j < labyrinth[i].length; j++) {
				if(labyrinth[i][j]==EXIT) {
					return new Vector2D (j,i);
				}
			}
		}
		return null;
	}
}
