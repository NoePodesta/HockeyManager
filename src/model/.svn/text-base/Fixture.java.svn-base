package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
