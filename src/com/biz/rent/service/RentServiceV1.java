package com.biz.rent.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.biz.rent.config.DBConnection;
import com.biz.rent.mapper.BookDao;
import com.biz.rent.mapper.RentDao;
import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentDTO;

public class RentServiceV1 {

	// SqlSession sqlSession = null;
	Scanner scanner = null;
	protected RentDao rentDao;
	BookDTO bookdto = new BookDTO();
	BookDao dao;

	public RentServiceV1() {
		/*
		 * this.sqlSession = DBConnection.getSqlSessionFactory(). openSession(true);
		 */
		scanner = new Scanner(System.in);

		this.rentDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(RentDao.class);
		dao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(BookDao.class);
	}

	public List<RentDTO> rentInfoView() // 1번 method
	{

		return null;
	}
	// 대출 정보를 보여주는 method

	public void findById() {
		// Dao Interface에서 지정한 method와
		// service 클래스에 구현한 method는
		// 이름이 같아도 되고 달라도 된다
		// 누구건지만 알면 됨

		while (true) {

			System.out.println("=======================");
			System.out.println("도서코드로 도서를 검색합니다.");
			System.out.println("=======================");
			System.out.print("도서코드를 입력하세요. >> ");
			System.out.println("멈추려면 -Q을 입력하세요.");
			String bookCode = scanner.nextLine();

			if (bookCode.equals("-Q"))
				break;

			bookdto = dao.findById(bookCode);
			bookdto.getB_code();
			System.out.println(bookdto.toString());
			break;

		}
	}

	public int viewAndupdate(RentDTO rentDTO)
	// 2. 대출할 도서, 대출할 회원을 선택
	// - 대여여부를 확인하여
	// - 대출이 가능하면 대출하게 하고 대여일로부터 2주 후 반납예정일 알려줌
	// - 이미 대여된 도서라서 대출이 불가하면 불가하다고 알려줌
	{
		BookDTO bDTO = rentDao.viewAndupdate(bookdto.getB_code());
		if (bDTO != null) {
			System.out.println("대출중인 책이라서 대출 불가능");
			return 0;
		}
		System.out.println("대출 가능한 책입니다.");

		LocalDate localDate = LocalDate.now();

		System.out.println("대출하신 도서의 이름은 " + bookdto.getB_name() + "입니다.");
		System.out.println("대출하신 날짜는 " + localDate.toString() + "입니다.");
		System.out.println("반납하실 날짜는 " + localDate.plusDays(14).toString() + "입니다.");

		RentDTO rDTO = new RentDTO();
		rDTO.setRent_bcode(bookdto.getB_code());
		rDTO.setRent_date(localDate.toString());
		rDTO.setRent_return_date(localDate.plusDays(14).toString());
		rDTO.setRent_ucode("u_code");
		rentDao.insert(rDTO);

		return 0;
	}
	

	public void update()
	// 이미 대출된 도서만을 대상으로 도서반납
	// 도서명을 입력하고
	// 도서의 대출가능여부 상태를 수정
	{

		while (true) {
			System.out.println("반납할 도서의 도서코드를 입력하세요. >> ");
			String bookCode = scanner.nextLine();
			// bookdto = dao.findById(bookCode);
			RentDTO rDTO = rentDao.findByBCode(bookCode);
			if (rDTO == null) {
				System.out.println("반납해야하는 책이 아닙니다");
				continue;
			}
			System.out.println("반납하시려면 y를 입력하세요.");
			String str = scanner.nextLine();
			if (str.equals("y")) {
				rDTO.setRent_retur_yn(str);
				rentDao.update(rDTO);
				System.out.println( "반납되었습니다.");
			} else {
				System.out.println("y가 입력되지 않아 반납이 되지 않았습니다. 다시 입력하세요. ");
				continue;
			}
			break;
		}

	}
}
