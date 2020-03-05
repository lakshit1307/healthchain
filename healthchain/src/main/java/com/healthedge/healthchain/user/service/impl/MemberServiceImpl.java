package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberServiceImpl {

    public List<BenefitPlanResponse> getBenefitPlansForMember(String memberId){
        return ProviderServiceImpl.createBenefitPlan("BP_1");
    }

}
