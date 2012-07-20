package model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Player")

public class Player implements Comparable{
	
	private int idplayer;
	private String name;
	private String lastName;
	private String email;
	private byte[] photo;
	private String position;
	private Team team;
	private List<Goal> goals;
    private List<Card> greenCards;
    private List<Card> yellowCards;
    private List<Card> redCards;

	public Player(){
		this.goals = new ArrayList<Goal>();
        this.greenCards = new ArrayList<Card>();
        this.yellowCards = new ArrayList<Card>();
        this.redCards = new ArrayList<Card>();

    }
	
	
	@Id
	@GeneratedValue
	@Column(name="Id_Player")
	public int getIdplayer() {
		return idplayer;
	}
	public void setIdplayer(int idplayer) {
		this.idplayer = idplayer;
	}
	
	@Column(name ="Player_Name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name ="Player_LastName", nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name ="Player_Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name ="Player_Position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	@OneToOne(cascade = CascadeType.ALL)
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@OneToMany(cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Column(name = "Goals")			
	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

	@Lob
	@Column(name= "Player_Photo")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
	@OneToMany(cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Column(name = "GreenCard")
    public List<Card> getGreenCards(){
        return greenCards;
    }
    public void setGreenCards(List<Card> greenCards) {
        this.greenCards = greenCards;
    }

    @OneToMany(cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "GreenCard")
    public List<Card> getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(List<Card> yellowCards) {
        this.yellowCards = yellowCards;
    }

    @OneToMany(cascade = CascadeType.REMOVE)//, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "GreenCard")
    public List<Card> getRedCards() {
        return redCards;
    }

    public void setRedCards(List<Card> redCards) {
        this.redCards = redCards;
    }
	
	@Override
	public int compareTo(Object o) {
		Player player = (Player) o;
			if(this.name.compareTo(player.getName())==0){
				return this.lastName.compareTo(player.getLastName());
			}else{
				return this.name.compareTo(player.getName());
			}
	}
	
	

}
