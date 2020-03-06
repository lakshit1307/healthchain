package com.healthedge.healthchain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BenefitPlanResponse {

    private String benefitPlanId;

    private String benefitPlanData;

    private String memberId;

    public BenefitPlanResponse(String success, String member_successfully_created) {
    }
}
