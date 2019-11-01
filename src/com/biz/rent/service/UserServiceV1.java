package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.rent.config.DBConnection;
import com.biz.rent.mapper.UserDao;
import com.biz.rent.persistence.UserDTO;

public class UserServiceV1 {

	SqlSession sqlSession = null;
	Scanner scanner = null;
	protected UserDao userDao;

	public UserServiceV1() {
		this.sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scanner = new Scanner(System.in);

		this.userDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UserDao.class);
	}

	/*
	 * 1. 회원을 등록하는 method (단, 이름과 전화번호가 같은 회원은 등록할 수 없다) 
	 * 2. 회원의 가입/탈퇴 여부를 수정하는 method (단 도서 반납 후 탈퇴가 가능) 
	 * 3. 이름과 전화번호를 대상으로 회원을 검색하는 method
	 * 
	 */

	public void insert() { // 회원을 등록하는 method

		System.out.println("===========================");

		String strUName;
		while (true) {
			System.out.print("회원명(Q:quit)");
			strUName = scanner.nextLine();
			if (strUName.equals("-Q"))
				break;
			if (strUName.trim().isEmpty()) {

				System.out.println(strUName);
				System.out.println("사용하시겠습니까?(Enter:Yes)");
				String strYesNo = scanner.nextLine();
				if (strYesNo.trim().isEmpty())
					break;
				else
					continue;
			}

			if (userDao.findByName(strUName) != null) {
				System.out.println("이미 등록된(사용중인) 회원명!!");
				continue;
			}
			break;

			// uName 입력 끝

		}

		// 회원 전화번호 입력
		String strUTel;
		while (true) {

			System.out.print("회원 전화번호 입력 (-Q:quit) ");
			strUTel = scanner.nextLine();
			if (strUTel.equals("-Q"))
				break;

			if (userDao.findByName(strUTel) != null) {
				System.out.println("이미 등록된(사용중인) 전화번호!!");
				continue;
			}

		} // 회원 등록 끝

	}
	
	
	public void insert2() { // 회원의 가입/탈퇴 여부를 수정하는 method (단 도서 반납 후 탈퇴가 가능) 
		
	}
	
	public void viewUser() { // 이름과 전화번호를 대상으로 회원을 검색하는 method
		
		while (true) { // 이름으로 검색

			System.out.println("=======================");
			System.out.println("회원명으로 회원을 검색합니다.");
			System.out.println("=======================");
			System.out.print("회원명을 입력하세요. >> ");
			System.out.println("멈추려면 -Q을 입력하세요.");
			String userName = scanner.nextLine();

			if (userName.equals("-Q"))
				break;

			UserDao dao = sqlSession.getMapper(UserDao.class);
			List<UserDTO> userdto = dao.findByName(userName);

			System.out.println(userdto.toString());
			break;
		}
		
		while (true) { // 전화번호로 검색

			System.out.println("=======================");
			System.out.println("회원 전화번호로 회원을 검색합니다.");
			System.out.println("=======================");
			System.out.print("전화번호를 입력하세요. >> ");
			System.out.println("멈추려면 -Q을 입력하세요.");
			String userTel = scanner.nextLine();

			if (userTel.equals("-Q"))
				break;

			UserDao dao = sqlSession.getMapper(UserDao.class);
			UserDTO userdto = dao.findByTel(userTel);

			System.out.println(userdto.toString());
			break;
		}
		
		
	}
	
	
}
