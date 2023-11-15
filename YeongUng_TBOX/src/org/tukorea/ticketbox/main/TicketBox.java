package org.tukorea.ticketbox.main;

import java.util.*;

import org.tukorea.ticketbox.cinema.*;
import org.tukorea.ticketbox.payment.Receipt;
import org.tukorea.ticketbox.util.ReceiptWriter;
import org.tukorea.ticketbox.util.Statistics;

public class TicketBox {
	public static final String ADMIN_PASSWORD = "admin";

	private FamillyScreen famillyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	
	Scanner scan = new Scanner(System.in);
	public void initScreen() {
	// Screen(영화제목, 영화 줄거리, 티켓가격, 좌석(rowMax), 좌석(colMax))
		famillyScreen = new FamillyScreen("비포 선라이즈",
				"파리로 돌아가는 셀린과 비엔나로 향하는 제시.기차 안에서 우연히", 12000, 10, 10);
		animationScreen = new AnimationScreen("마녀를 잡아라",
				"사고로 부모를 잃은 한 소년이 할머니와 함께 작은 시골 마을에", 20000, 10, 10);
		premiumScreen = new PremiumScreen("인디아나 존스: 운명의 다이얼",
				"모험의 또 다른 이름, 마침내 그가 돌아왔다 1969년 뉴욕 전설적인이자", 50000, 5, 5);
		
	}
	public Screen selectScreen() {
		System.out.println("----------------------");
		System.out.println("[   상영관 선택 메인메뉴   ]");
		System.out.println("----------------------");
		
		System.out.println("1. 가족 영화 1관");
		System.out.println("2. 애니메이션 고화질 영화 2관");
		System.out.println("3. 프리미엄 고화질 영화 3관 (커피, 파니니 샌드위치 등 간식 제공)\n"
						 + "9. 관리자 메뉴\n"
						 + "   선택(1~3, 9)외 종료\n");
		System.out.print("상영관 선택 : ");
		try {
			int num = scan.nextInt();		
			switch(num) {
			case 1:
				return famillyScreen;
			case 2:
				return animationScreen;
			case 3: 
				return premiumScreen;
			case 9:
				managerMode();
				break;
			default:
				return null;	
			}
		}catch(InputMismatchException e) {
			System.out.println("정수가 아닙니다. 다시 입력하세요!");
			scan = new Scanner(System.in);
		}
		
		return null;
	}
	void managerMode() { // 관리자 기능
		Scanner sc = new Scanner(System.in);
		
		System.out.print("암호 입력: ");
		String password = sc.next();
		if(password.equals(ADMIN_PASSWORD)) {
			Statistics statistics = new Statistics();
			ReceiptWriter writer = new ReceiptWriter();
			HashMap<Integer, Receipt> familly = famillyScreen.getMap();
			HashMap<Integer, Receipt> animation = animationScreen.getMap();
			HashMap<Integer, Receipt> premium = premiumScreen.getMap();
			System.out.println("----------------------");
			System.out.println("----   관리자 기능   ----");
			System.out.println("----------------------");
			
			int sumTicket = familly.size() + animation.size() + premium.size();
			System.out.println("영화관 총 티켓 판매량 : " + sumTicket);
			System.out.println("가족 영화관 결제 총액 : " + statistics.sum(familly));
			System.out.println("애니메이션 영화관 결제 총액 : " + statistics.sum(animation));
			System.out.println("프리미엄 영화관 결제 총액 : " + statistics.sum(premium));
	
			System.out.println("\n가족 영화관 영수증 전체 출력");
			writer.printConsole(familly);			
			System.out.println("\n애니메이션 영화관 영수증 전체 출력");
			writer.printConsole(animation);			
			System.out.println("\n프리미엄 영화관 영수증 전체 출력");
			writer.printConsole(premium);	
			
		}
		else {
			System.out.println("관리자 암호가 틀렸습니다.");
		}

	}

}
