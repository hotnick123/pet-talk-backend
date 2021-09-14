package com.pettalk.pettalkbackend.repository.custom;

import com.pettalk.pettalkbackend.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BoardRepositoryCustom {
    Page<Board> findAllCustom(PageRequest pageRequest);
    List<Board> findPopularList();
}
