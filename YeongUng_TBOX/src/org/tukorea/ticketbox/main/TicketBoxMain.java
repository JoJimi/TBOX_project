package org.tukorea.ticketbox.main;
import org.tukorea.ticketbox.cinema.Screen;
import java.util.*;


public class TicketBoxMain {

	public static void main(String[] args) {
		TicketBox ticketBox = new TicketBox();
		Scanner scan = new Scanner(System.in);
		Screen screen = null;
		
		boolean mainMenu = true; // 상영관 선택 메뉴 사용
		ticketBox.initScreen(); // 3개의 스크린 객체를 생성
		
		while(true) {
			if(mainMenu) {
				screen = ticketBox.selectScreen();
				if( screen == null ) {
					System.out.print("\n 안녕히 가세요 !");
					scan.close();
					System.exit(0);
				}
				mainMenu = false;
			}
			screen.showScreenMenu();
			System.out.print("\n 메뉴를 선택하세요 >> ");
		
			try {
				int select = scan.nextInt();
				switch(select) {
				case 1: // 스크린 상영 영화 정보 보기
					screen.showMovieInfo();
					break;
				case 2:
					screen.showSeatMap();
					break;
				case 3:
					screen.reserveTicket();
					break;
				case 4:
					screen.changeTicket();
					break;
				case 5:
					screen.cancelReservation();
					break;
				case 6:
					screen.payment();
					break;
				case 7:
					screen.printTicket();
					break;
				case 9:
					mainMenu = true;
					break;
				default:
					System.out.println("번호가 틀렸습니다. 다시 입력해주세요.");
					break;
				}
			}catch(InputMismatchException e) {
				System.out.println("정수가 아닙니다. 다시 입력하세요!");
				scan = new Scanner(System.in);
			}
		}
	}
}
