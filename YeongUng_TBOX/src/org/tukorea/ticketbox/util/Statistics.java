package org.tukorea.ticketbox.util;

import java.util.*;
import org.tukorea.ticketbox.payment.Receipt;

public class Statistics {
	// 스크린 별 결제 금액 총액 계산
	public static double sum( HashMap<Integer, Receipt> map) {
		double sum = 0.0;
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();

		while(it.hasNext()) {		
			sum += map.get(it.next()).gettotalAmount();
		}
		
		return sum;
	}
}
