package model;

import enums.Privilege;

import javax.persistence.*;

@Entity
@DiscriminatorValue("UserAdmin")
public class UserAdmin extends User {

	private Tournament tournament;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}


    public Privilege getPrivilege() {
        return Privilege.USERADMIN;
    }
}