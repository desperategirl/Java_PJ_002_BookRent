package com.biz.rent.mapper;

import java.util.List;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.UserDTO;

public interface UserDao {
	
   public UserDTO findByTel(String u_tel); // 회원의 전화번호로 회원을 검색
	
	public List<UserDTO> findByName(String u_name); // 회원명으로 회원을 검색
	
	public int insert(BookDTO bookDTO); // 회원 등록
	
	public int update(BookDTO bookDTO); // (도서를 반납한 회원만) 가입여부 수정(탈퇴)

}
