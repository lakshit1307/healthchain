package com.healthedge.healthchain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BenefitPlan {

    @Id
    private String benefitPlanId;

    private String benefitPlanName;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    private String transactionHash;

}
