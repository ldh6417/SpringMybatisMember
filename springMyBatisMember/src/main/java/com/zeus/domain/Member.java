package com.zeus.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Member {
	private int No; 
	private String Id; 
	private String Pw; 
	private String Name; 
	private Date regDate; 
	private Date modDate; 
	 
	private List<MemberAuth> authList;
}
