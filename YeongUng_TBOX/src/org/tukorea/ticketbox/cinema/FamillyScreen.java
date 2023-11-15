package org.tukorea.ticketbox.cinema;

public class FamillyScreen extends Screen {
	public FamillyScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}
	public void showMovieInfo( ) {
		System.out.println("영화 이름 : " + movieName);
		System.out.println("영화 내용 : " + movieStory);
		System.out.println("영화 가격 : " + ticketPrice + "\n");
	}
}
