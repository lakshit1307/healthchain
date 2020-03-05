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
public class Member {

    @Id
    String memberId;

    String memberName;

    @OneToOne(fetch = FetchType.EAGER)
    BenefitPlan benefitPlan;
}
