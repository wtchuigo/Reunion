package com.wtchuigo.reunion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtchuigo.reunion.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
