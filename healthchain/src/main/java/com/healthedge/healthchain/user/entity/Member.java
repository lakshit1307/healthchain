package com.healthedge.healthchain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    String memberId;

    String memberName;

    BenefitPlan benefitPlan;
}
