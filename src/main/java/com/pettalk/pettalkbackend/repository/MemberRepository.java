package com.pettalk.pettalkbackend.repository;

import com.pettalk.pettalkbackend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByMemberId(String memberId);
}
