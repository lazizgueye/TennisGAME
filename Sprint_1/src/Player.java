package javaTennisGAME;

public class Player {
	// Variables
	private String name;
	private Boolean playTour;
	private int score;
	
	// Constructors
	public Player (String _name) {
		this.name = _name;
		this.playTour = false;
		this.score = 0;
	}
	
	// Getters
	public String getName() {
		return this.name;
	}
	public Boolean getPlayTour() {
		return this.playTour;
	}
	public int getScore() {
		return this.score;
	}
	
	// Setters
	public void setPlayTour(Boolean _tour) {
		this.playTour = _tour;
	}
	public void setScore() {
		if(this.score == 0) this.score = 15;
		else if(this.score == 15) this.score = 30;
		else if(this.score == 30) this.score = 40;
		else this.score = 50;
	}
	
	// Methods
	public void print() {
		if(this.score>40)
			System.out.println(this.name +" : Win Game");
		else
			System.out.println(this.name +" : "+ this.score);
	}
	
}
