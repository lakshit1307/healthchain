package com.healthedge.healthchain.user.Repository;

import com.healthedge.healthchain.user.entity.BenefitPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenfitPlanRepository extends JpaRepository<BenefitPlan, String> {

    List<BenefitPlan> findByBenefitPlanId(String id);




}
