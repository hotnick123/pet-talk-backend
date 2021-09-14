package com.pettalk.pettalkbackend.repository.custom;

import com.pettalk.pettalkbackend.entity.Board;
import com.pettalk.pettalkbackend.entity.QBoard;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    QBoard board = QBoard.board;

    public Page<Board> findAllCustom(PageRequest pageRequest) {
        log.info(String.valueOf(pageRequest));
        return null;
    }

    @Override
    public List<Board> findPopularList() {
        QueryResults<Board> results = queryFactory
                .select(Projections.fields(Board.class, board.id, board.title, board.count))
                .from(board)
                .orderBy(board.count.desc())
                .orderBy(board.createdAt.asc())
                .limit(10)
                .fetchResults();
        List<Board> content = results.getResults();
        return content;
    }
}
