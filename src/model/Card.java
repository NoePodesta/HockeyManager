package model;

import enums.CardColour;

import javax.persistence.*;




@Entity
@Table(name = "Card")
public class Card {
	

	private int idCard;
	private Match match;
	private Player player;
	private Team team;
    private CardColour cardColour;
	
	@Id
	@GeneratedValue
	@Column(name="idCard")
	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	

	@OneToOne(cascade = CascadeType.REMOVE)
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@OneToOne(cascade = CascadeType.REMOVE)
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

    @Column(name="CardColour")
    public CardColour getCardColour() {
        return cardColour;
    }

    public void setCardColour(CardColour cardColour) {
        this.cardColour = cardColour;
    }
    
	@OneToOne(cascade = CascadeType.REMOVE)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}




	
}