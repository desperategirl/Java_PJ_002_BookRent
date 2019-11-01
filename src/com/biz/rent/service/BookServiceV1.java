package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.rent.config.DBConnection;
import com.biz.rent.mapper.BookDao;
import com.biz.rent.persistence.BookDTO;

public class BookServiceV1 {

	SqlSession sqlSession = null;
	Scanner scanner = null;
	protected BookDao bookDao;

	BookDTO bookdto;
	public BookServiceV1() {
		this.sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scanner = new Scanner(System.in);

		this.bookDao = DBConnection.getSqlSessionFactory().
				openSession(true).getMapper(BookDao.class);
	}

	/*
	 * 1. 도서코드로 도서를 검색하는 method 구현 (findById) 
	 * 2. 도서명으로 도서를 검색하는 method 구현 (findByName) 
	 * 3. 도서를 추가하는 method (insert)
	 */

	// 1. 도서코드로 도서를 검색하는 method 구현 (findById)
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

//			BookDao dao = sqlSession.getMapper(BookDao.class);
			bookdto = bookDao.findById(bookCode);

			System.out.println(bookdto.toString());
			break;

		}
	}

	// 2. 도서명으로 도서를 검색하는 method 구현 (findByName)
	public void findByName() {

		while (true) {

			System.out.println("==========================");
			System.out.println("도서명으로 도서를 검색합니다.");
			System.out.println("===============================");
			System.out.println("도서명을 입력하세요. 멈추려면 -Q을 입력하세요. >> ");
			String bookName = scanner.nextLine();

			if (bookName.equals("-Q"))
				break;

			BookDao dao = sqlSession.getMapper(BookDao.class);
			List<BookDTO> bookList = dao.findByName(bookName);
			for (BookDTO dto : bookList) {
				System.out.println(dto.toString());
			}
		}
	}

	// 3. 도서를 추가하는 method (insert)

	/*
	 * 도서정보(도서코드와 도서명)를 입력받아서 insert 수행 
	 * 도서코드를 입력받아서 
	 * 이미 있는 도서코드면 다시 입력하도록 
	 * 없으면 다음으로 진행
	 * 
	 * 1. 도서코드를 어떻게 할것인가? 
	 * 직접 입력하기(이미 문서로 작성된 경우)
	 * 
	 * 2. 도서이름을 입력하는데, 
	 * 도서명이 완전히 일치하는 도서가 이미 있는 경우 
	 * 도서명은 중복될 수 없음
	 * 
	 */
	public void insert() {

		System.out.println("===========================");

		String strBCode;
		while (true) {
			System.out.print("상품코드(Q:quit)");
			strBCode = scanner.nextLine();
			if (strBCode.equals("-Q"))
				break;
			if (strBCode.trim().isEmpty()) {

				System.out.println(strBCode);
				System.out.println("사용하시겠습니까?(Enter:Yes)");
				String strYesNo = scanner.nextLine();
				if (strYesNo.trim().isEmpty())
					break;
				else
					continue;

			}

			if (strBCode.length() != 5) {
				System.out.println("도서코드의 길이 규칙에 맞지 않음");
				// 조치취하기
				continue;
			}

			strBCode = strBCode.toUpperCase();
			if (!strBCode.substring(0, 1).equalsIgnoreCase("BK")) {
				System.out.println("상품코드는 첫글자가 BK로 시작되어야 함");
				continue;
			}

			try {
				Integer.valueOf(strBCode.substring(2));
			} catch (Exception e) {
				System.out.println("상품코드3번째 이후는 숫자만 올수 있음");
				continue;
			}

			if (bookDao.findById(strBCode) != null) {
				System.out.println("이미 등록된(사용중인)코드!!");
				continue;
			}
			break;

		} // bcode 입력 끝
		if (strBCode.equals("-Q"))
			return;

		// 도서명 입력
		String strBName;
		while (true) {

			System.out.print("도서명(-Q:quit) ");
			strBName = scanner.nextLine();
			if (strBName.equals("-Q"))
				break;

			if (bookDao.findByName(strBName) != null) {
				System.out.println("이미 등록된(사용중인)코드!!");
				continue;
			}

			if (strBName.trim().isEmpty()) {
				System.out.println("도서명은 반드시 입력해야 함");
				continue;
			}

		} // 도서명 입력 끝

	}

}

