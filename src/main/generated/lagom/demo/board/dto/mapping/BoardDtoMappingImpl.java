package lagom.demo.board.dto.mapping;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.entity.Board;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-24T15:28:03+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BoardDtoMappingImpl implements BoardDtoMapping {

    @Override
    public Board toEntity(BoardDTO.RequestSave request) {
        if ( request == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.boardId( request.getBoardId() );
        board.boardType( request.getBoardType() );
        board.writer( request.getWriter() );
        board.title( request.getTitle() );
        board.contents( request.getContents() );
        board.isPublic( request.getIsPublic() );
        board.regDate( request.getRegDate() );

        return board.build();
    }

    @Override
    public List<BoardDTO.Response> toDto(List<Board> board) {
        if ( board == null ) {
            return null;
        }

        List<BoardDTO.Response> list = new ArrayList<BoardDTO.Response>( board.size() );
        for ( Board board1 : board ) {
            list.add( toDto( board1 ) );
        }

        return list;
    }

    @Override
    public BoardDTO.Response toDto(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardDTO.Response response = new BoardDTO.Response();

        response.setBoardId( board.getBoardId() );
        response.setBoardType( board.getBoardType() );
        response.setTitle( board.getTitle() );
        response.setContents( board.getContents() );
        response.setWriter( board.getWriter() );
        response.setIsPublic( board.getIsPublic() );
        response.setRegDate( board.getRegDate() );

        return response;
    }
}
