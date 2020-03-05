package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.common.util.Constants;
import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.entity.BenefitPlan;
import com.healthedge.healthchain.user.entity.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImpl {

    public BaseResponse createBenefitPlan(BenefitPlanRequest benefitPlan){
        //TODO Create a benefit plan


        BaseResponse baseResponse=new BaseResponse(Constants.SUCCESS, "Benefit Plan successfully created");
        return baseResponse;
    }

    public BaseResponse updateBenefitPlan(BenefitPlanRequest benefitPlan){
        //TODO update a benefit plan


        BaseResponse baseResponse=new BaseResponse(Constants.SUCCESS, "Benefit Plan successfully updated");
        return baseResponse;
    }

    public BaseResponse createMember(Member member){
        //TODO Create a member
        BaseResponse baseResponse=new BaseResponse(Constants.SUCCESS, "Member successfully created");
        return baseResponse;
    }

    public List<BenefitPlan> getAllBenefitPlans(){
        //TODO
        List<BenefitPlan> benefitPlans= new ArrayList<>();
        return benefitPlans;
    }

    public List<BenefitPlan> getBenefitPlanHistory(String benefitPlanId){
        //TODO
        List<BenefitPlan> benefitPlans= new ArrayList<>();
        return benefitPlans;
    }
}
