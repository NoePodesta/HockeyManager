package model;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;




@Entity
@Table(name = "Goal")
public class Goal {
	

	private int idGoal;
	private Match match;
	private Player player;
	private Team team;
	
	
	@Id
	@GeneratedValue
	@Column(name="IdGoal")
	public int getIdGoal() {
		return idGoal;
	}

	public void setIdGoal(int idGoal) {
		this.idGoal = idGoal;
	}

	
	@OneToOne(cascade = CascadeType.REMOVE)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@OneToOne(cascade = CascadeType.REMOVE)
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@OneToOne(cascade = CascadeType.REMOVE)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	
}
