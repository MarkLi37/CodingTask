package com.example.codingtask.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Rewards {
    private Integer customerId;
    private long lastFirstMonthRewards;
    private long lastSecondMonthRewards;
    private long lastThirdMonthRewards;
    private long lastThreeMonthsRewards;
}
