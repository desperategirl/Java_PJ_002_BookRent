package com.biz.rent.mapper;

import java.util.List;

import com.biz.rent.persistence.BookDTO;

public interface BookDao {
	
	// BookDao 인터페이스
	
	// 1. 도서코드와 도서명을 입력하여 도서를 등록하는 method
	// 		(만약 도서코드와 도서명이 같으면 
	//		등록할 수 없게 만들어야 한다)
	// 2. 도서 별 대여 여부를 확인할 수 있는 method
	// 3. 제목과 저자를 검색어로 포함해 도서를 검색할 수 있는 method
	
	public List<BookDTO> selectAll();
	
	public BookDTO findById(String b_code); // 도서코드로 도서를 검색
	
	public List<BookDTO> findByName(String b_name); // 도서명으로 도서를 검색
	public BookDTO findByBName(String name);
	
	public int insert(BookDTO bookDTO); // 도서 추가
	
	public int update(BookDTO bookDTO); // 도서 변경
	
	public int delete(String b_code);

	public String getMaxBookCode() ;


	
}