package idusw.springboot.controller;

import idusw.springboot.domain.Board;
import idusw.springboot.domain.Member;
import idusw.springboot.entity.BoardEntity;
import idusw.springboot.entity.MemberEntity;
import idusw.springboot.repository.BoardRepository;
import idusw.springboot.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardControllerTests {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Test
    void createMember() {
        Board board = Board.builder()
                .content("13@13.com")
                .title("13")
                .build();
        if(boardService.registerBoard(board) > 0 ) // 정상적으로 레코드의 변화가 발생하는 경우 영향받는 레코드 수를 반환
            System.out.println("등록 성공");
        else
            System.out.println("등록 실패");
    }

    @Test
    void initializeMember() {
        // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        IntStream.rangeClosed(1, 41).forEach(i -> {
            BoardEntity board = BoardEntity.builder()
                    .bno(Long.valueOf(i))
                    .content("Content" + i)
                    .title("title" + i)
                    .writer()
                    .build();
            boardRepository.save(board);
        });
    }


}
