package com.pettalk.pettalkbackend.repository.custom;

import com.pettalk.pettalkbackend.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    public Page<Board> findAllCustom(PageRequest pageRequest) {
        log.info(String.valueOf(pageRequest));
//        JPAFactoryQuery query = new JPAQueryFactory(em);
//        QMember m = QMember.member;


//        List<Board> boardList = entityManager.createQuery("SELECT b FROM board AS b", Board.class).getResultList();
//        Page<Board> boards = boardRepository.findAll(pageable);
//        return boards;
        return null;
    }
}
