package com.healthedge.healthchain.user.Repository;

import com.healthedge.healthchain.user.entity.BenefitPlan;
import com.healthedge.healthchain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByBenefitPlanId(String Id);

    @Query(value = "SELECT * FROM member WHERE member_id=:memberId", nativeQuery = true)
    List<Member> getMember(String memberId);




}
