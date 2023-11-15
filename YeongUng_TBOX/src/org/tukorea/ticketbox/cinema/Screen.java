package org.tukorea.ticketbox.cinema;

import org.tukorea.ticketbox.payment.*;
import java.util.*;

public abstract class Screen {
	protected int ticketPrice; // 티켓 가격
	protected int rowMax; // 좌석 행 최대 값
	protected int colMax; // 좌석 열 최대 값
	protected String movieName; // 상영중인 영화 제목
	protected String movieStory; // 상영중인 영화 줄거리
	protected MovieTicket [][] seatArray; // 좌석 2차원 배열
	public abstract void showMovieInfo( ); // 영화 정보 보여주기
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>();
	
	Screen(String name, String story, int price, int row, int col) { // 생성자
		this.movieName = name;
		this.movieStory = story;
		this.ticketPrice = price;
		this.rowMax = row;
		this.colMax = col;
		seatArray = new MovieTicket[rowMax][colMax];
		for(int i = 0; i < rowMax; i++) {
			for(int j = 0; j < colMax; j++) {
				seatArray[i][j] = new MovieTicket();
				seatArray[i][j].setStatus(MovieTicket.SEAT_EMPTY_MARK); 
			}
		}
	}
	
	public void showScreenMenu() { // 상영관 메뉴 보여주기
		if(movieName == "비포 선라이즈") {
			System.out.println("----------------------");
			System.out.println(" 영화 메뉴 - 비포 선라이즈  ");
			System.out.println("----------------------");
		}
		else if(movieName == "마녀를 잡아라") {
			System.out.println("----------------------");
			System.out.println(" 영화 메뉴 - 마녀를 잡아라  ");
			System.out.println("----------------------");
		}
		else if(movieName == "인디아나 존스: 운명의 다이얼") {
			System.out.println("----------------------");
			System.out.println(" 영화 메뉴 - 인디아나 존스: 운명의 다이얼");
			System.out.println("----------------------");
		}
		System.out.println("1. 상영 영화 정보");
		System.out.println("2. 좌석 예약 현황");
		System.out.println("3. 좌석 예약 하기");
		System.out.println("4. 좌석 변경 하기");
		System.out.println("5. 예약 취소 하기");
		System.out.println("6. 좌석 결제 하기");
		System.out.println("7. 티켓 출력 하기");
		System.out.println("9. 메인 메뉴 이동");
		
	}
	public void showSeatMap() { // 상영관 좌석 예약 현황 보여주기
		System.out.println("\t[ 좌석 예약 현황 ]");
		System.out.print("\t");
		for(int i = 0; i < rowMax; i++) {
			System.out.print("[" + (i + 1) + "] ");	
		}
		System.out.println();
		for(int i = 0; i < rowMax; i++) {
			System.out.print("[" + (i + 1) + "]\t");
			
			for(int j = 0; j < colMax; j++) {
				System.out.print("[" + seatArray[i][j].getStatus() + "] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private MovieTicket checkReservedId(int id) { // 예약 번호로 티켓 체크하기
		for(int i = 0; i < rowMax; i++) {
			for(int j = 0; j < colMax; j++) {
				if(seatArray[i][j].getReservedId() == id) {
					return seatArray[i][j];
				}
			}
		}
		return null;
	}
/* 랜덤으로 예약 번호 발급
번호범위 : 100부터 (총 좌석수 * 8 + 100) 까지 부여 */
	public void reserveTicket() { // 좌석 예약
		Scanner sc  = new Scanner(System.in);
		System.out.println(" [ 좌석 예약 ]");
		System.out.print("좌석 행 번호 입력(1 ~ " + rowMax + ") : ");
		int row = sc.nextInt();
		System.out.print("좌석 열 번호 입력(1 ~ " + colMax + ") : ");
		int col = sc.nextInt();
		int num = (int) (Math.random() * rowMax * colMax * 8) + 100;
		
		if(seatArray[row-1][col-1].getStatus() == MovieTicket.SEAT_RESERVED_MARK) {
			System.out.println("이미 예약된 좌석입니다.");
			return;
		}
		
		if(seatArray[row-1][col-1].getStatus() == MovieTicket.SEAT_PAY_COMPLETION_MARK) {
			System.out.println("이미 결제된 좌석입니다.");
			return;
		}
		
		seatArray[row-1][col-1].setReservedId(num);
		seatArray[row-1][col-1].setStatus(MovieTicket.SEAT_RESERVED_MARK);
		seatArray[row-1][col-1].setSeat(row, col);
		System.out.println("행[" + row + "] 열[" + col + "] " 
		+ seatArray[row-1][col-1].getReservedId() + " 예약 번호로 접수되었습니다.");
	}
	public void changeTicket () {
		MovieTicket ticket = new MovieTicket();
		Scanner sc  = new Scanner(System.in);
		System.out.println(" [ 좌석 변경 ]");
		System.out.print("좌석 예약 번호 입력 : ");
		int num = sc.nextInt();
		
		if(num < 100 || num > 100 + rowMax * colMax * 8 ) {
			System.out.println("예약 번호를 다시 확인해주세요.");
			return;
		}
		
		ticket = checkReservedId(num);
		
		if(ticket == null) {
			System.out.println("해당하는 예약 번호가 없습니다.");
			return;
		}
		
		System.out.print("좌석 행 번호 입력(1 ~ " + rowMax + ") : ");
		int row = sc.nextInt();
		System.out.print("좌석 열 번호 입력(1 ~ " + colMax + ") : ");
		int col = sc.nextInt();	
		
		if(seatArray[row-1][col-1].getStatus() == MovieTicket.SEAT_RESERVED_MARK) {
			System.out.println("이미 예약된 좌석입니다.");
			return;
		}
		
		if(seatArray[row-1][col-1].getStatus() == MovieTicket.SEAT_PAY_COMPLETION_MARK) {
			System.out.println("이미 결제된 좌석입니다.");
			return;
		}
		
		checkReservedId(num).setStatus(MovieTicket.SEAT_EMPTY_MARK);
		checkReservedId(num).setReservedId(0);
		
		seatArray[row-1][col-1].setSeat(row, col);
		seatArray[row-1][col-1].setReservedId(num);
		seatArray[row-1][col-1].setStatus(MovieTicket.SEAT_RESERVED_MARK);
		
		System.out.println("예약번호 " + seatArray[row-1][col-1].getReservedId() +
		" 행[" + seatArray[row-1][col-1].getRow() + "] 열[" + seatArray[row-1][col-1].getCol() + "] 좌석으로 변경되었습니다.");
	}
	public void cancelReservation() {
		MovieTicket ticket = new MovieTicket();
		Scanner sc  = new Scanner(System.in);
		System.out.println(" [ 좌석 예약 취소 ]");
		System.out.print("좌석 예약 번호 입력 : ");
		int num = sc.nextInt();
		
		if(num < 100 || num > 100 + rowMax * colMax * 8 ) {
			System.out.println("예약 번호를 다시 확인해주세요.");
			return;
		}
		
		if(checkReservedId(num).getStatus() == MovieTicket.SEAT_PAY_COMPLETION_MARK) { 
			System.out.println("이미 결제돼서 취소가 불가능합니다.");
			return;
		}
		
		ticket = checkReservedId(num);
		
		if(ticket == null) 
			System.out.println("해당하는 예약 번호가 없습니다.");
		else {
			System.out.println("예약번호 " + ticket.getReservedId() +" 예약 취소 처리되었습니다.");
			ticket.setStatus(MovieTicket.SEAT_EMPTY_MARK);
			ticket.setReservedId(0);
		}
	}
	public void payment() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" [ 좌석 예약 결제 ]");
		System.out.print("예약 번호 입력 : ");
		int reserveNum = sc.nextInt();
		
		if(reserveNum < 100 || reserveNum > 100 + rowMax * colMax * 8 ) {
			System.out.println("예약 번호를 다시 확인해주세요.");
			return;
		}
		if(checkReservedId(reserveNum) == null) {
			System.out.println("예약 번호를 다시 확인해주세요.");
			return;
		}
		if(checkReservedId(reserveNum).getStatus() == MovieTicket.SEAT_PAY_COMPLETION_MARK){
			System.out.println("이미 결제되었습니다.");
			return;
		}
		System.out.println("----------------------");
		System.out.println("\t결제 방식 선택");
		System.out.println("----------------------");
		System.out.println("1. 은행 이체");
		System.out.println("2. 카드 결제");
		System.out.println("3. 모바일 결제");
		System.out.print("결재 방식 입력(1~3) : ");
		try {
			int PayMethod = sc.nextInt();
			
			switch(PayMethod) {
			case Pay.BANK_TRANSFER_PAYMENT:{
				System.out.println("[ 은행 이체 ]");
				System.out.print("이름 입력: ");
				String name = sc.next();
				System.out.print("은행 번호 입력(8자리수) : ");
				String num = sc.next();
				if(num.length() != 8) {
					System.out.println("은행 번호가 틀렸습니다.");
					return;
				}
				BankTransfer bank = new BankTransfer();			
				Receipt receipt = bank.charge(movieName, ticketPrice, name, num);
				checkReservedId(reserveNum).setStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
				receiptMap.put( checkReservedId(reserveNum).getReservedId(), receipt );
				System.out.println("은행 결제가 완료되었습니다. : " + receipt.gettotalAmount() + "원");
				break;	
			}
			case Pay.CREDIT_CARD_PAYMENT:{
				System.out.println("[ 카드 결제 ]");
				System.out.print("이름 입력: ");
				String name = sc.next();
				System.out.print("카드 번호 입력(8자리수) : ");
				String num = sc.next();
				if(num.length() != 8) {
					System.out.println("카드 번호가 틀렸습니다.");
					return;
				}
				CardPay card = new CardPay();			
				Receipt receipt = card.charge(movieName, ticketPrice, name, num);
				checkReservedId(reserveNum).setStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
				receiptMap.put( checkReservedId(reserveNum).getReservedId(), receipt );
				System.out.println("카드 결제가 완료되었습니다. : " + receipt.gettotalAmount() + "원");
				break;	
			}
			case Pay.MOBILE_PHONE_PAYMENT:{
				System.out.println("[ 모바일 결제 ]");
				System.out.print("이름 입력: ");
				String name = sc.next();
				System.out.print("핸드폰 번호 입력(11자리수) : ");
				String num = sc.next();
				if(num.length() != 11) {
					System.out.println("핸드폰 번호가 틀렸습니다.");
					return;
				}
				
				MobilePay mobile = new MobilePay();
				Receipt receipt = mobile.charge(movieName, ticketPrice, name, num);
				checkReservedId(reserveNum).setStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
				receiptMap.put( checkReservedId(reserveNum).getReservedId(), receipt );
				System.out.println("모바일 결제가 완료되었습니다. : " + receipt.gettotalAmount() + "원");
				break;	
			}
			default:
				System.out.println("번호가 틀렸습니다.");
				break;	
			}
		}catch(InputMismatchException e) {
			System.out.println("정수가 아닙니다. 다시 입력하세요!");
			sc = new Scanner(System.in);
		}
		
	}
	public void printTicket() {  // 티켓 영수증 출력
		Scanner sc = new Scanner(System.in);
		System.out.println("[ 좌석 티켓 출력 ]");
		System.out.print("예약 번호 입력 : ");
		int num = sc.nextInt();
		
		if(checkReservedId(num) != null) {
			if(checkReservedId(num).getStatus() == MovieTicket.SEAT_PAY_COMPLETION_MARK) {
				Receipt receipt = receiptMap.get(num);
				System.out.println("----------------------");
				System.out.println("\tReceipt");
				System.out.println("----------------------");
				System.out.println(receipt.toString());
			}
			else {
				System.out.println("아직 결제된 상품이 아닙니다.");
			}
		}
		else {
			System.out.println("번호를 다시 확인해주세요.");
		}
	
	}
	public HashMap<Integer, Receipt> getMap(){
		return receiptMap;
	}
}
