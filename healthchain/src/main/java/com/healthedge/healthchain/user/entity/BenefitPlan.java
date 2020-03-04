package com.healthedge.healthchain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BenefitPlan {

    @Id
    private String benefitPlan;

    private String benefitPlanName;

    private Account account;

    private String transactionHash;

}
