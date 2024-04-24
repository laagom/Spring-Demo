package lagom.demo.board.repository.custom;

import lagom.demo.board.dto.BoardDTO;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardDTO.Response> findAllByQueryDSL(BoardDTO.Request req);
}
