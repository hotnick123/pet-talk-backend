package com.pettalk.pettalkbackend.service;

import com.pettalk.pettalkbackend.dto.BoardType;
import com.pettalk.pettalkbackend.dto.comment.CommentRequest;
import com.pettalk.pettalkbackend.entity.Comment;
import com.pettalk.pettalkbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment create(BoardType boardType, Long boardNo, CommentRequest commentRequest) {
        Comment comment = Comment.builder()
                .boardType(boardType)
                .boardNo(boardNo)
                .content(commentRequest.getContent())
                .writer(commentRequest.writer)
                .build();
        return commentRepository.save(comment);
    }

    public List<Comment> getComment(BoardType boardType, Long boardNo) {
        return commentRepository.findByBoardTypeAndBoardNo(boardType, boardNo);
    }
}
