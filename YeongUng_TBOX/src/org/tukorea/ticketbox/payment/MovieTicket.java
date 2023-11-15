package org.tukorea.ticketbox.payment;

public class MovieTicket {
	public static final char SEAT_EMPTY_MARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	private int row; // 좌석 행
	private int col; // 좌석 열
	private int reservedId; // 예약 번호
	private char status; // 좌석 상태 - EMPTY, RESERVED, PAY_COMPLETION
	public int getRow() { return row; }
	public int getCol() { return col; }
	public void setSeat( int row, int col) {
		this.row = row;
		this.col = col;
	} // 좌석번호저장
	public char getStatus() { return status; }
	public void setStatus(char status) { 
		this.status = status;
	}
	public void setReservedId(int id ) { 
		this.reservedId = id;
	} // 예약번호저장
	public int getReservedId() { return reservedId; } // 예약번호 읽기

}
