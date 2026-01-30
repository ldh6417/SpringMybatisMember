package com.zeus.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.domain.Member;
import com.zeus.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@MapperScan(basePackages = "com.zeus.mapper")
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/insertForm")
	public String boardInsertForm(Model model) {
		return "member/insertForm";
	}

	@PostMapping("/insert")
	public String memberInsert(Member member, Model model) {
		log.info("insert member = " + member.toString());
		try {
			int count = memberService.register(member);
			if (count > 0) {
				model.addAttribute("message", "%s 등록 성공.".formatted(member.getName()));
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%s 등록 실패".formatted(member.getName()));
		return "member/failed";
	}

	@GetMapping("/memberlist")
	public String memberList(Model model) {
		log.info("memberlist");

		try {
			List<Member> memberList = memberService.list();
			model.addAttribute("memberList", memberList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "member/memberList";
	}

	@GetMapping("/detail")
	public String memberDetail(Member member, Model model) {
		log.info("Detail" + member.toString());

		try {
			Member m = memberService.read(member);
			if (m == null) {
				return "member/failed";
			}
			model.addAttribute("member", m);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/detail";
	}

	@GetMapping("/updateForm")
	public String memberUpdateForm(Member m, Model model) {
		log.info("updateForm " + m.toString());

		try {
			Member member = memberService.read(m);
			if (member == null) {
				model.addAttribute("message", "%d 님의 정보가 없습니다".formatted(m.getNo()));
				return "member/failed";
			}
			model.addAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/updateForm";
	}

	@PostMapping("/update")
	public String memberUpdate(Member m, Model model) {
		log.info("update" + m.toString());
		try {
			int count = memberService.update(m);
			if (count > 0) {
				model.addAttribute("message", "%d 님의 회원수정성공".formatted(m.getNo()));
				return "member/success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "%d 님의 회원수정성공".formatted(m.getNo()));
		return "member/failed";
	}
	


}