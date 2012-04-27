package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enums.Privilege;

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
