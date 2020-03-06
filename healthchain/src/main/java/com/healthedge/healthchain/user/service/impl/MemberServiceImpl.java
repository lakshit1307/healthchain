package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.user.Repository.MemberRepository;
import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import com.healthedge.healthchain.user.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl {

  @Autowired
  MemberRepository memberRepository;

    public List<BenefitPlanResponse> getBenefitPlansForMember(String memberId){
      Optional<Member> mem =memberRepository.findById(memberId);
      if(mem.isPresent()){

      }

        return ProviderServiceImpl.createBenefitPlan("BP_1");
    }



}
