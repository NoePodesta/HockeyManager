package model;

import enums.Privilege;

import javax.persistence.*;

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