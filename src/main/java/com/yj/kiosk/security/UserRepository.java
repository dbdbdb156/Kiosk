package com.yj.kiosk.security;

import com.yj.kiosk.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByEmail(String email);

}
