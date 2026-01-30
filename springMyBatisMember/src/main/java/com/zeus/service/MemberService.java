package com.zeus.service;

import java.util.List;

import com.zeus.domain.Member;

public interface MemberService {
	// 회원가입 => 비지니스모델 (회원테이블가입, 회원권한테이블가입)
	public int register(Member member) throws Exception;

	// 회원리스트, 회원정보, 회원수정, 회원삭제, 권한삭제
	// 회원삭제 => 비지니스모델(회원테이블삭제, 회원권한테이블삭제)
	public List<Member> list() throws Exception;
	public Member read(Member member) throws Exception;
	public int update(Member member) throws Exception;
	public int delete(Member member) throws Exception;
	public List<Member> memberSearch(Member member)throws Exception;
}
