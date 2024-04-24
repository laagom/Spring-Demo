package lagom.demo.board.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lagom.demo.board.dto.BoardDTO;
import lagom.demo.board.entity.QBoard;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    public final JPAQueryFactory queryFactory;
    private QBoard qBoard = QBoard.board;

    
    /* (DSL) 게시글 조회 */
    @Override
    public List<BoardDTO.Response> findAllByQueryDSL(BoardDTO.Request req) {
        return queryFactory
                .select(Projections.fields(BoardDTO.Response.class,
                        qBoard.boardId
                        , qBoard.boardType
                        , qBoard.title
                        , qBoard.writer
                        , qBoard.contents))
                .from(qBoard)
                //.where(qBoard.title.contains(req.getKeyword()))
                //.where(qBoard.title.contains(req.getKeyword()).or(qBoard.contents.contains(req.getKeyword())))
                .where(titleLike(req.getKeyword()))
                .offset(0)
                .limit(10)
                .orderBy(qBoard.regDate.desc())
                .fetch();
    }

    private BooleanExpression titleLike(String keyword) {
        return StringUtils.isNotEmpty(keyword) ? qBoard.title.contains(keyword) : null;
    }
}