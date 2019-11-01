package com.biz.rent.mapper;

import java.util.List;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentDTO;

public interface RentDao {

	// RentDao 인터페이스
	
		/*
		 * 1.도서, 회원을 선택할 시 검색 기능을 제공하는 method
		 *   대출 정보(도서정보, 회원정보, 대여일, 반납예정일, 반납여부)
		 * 		를 알려주는 method
		 * 		(도서 또는 회원을 검색했을 때 검색결과를 출력)
		 * 
		 * 2. 도서 대출 시 
		 *     대출할 도서와 대출할 회원을 기존 정보에서 선택하고 
		 *     대출정보를 update 하는 method
		 * 2-2. 대여여부를 확인해 대출이 불가하게 만드는 method
		 * 2-3. 도서 대출 가능하면 도서 대여일로부터 
		 *      2주 후의 반납 예정일을 알려주는 method
		 * 
		 * 3. 대출된 도서를 대상으로, 도서 반납을 받는 method
		 * 		(도서를 검색하고 대출여부의 상태를 수정)
		 * 
		 */
	
	
		public List<RentDTO> rentInfoView(); // 1번 method
		
		public BookDTO viewAndupdate(String code); 
		// 2. 대출할 도서, 대출할 회원을 선택
		// - 대여여부를 확인하여 
		//   - 대출이 가능하면 대출하게 하고 대여일로부터 2주 후 반납예정일 알려줌
		//   - 이미 대여된 도서라서 대출이 불가하면 불가하다고 알려줌
		

		
		public int update(RentDTO rentDTO); 
		// 대출된 도서를 대상으로 도서반납을 받음
		// 도서 또는 회원명을 검색하고 도서의 대출여부와 
		// 회원의 도서반납 상태를 수정

		public void insert(RentDTO rDTO);

		public RentDTO findByBCode(String b_code);
		

	
	
}
