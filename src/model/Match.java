package model;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Match")
public class Match implements Comparable {
	
	private int id_Match;
	private Fixture Fixture;
	private Team local;
	private Team guest;
	private Date date;
	private List<Goal> highScoring; //el que hizo gol
	private List<Card> playerGCard; // Todas las Tarjetas
	private int resultLocal;
	private int resultGuest;
	private Integer fecha;
	
	public Match(){
		this.highScoring = new ArrayList<Goal>();
		this.playerGCard = new ArrayList<Card>();
		this.resultGuest=-1;
		this.resultLocal=-1;
	}
	
	@Id
	@GeneratedValue
	@Column(name="Id_Match")
	public int getId_Match() {
		return id_Match;
	}
	public void setId_Match(int idMatch) {
		id_Match = idMatch;
	}
	
	@OneToOne
	public Team getLocal() {
		return local;
	}
	public void setLocal(Team local) {
		this.local = local;
	}
	
	@OneToOne
	public Fixture getFixture() {
		return Fixture;
	}
	public void setFixture(Fixture fixture) {
		Fixture = fixture;
	}
	
	@OneToOne
	public Team getGuest() {
		return guest;
	}
	public void setGuest(Team guest) {
		this.guest = guest;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

	
	@OneToMany(cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Column(name = "PlayerHighScoring")	
	public List<Goal> getHighScoring() {
		return highScoring;
	}

	public void setHighScoring(List<Goal> highScoring) {
		this.highScoring = highScoring;
	}

	@OneToMany(cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Column(name = "PlayerCard")
	public List<Card> getPlayerGCard() {
		return playerGCard;
	}

	public void setPlayerGCard(List<Card> playerGCard) {
		this.playerGCard = playerGCard;
	}

	
	@Column(name ="Fecha_Nro")
	public int getFecha() {
		return fecha;
	}
	public void setFecha(Integer fecha) {
		this.fecha = fecha;
	}

	
	@Column(name ="LocalResult")
	public int getResultLocal() {
		return resultLocal;
	}
	public void setResultLocal(int resultLocal) {
		this.resultLocal = resultLocal;
	}
	@Column(name ="GuestResult")
	public int getResultGuest() {
		return resultGuest;
	}
	public void setResultGuest(int resultGuest) {
		this.resultGuest = resultGuest;
	}
	
	@Override
	public int compareTo(Object o) {
		Match match = (Match) o;
		return fecha.compareTo(match.getFecha());
		
	}


}
