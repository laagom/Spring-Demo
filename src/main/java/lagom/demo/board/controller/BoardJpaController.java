package lagom.demo.board.controller;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.entity.Board;
import lagom.demo.board.service.BoardJspService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpaBoards")
public class BoardJpaController {
    private final BoardJspService boardJspService;

    /* (JPA) 게시글 조회*/
    @GetMapping
    public List<BoardDTO.Response> getBoards(BoardDTO.Request req){
        return boardJspService.getBoards(req);
    }
}
