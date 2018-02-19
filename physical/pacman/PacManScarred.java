package MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.pacman;

import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Daedalus;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Predator;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.physical.Prey;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Direction;
import MiniProjets.Labyrinthe.src.ch.epfl.maze.util.Vector2D;

/**
 * Pac-Man character, from the famous game of the same name.
 * 
 */

public class PacManScarred extends Prey {

	public PacManScarred(Vector2D position) {
		super(position);
	}

	@Override
	public Direction move(Direction[] choices, Daedalus daedalus) {
		double minDist = -1;
		Predator minPred = null;
		
		//pour chaque prédateur du dédale
		for (Predator d : daedalus.getPredators()) {
			double distancePréd = this.getPosition().sub(d.getPosition()).dist();
			if (minDist ==-1 || distancePréd<minDist) {
				minDist = distancePréd;
				minPred = d;
			}
		}
		//si il n'y a pas de prédateur --> déplacement standard
		if (minPred==null) {
			return this.move(choices);
		}
		//sinon les Pacman s'éloignent du prédateur le plus proche
		else {
			minDist = -1;
			Direction minDir = Direction.NONE;
			for (Direction direction : choices) {
				double distToTarget = (getPosition().addDirectionTo(direction).sub(minPred.getPosition())).dist();
				if(distToTarget > minDist || minDist == -1){
					minDist = distToTarget;
					minDir = direction;
				}
			}
			
			if (minDir !=Direction.NONE) {
				return minDir;
			}
			else { return this.move(choices); }
			
		}
	}

	@Override
	public Prey copy() {
		PacManScarred pacman = new PacManScarred(this.getPosition());
		return pacman;
	}
}
