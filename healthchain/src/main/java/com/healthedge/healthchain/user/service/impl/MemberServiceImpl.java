package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.user.entity.BenefitPlan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl {

    public List<BenefitPlan> getBenifitPlansForMember(String memberId){
        List<BenefitPlan> benefitPlans= new ArrayList<>();
        return benefitPlans;
    }

}
