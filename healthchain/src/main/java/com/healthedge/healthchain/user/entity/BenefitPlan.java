package com.healthedge.healthchain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BenefitPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String benefitPlanId;

    private String transactionHash;



}
