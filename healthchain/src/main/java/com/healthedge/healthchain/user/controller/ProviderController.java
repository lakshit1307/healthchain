package com.healthedge.healthchain.user.controller;

import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import com.healthedge.healthchain.user.entity.Member;
import com.healthedge.healthchain.user.service.impl.ProviderServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "v1.0/provider")
public class ProviderController {

    @Autowired
    private ProviderServiceImpl providerService;


    @ApiOperation(value = "create a benefit plan")
    @PostMapping(value = "/benefitPlan")
    @ResponseBody
    public ResponseEntity<BaseResponse> createBenefitPlan(@RequestBody BenefitPlanRequest benefitPlan) throws CipherException, IOException {
        BaseResponse baseResponse = providerService.createBenefitPlan(benefitPlan);
        return new ResponseEntity(baseResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "create a member")
    @PostMapping(value = "/member")
    @ResponseBody
    public ResponseEntity<BaseResponse> createMember(@RequestBody Member member) {
        BaseResponse baseResponse = providerService.createMember(member);
        return new ResponseEntity(baseResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "update a benefit plan")
    @PutMapping(value = "/benefitPlan")
    @ResponseBody
    public ResponseEntity<BaseResponse> updateBenefitPlans(@RequestBody BenefitPlanRequest benefitPlanRequest) throws CipherException, IOException {
        BaseResponse baseResponse = providerService.createBenefitPlan(benefitPlanRequest);
        return new ResponseEntity(baseResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "get all the benefit plans present in the system")
    @GetMapping(value = "/benefitPlan")
    @ResponseBody
    public ResponseEntity<List<BenefitPlanResponse>> getAllBenefitPlansForProvider() throws CipherException, IOException, ExecutionException, InterruptedException {
//        providerService.retrieveFromLedger(null, null);
//        providerService.getEthAccounts();
        List<BenefitPlanResponse> benefitPlans = providerService.getAllBenefitPlans();
        return new ResponseEntity(benefitPlans, HttpStatus.OK);
    }

    @ApiOperation(value = "get a the benefit plan")
    @GetMapping(value = "/benefitPlan/{benefitPlanId}")
    @ResponseBody
    public ResponseEntity<List<BenefitPlanResponse>> getBenefitPlanHistory(@PathVariable("benefitPlanId") String benefitPlanId) {
        List<BenefitPlanResponse> benefitPlans = providerService.getBenefitPlanHistory(benefitPlanId);
        return new ResponseEntity(benefitPlans, HttpStatus.OK);
    }

}
