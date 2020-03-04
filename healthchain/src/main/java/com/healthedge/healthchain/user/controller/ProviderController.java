package com.healthedge.healthchain.user.controller;

import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.dto.UserDTO;
import com.healthedge.healthchain.user.entity.BenefitPlan;
import com.healthedge.healthchain.user.entity.Member;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1.0/provider")
public class ProviderController {

    @ApiOperation(value = "view a user")
    @PostMapping(value = "/user/{userId}")
    @ResponseBody
    public ResponseEntity createBenefitPlan(@RequestBody BenefitPlanRequest benefitPlan) {
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    public ResponseEntity createMember(@RequestBody Member member){

    }

    public ResponseEntity updateBenefitPlans(@RequestBody BenefitPlanRequest benefitPlanRequest){

    }


    public getAllBenefitPlansForProvider(){

    }


    public getBenefitPlanHistory(String benefitPlanId) {
    }

}
