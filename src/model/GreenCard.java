package model;

import javax.persistence.*;




@Entity
@Table(name = "GreenCard")
public class GreenCard {
	

	private int idGreenCard;
	private Match match;
	private Player player;
	private Team team;
	
	
	@Id
	@GeneratedValue
	@Column(name="idGreenCard")
	public int getIdGreenCard() {
		return idGreenCard;
	}

	public void setIdGreenCard(int idGreenCard) {
		this.idGreenCard = idGreenCard;
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