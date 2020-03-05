package com.healthedge.healthchain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Member> member;

}
