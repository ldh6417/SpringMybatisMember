package com.zeus.controller;

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

	/*
	 * 
	 * @GetMapping("/boardlist") public String boardList(Model model) {
	 * log.info("boardlist");
	 * 
	 * try { List<Board> boardList = boardService.list();
	 * 
	 * model.addAttribute("boardList", boardList);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return "board/boardList"; }
	 * 
	 * @GetMapping("/detail") public String boardDetail(Board b, Model model) {
	 * log.info("boardDetail board = " + b.toString());
	 * 
	 * try { Board board = boardService.read(b); if (board == null) { return
	 * "board/failed"; } model.addAttribute("board", board);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return "board/detail"; }
	 * 
	 * @GetMapping("/delete") public String boardDelete(Board board, Model model) {
	 * log.info("boardDetail board = " + board.toString());
	 * 
	 * try { boardService.delete(board); } catch (Exception e) {
	 * e.printStackTrace(); model.addAttribute("message",
	 * "%d 님의 정보 삭제가 실패하였습니다.".formatted(board.getNo())); return "board/failed"; }
	 * model.addAttribute("message", "%d 님의 정보가 삭제되었습니다.".formatted(board.getNo()));
	 * return "board/success"; }
	 * 
	 * @GetMapping("/updateForm") public String boardUpdateForm(Board b, Model
	 * model) { log.info("updateForm board = " + b.toString());
	 * 
	 * try { Board board = boardService.read(b); if (board == null) {
	 * model.addAttribute("message", "%d 님의 정보가 없습니다".formatted(b.getNo())); return
	 * "board/failed"; } model.addAttribute("board", board); } catch (Exception e) {
	 * e.printStackTrace(); } return "board/updateForm"; }
	 * 
	 * @PostMapping("/update") public String updateBoard(Model model, Board board) {
	 * log.info("updateBoard board = " + board.toString());
	 * 
	 * try { boardService.update(board); } catch (Exception e) {
	 * e.printStackTrace(); model.addAttribute("message",
	 * "%d 님의 게시판이 수정되지 않았습니다.".formatted(board.getNo())); return "board/failed"; }
	 * model.addAttribute("message",
	 * "%d 님의 게시판이 수정되었습니다.".formatted(board.getNo())); return "board/success"; }
	 * 
	 * @GetMapping("/search") public String boardSearch(Model model, Board board) {
	 * log.info("searchType = " + board.toString());
	 * 
	 * try { List<Board> boardList = boardService.boardSearch(board);
	 * 
	 * model.addAttribute("boardList", boardList);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return "board/boardList"; }
	 */
}