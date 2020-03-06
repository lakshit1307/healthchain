package com.healthedge.healthchain.user.controller;

import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import com.healthedge.healthchain.user.service.impl.MemberServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "v1.0/member")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    @ApiOperation(value = "get the benefit plan history for member")
    @GetMapping(value = "/{memberId}")
    @ResponseBody
    public ResponseEntity<List<BenefitPlanResponse>> getBenefitPlansForMember(String memberId) throws Exception {
        List<BenefitPlanResponse> benefitPlans = memberService.getBenefitPlansForMember(memberId);
        return new ResponseEntity(benefitPlans, HttpStatus.OK);
    }
}
