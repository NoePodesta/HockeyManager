package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enums.Privilege;

@Entity
@Table(name = "UserAdmin")
public class UserAdmin extends User{
	

	@Enumerated
	private static final Privilege PRIVILEGE = Privilege.USERADMIN;
	
	private Tournament tournament;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	
	
}