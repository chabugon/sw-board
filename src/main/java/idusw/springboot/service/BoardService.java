package idusw.springboot.service;

import idusw.springboot.domain.Board;

import java.util.List;

public interface BoardService {
    int registerBoard(Board board);
    Board findBoardById(Board board); // id (유일한 식별자) - bno 로 조회
    List<Board> findBoardAll(); // 게시물 목록 출력 (페이지 처리나 정렬, 필터 ==? 검색)
    int updateBoard(Board board); // 게시물 정보
    int deleteBoard(Board board); // 게시물이 ID 값만
}
