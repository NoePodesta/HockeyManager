package model;

import enums.Privilege;

import javax.persistence.*;

@Entity
@Table(name = "Captain")
public class Captain extends User {
	
	@Enumerated
	private static final Privilege PRIVILEGE = Privilege.CAPTAIN;
	
	private Team team;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
