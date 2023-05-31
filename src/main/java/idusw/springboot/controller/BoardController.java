package idusw.springboot.controller;

import idusw.springboot.domain.Board;
import idusw.springboot.domain.Member;
import idusw.springboot.domain.PageRequestDTO;
import idusw.springboot.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {
     // BoardController 에서 사용할 BoardService 객체를 참조하는 변수

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        // Spring Framework 가 BoardService 객체를 주입, boardService(주입될 객체의 참조변수)
        this.boardService = boardService;
    }

    @GetMapping("/reg-form")
    public String getRegForm(PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("mb");
        if(member !=null){
            model.addAttribute("board", Board.builder().build());
            System.out.println(member.getEmail());
            return "/boards";
        }else
            return "/boards/reg-from"; // boards/reg-from.html 전달
    }

    @PostMapping("")
    public String postBoard(@ModelAttribute("board") Board dto, Model model, HttpServletRequest request) {
        session = request.getSession();
        Member member = (Member) session.getAttribute("mb");
        // form에서 hidden 전송하는 방식으로 변경
        dto.setWriterSeq(member.getSeq());
        dto.setWriterEmail(member.getEmail());
        dto.setWriterName(member.getName());

        Long bno = Long.valueOf(boardService.registerBoard(dto));

        return "redirect:/boards/" + bno; // 등록 후 상세세 보기
    }

    @GetMapping("")
    public String getBoards(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("list", boardService.findBoardAll(PageRequestDTO pageRequestDTO));
        return "/boards/list";
    }

    @GetMapping("/{bno}")
    public String getBoardByBno(@PathVariable("bno") Long bno, Model model) {
        Board board = boardService.findBoardById(Board.builder().bno(bno).build());
        boardService.findBoardById(board);
        model.addAttribute("dto", boardService.findBoardById(board));
        return "/boards/read";
    }

    @GetMapping("/{bno}/up-form")
    public String getUpForm(@PathVariable("bno") Long bno, Model model) {
        Board board = boardService.findBoardById(Board.builder().bno(bno).build());
        model.addAttribute("board", board);
        return "/boards/upform";
    }

    @PutMapping("/{bno}")
    public String putBoard(@ModelAttribute("board") Board board, Model model) {
        boardService.updateBoard(board);
        model.addAttribute(boardService.findBoardById(board);
        return "redirect:/boards/" + board.getBno();
    }

    @GetMapping("/{bno}/del-form")
    public String getDelForm(@PathVariable("board") Board board, Model model) {
        boardService.deleteBoard(board);
        model.addAttribute(board);
        return "/boards/del-form";
    }

    @DeleteMapping("/{bno}")
    public String deleteBoard(@ModelAttribute("board") Board board, Model model) {
        boardService.deleteBoard(board);
        model.addAttribute(board);
        return "redirect:/boards";
    }

    @GetMapping(value = {"/", ""})
    public String getBoardList(Model model){
        model.addAttribute("key", "value");
        return "/board/list"; // board/list.html 뷰로 전달
    }
}