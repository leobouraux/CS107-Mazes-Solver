package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical;


import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Predator that kills a prey when they meet with each other in the labyrinth.
 * 
 */

abstract public class Predator extends PredatorOrPrey {

	/* constants relative to the Pac-Man game */
	public static final int SCATTER_DURATION = 14;
	public static final int CHASE_DURATION = 40;

	private boolean scatter;
	private Prey target;
	private Vector2D home;


	/**
	 * Constructs a predator with a specified position.
	 * 
	 * @param position
	 *            Position of the predator in the labyrinth
	 */

	public Predator(Vector2D position) {
		super(position);
		this.scatter=false;
		this.target = null;
		this.home = position;
	}



	protected Prey getTarget(){
		return this.target;
	}



	public boolean scatterMode () {
		if (getStepCounter()%54 <= CHASE_DURATION) {
			scatter = false;
		}
		else if (getStepCounter()%54 > CHASE_DURATION) {
			scatter = true;
		}
		return scatter;
	}

	/**
	 * Retrieves the next direction of the animal, by selecting one choice among
	 * the ones available from its position.
	 * <p>
	 * In this variation, the animal knows the world entirely. It can therefore
	 * use the position of other animals in the daedalus to hunt more
	 * effectively.
	 * 
	 * @param choices
	 *            The choices left to the animal at its current position (see
	 *            {@link ch.epfl.maze.physical.World#getChoices(Vector2D)
	 *            World.getChoices(Vector2D)})
	 * @param daedalus
	 *            The world in which the animal moves
	 * @return The next direction of the animal, chosen in {@code choices}
	 */

	public Direction move(Direction[] choices, Daedalus daedalus) {

		// si le fantôme n'a plus sa proie en cours (si elle s'est faite attraper)
		if(this.target == null || !daedalus.hasPrey(this.target)){
			// si il reste encore des proies, alors la cible est la première de la liste
			if(daedalus.getPreys().size()>0){
				this.target = daedalus.getPreys().get(0);
			}

			//sinon, le fantôme reprend un comportement aléatoire
			else{
				this.target = null;
				return this.move(choices);
			}
		}
		if (choices.length==0) {
			return Direction.NONE;
		}
		if (choices.length==1) {
			return choices[0];
		}
		else if (choices.length==2) {
			if (getPreviousChoice().isOpposite(choices[0])) {
				return choices[1];
			}
			else { return choices[0]; }
		} 
		else {
			// si on est en ScatterMode --> cible = maison
			// sinon --> cible = cible normale
			Vector2D targetPosition = this.scatterMode()?this.home:this.getTargetPosition(daedalus);


			// La distance minimale à la cible est initialisée à -1
			// en attendant de tomber sur une direction valable
			double minDist = -1;
			Direction minDir = Direction.NONE;
			for (Direction direction : choices) {
				if (!direction.isOpposite(getPreviousChoice())){
					double distToTarget = (getPosition().addDirectionTo(direction).sub(targetPosition)).dist();
					if(distToTarget < minDist || minDist == -1){
						minDist = distToTarget;
						minDir = direction;
					}
				}
			}
			return minDir;
		}

	}

	//connaitre les coordonnées de la cible en fonction du daedalus (varie pour chaque fantôme)
	abstract public Vector2D getTargetPosition (Daedalus daedalus);

	abstract public Animal copy();

}

