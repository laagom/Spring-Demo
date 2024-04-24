package lagom.demo.board.controller;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    
    /* 게시글 조회 */
    @GetMapping
    public List<BoardDTO.Response> getBoards(BoardDTO.Request req){
        return boardService.getBoards(req);
    }
}
