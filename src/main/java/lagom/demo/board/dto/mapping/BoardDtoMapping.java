package lagom.demo.board.dto.mapping;

import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardDtoMapping {

    BoardDtoMapping INSTANCE = Mappers.getMapper(BoardDtoMapping.class);

    public Board toEntity(BoardDTO.RequestSave request);

    List<BoardDTO.Response> toDto (List<Board> board);

    BoardDTO.Response toDto(Board board);
}
