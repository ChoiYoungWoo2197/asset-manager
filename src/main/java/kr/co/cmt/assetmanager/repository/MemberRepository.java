package kr.co.cmt.assetmanager.repository;

import kr.co.cmt.assetmanager.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
