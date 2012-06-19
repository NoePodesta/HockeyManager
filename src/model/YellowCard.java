package model;

import javax.persistence.*;




@Entity
@Table(name = "YellowCard")
public class YellowCard {
	

	private int idYellowCard;
	private Match match;
	private Player player;
	private Team team;
	
	
	@Id
	@GeneratedValue
	@Column(name="idYellowCard")
	public int getIdYellowCard() {
		return idYellowCard;
	}
	
	public void setIdYellowCard(int idYellowCard) {
		this.idYellowCard = idYellowCard;
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