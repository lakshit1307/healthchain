package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.user.Repository.BenfitPlanRepository;
import com.healthedge.healthchain.user.Repository.MemberRepository;
import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import com.healthedge.healthchain.user.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private BenfitPlanRepository benfitPlanRepository;

    @Autowired
    private ProviderServiceImpl providerService;


    public List<BenefitPlanResponse> getBenefitPlansForMember(String memberId) throws Exception {
      Member member=memberRepository.getOne(memberId);
      return providerService.getBenefitPlanHistory(member.getBenefitPlanId());
    }


}
