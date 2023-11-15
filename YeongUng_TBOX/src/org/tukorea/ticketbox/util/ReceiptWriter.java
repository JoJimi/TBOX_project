package org.tukorea.ticketbox.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.tukorea.ticketbox.payment.Receipt;

public class ReceiptWriter {
	// 콘솔에 스크린 별 영수증 출력하기
	// 소프트,마녀를 잡아라,CardPay,123-4567,20000.0,23000.0
	public void printConsole(HashMap<Integer, Receipt> map) {
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		
		while(it.hasNext()) {
			System.out.println(map.get(it.next()).toBackupString());
		}
	}
}

