package model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "Team")
public class Team implements Comparable{
	
	private int idTeam;
	private String name;
	private byte[] photo;
	private String history;
	private String description;
	private Tournament tournament;
	private List<Player> players;
	private Integer pts; //puntos
	private int gf; //goles a favor
	private int gc; //goles en contra
	private int diferencia; //diferencia de goles
	private int pj; //partidos jugados
	private Integer pg; //partidos ganados
	private int pp; //partidos perdidos
	private Integer pe; //partidos empatados
	private int position;

	public Team(){
		this.players = new ArrayList<Player>();
		this.pts=0;
		this.gc = 0;
		this.gf=0;
		this.pj=0;
		this.pp=0;
		this.pe=0;
		this.pg=0;
	}
	
	@Id
	@GeneratedValue
	@Column(name="Id_Team")
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	
	@Column(name ="Team_Name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Lob
	@Column(name= "TEAM_IMAGE")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	@Column(name ="Team_Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Column(name = "Player")			
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	@Column(name ="Team_Points")
	public int getPts() {
		return pts;
	}
	public void setPts(int pts) {
		this.pts = pts;
	}
	
	@Column(name ="Team_GF")
	public int getGf() {
		return gf;
	}
	public void setGf(int gf) {
		this.gf = gf;
	}
	
	@Column(name ="Team_GC")
	public int getGc() {
		return gc;
	}
	public void setGc(int gc) {
		this.gc = gc;
	}
	
	@Column(name ="Team_Diference")
	public int getDiferencia() {
		return diferencia;
	}
	public void setDiferencia(int diferencia) {
		this.diferencia = diferencia;
	}
	
	@Column(name ="Team_PJ")
	public int getPj() {
		return pj;
	}
	public void setPj(int pj) {
		this.pj = pj;
	}
	
	@Column(name ="Team_PG")
	public int getPg() {
		return pg;
	}
	public void setPg(int pg) {
		this.pg = pg;
	}
	
	@Column(name ="Team_PP")
	public int getPp() {
		return pp;
	}
	public void setPp(int pp) {
		this.pp = pp;
	}
	
	@Column(name ="Team_PE")
	public int getPe() {
		return pe;
	}
	public void setPe(int pe) {
		this.pe = pe;
	}
	
	@Column(name ="Team_Position")
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	@OneToOne(cascade = CascadeType.REMOVE)
	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	
	@Override
	public int compareTo(Object o) {
		Team team = (Team) o;
			if(this.pts.compareTo(team.getPts())==0){
				if(this.pg.compareTo(team.getPg())==0){
					return this.pe.compareTo(team.getPe());
				} else{
					return this.pg.compareTo(team.getPg());
				}
			}else{
				return this.pts.compareTo(team.getPts());
			}
	}
}
