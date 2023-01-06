package com.example.codingtask;

import com.example.codingtask.entity.Transaction;
import com.example.codingtask.service.RewardsServiceUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    RewardsServiceUtils rewardsServiceUtils = new RewardsServiceUtils();

    @Test
    public void testTransactionsToRewardPoints() {
        assertEquals(rewardsServiceUtils.rewardsCalculation(120), 90);
    }
    @Test
    public void testMinimumValue() {
        int min = Integer.MIN_VALUE;
        assertEquals(0,rewardsServiceUtils.rewardsCalculation(min));
    }

    @Test
    public void testMaximumValue() {
        int max = Integer.MAX_VALUE;

        assertEquals(4294967144L,rewardsServiceUtils.rewardsCalculation(max));
    }


    @Test
    public void testZero() {
        assertEquals(0,rewardsServiceUtils.rewardsCalculation(0));
    }
    @Test
    public void testInvalidValue() {
        int invalid = -1;
        assertEquals(0,rewardsServiceUtils.rewardsCalculation(-1));
    }

}
