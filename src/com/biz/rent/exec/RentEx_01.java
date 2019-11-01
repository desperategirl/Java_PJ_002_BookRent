package com.biz.rent.exec;

import java.time.LocalDate;

import com.biz.rent.persistence.RentDTO;
import com.biz.rent.service.RentServiceV1;

public class RentEx_01 {

	public static void main(String[] args) {
		System.out.println("클래스 초기화 실행전");
		

		
		RentServiceV1 rsV1 = new RentServiceV1();
		// System.out.println("rentInfo 실행전");
		rsV1.findById();
		System.out.println();

		// System.out.println("rentInfo 실행후");
		RentDTO d = null;
		
		System.out.println();
		
		rsV1.viewAndupdate(d);
		System.out.println();
		rsV1.update();
		
		
		
		
	}
	
	
}
