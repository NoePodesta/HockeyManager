package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Tournament")
public class Tournament implements Comparable {
	
	private int idTournament;
	private String name;
	private byte[] photo;
	private List<Team> teams;
	private Fixture fixture;
	private String description;
	
	
	public Tournament(){
		this.teams = new ArrayList<Team>();
	}

	@Id
	@GeneratedValue
	@Column(name="Id_Tournament")
	
	public int getIdTournament() {
		return idTournament;
	}
	public void setIdTournament(int idTournament) {
		this.idTournament = idTournament;
	}
	
	@Column(name ="Name", nullable=false, unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Lob
	@Column(name= "Tournament_Photo")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@Column(name = "Team")
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	@OneToOne(cascade = CascadeType.REMOVE)
	public Fixture getFixture() {
		return fixture;
	}
	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	@Column(name ="Descripcion")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Object o) {
		Tournament tournament = (Tournament) o;
		return name.compareTo(tournament.getName());
	}
	
}
