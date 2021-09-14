package com.pettalk.pettalkbackend.repository.custom;

import com.pettalk.pettalkbackend.entity.Gallery;
import com.pettalk.pettalkbackend.entity.QComment;
import com.pettalk.pettalkbackend.entity.QGallery;
import com.pettalk.pettalkbackend.entity.QUser;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class GalleryRepositoryCustomImpl implements GalleryRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    QGallery gallery = QGallery.gallery;
    QComment comment = QComment.comment;
    QUser user = QUser.user;

    @Override
    public PageImpl<Object[]> findAllCustom(Pageable pageable) {
        QueryResults<Tuple> results = queryFactory
                .select(gallery, user)
                .from(gallery)
                .join(user)
                .on(gallery.writer.eq(user.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .fetchResults();
        List<Tuple> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<Object[]>(content.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList()), pageable, total);
    }

    @Override
    public List<Gallery> findPopularList() {
        QueryResults<Gallery> results = queryFactory
                .select(Projections.fields(Gallery.class, gallery.id, gallery.title, gallery.count))
                .from(gallery)
                .orderBy(gallery.count.desc())
                .orderBy(gallery.createdAt.asc())
                .limit(10)
                .fetchResults();
        List<Gallery> content = results.getResults();
        return content;
    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();
        // Sort
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(Gallery.class, "gallery");
            orders.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        return orders;
    }
}


