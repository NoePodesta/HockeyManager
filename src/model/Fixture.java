package model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Fixture")

public class Fixture {
	
	private int idFixture;
	private List<Match> matches;

	public Fixture(){
		this.matches = new ArrayList<Match>();
	}
	
	@Id
	@GeneratedValue
	@Column(name="Id_Fixture")
	public int getIdFixture() {
		return idFixture;
	}
	public void setIdFixture(int idFixture) {
		this.idFixture = idFixture;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Column(name = "Matches")
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
}
