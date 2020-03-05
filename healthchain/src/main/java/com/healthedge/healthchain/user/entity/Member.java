package com.healthedge.healthchain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    String memberId;

    String memberName;

    @ManyToOne
    @JoinColumn(name = "benefitId", referencedColumnName = "id")
    private BenefitPlan benefitPlan;

    String accountId;
}
