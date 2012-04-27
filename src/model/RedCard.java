package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "RedCard")
public class RedCard {
	

	private int idRedCard;
	private Match match;
	private Player player;
	

	private Team team;
	
	
	@Id
	@GeneratedValue
	@Column(name="idRedCard")
	public int getIdRedCard() {
		return idRedCard;
	}
	
	public void setIdRedCard(int idRedCard) {
		this.idRedCard = idRedCard;
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