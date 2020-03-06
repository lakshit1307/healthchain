package com.healthedge.healthchain.user.Repository;

import com.healthedge.healthchain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByBenefitPlanId(String Id);




}
