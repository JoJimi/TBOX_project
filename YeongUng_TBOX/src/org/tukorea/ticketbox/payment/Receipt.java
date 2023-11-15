package org.tukorea.ticketbox.payment;

public class Receipt {
	String client; // 사용자 이름
	String productName; // 영화 상품 이름
	String payMethod; // 결제 수단
	String payNumber; // 결제 정보(번호)
	double subTotalAmount; // 커미션 제외한 금액
	double totalAmount; // 커미션 포함한 금액
	public Receipt(String product, double amount, String name, String number, double commision, String method) {
		this.productName = product;
		this.subTotalAmount = amount;
		this.client = name;
		this.payNumber = number;
		this.totalAmount = Math.round(amount * (1 + commision));
		this.payMethod = method;
	}
	public double gettotalAmount() {
		return totalAmount;
	}
	@Override
	public String toString() { // 티켓 결제 내용 출력
		return "[ Receiced by :\t" + client + " ]\n[ Product :\t" +  productName
			+ " ]\n[ PayMethod :\t" + payMethod + " ]\n[ PayNumber :\t" + payNumber
			+ " ]\n[ SubTotal :\t" + subTotalAmount + " ]\n[ Total :\t" + totalAmount + " ]";
	} 
	// 영수증 내용을 구분자(,)를 사용하여 한 줄에 출력
	public String toBackupString() { 
		return client + ", " + productName + ", " 
	+ payMethod + ", " + payNumber + ", " + subTotalAmount + ", " + totalAmount;
	}
}
