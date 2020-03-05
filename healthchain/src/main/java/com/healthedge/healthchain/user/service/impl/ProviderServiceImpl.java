package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.common.util.Constants;
import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
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

    public List<BenefitPlanResponse> getAllBenefitPlans(){
        //TODO
        return createBenefitPlan("BP_1");
    }

    public List<BenefitPlanResponse> getBenefitPlanHistory(String benefitPlanId){
        //TODO
        return createBenefitPlan("BP_1");

    }

    public static List<BenefitPlanResponse> createBenefitPlan(String benefitPlanId){
        List<BenefitPlanResponse> benefitPlans= new ArrayList<>();
        BenefitPlanResponse benefitPlan=new BenefitPlanResponse();
        benefitPlan.setBenefitPlanId(benefitPlanId);
        benefitPlan.setBenefitPlanName("BENEFIT_PLAN_1");
        benefitPlan.setBenefitPlanData("The benefit plan data will be shown here");
        return benefitPlans;
    }
}
