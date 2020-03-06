package com.healthedge.healthchain.user.Repository;

import com.healthedge.healthchain.user.entity.BenefitPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenfitPlanRepository extends JpaRepository<BenefitPlan, String> {

    List<BenefitPlan> findByBenefitPlanId(String id);

    @Query(value = "SELECT * FROM benefit_plan WHERE benefit_plan_id=:benefitPlanId ORDER BY id Desc", nativeQuery = true)
    List<BenefitPlan> getBenefitPlanByIdSortedReverse(String benefitPlanId);




}
