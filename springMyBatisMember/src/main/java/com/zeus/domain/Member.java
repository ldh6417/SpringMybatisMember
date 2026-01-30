package com.zeus.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Member {
	private int no; 
	private String id; 
	private String pw; 
	private String name; 
	private Date regDate; 
	private Date modDate; 
	private String searchType;
	private String keyword;

	private List<MemberAuth> authList;
}
