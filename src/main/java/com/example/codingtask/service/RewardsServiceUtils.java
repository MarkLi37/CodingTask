package com.example.codingtask.service;

import com.example.codingtask.constant.RewardsConstant;
import com.example.codingtask.domain.Rewards;
import com.example.codingtask.entity.Transaction;
import com.example.codingtask.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardsServiceUtils {
    @Autowired
    TransactionRepository transactionRepository;
 //   @Autowired
//    public RewardsServiceUtils(TransactionRepository transactionRepository){
//        this.transactionRepository = transactionRepository;
//    }

    public Rewards getRewardsByCustomerId(Integer customerId){
        Timestamp lastMonthTimestamp = daysToTimestamp(RewardsConstant.daysPerMonth);
        Timestamp lastSecondMonthTimestamp = daysToTimestamp(2*RewardsConstant.daysPerMonth);
        Timestamp lastThirdMonthTimestamp = daysToTimestamp(3*RewardsConstant.daysPerMonth);
        List<Transaction> lastMonthTransaction = getTransactionByCustomerIdAndDate(customerId,lastMonthTimestamp,Timestamp.from(Instant.now()));
        List<Transaction> lastSecondTransaction = getTransactionByCustomerIdAndDate(customerId,lastSecondMonthTimestamp,lastMonthTimestamp);
        List<Transaction> lastThirdMonthTransaction = getTransactionByCustomerIdAndDate(customerId,lastThirdMonthTimestamp,lastSecondMonthTimestamp);
        long lastMonthRewards = transactionsToRewardPoints(lastMonthTransaction);
        long lastSecondMonthRewards = transactionsToRewardPoints(lastSecondTransaction);
        long lastThirdMonthRewards = transactionsToRewardPoints(lastThirdMonthTransaction);
        return rewardsUtil(customerId,lastMonthRewards,lastSecondMonthRewards,lastThirdMonthRewards);
    }
    public long transactionsToRewardPoints(List<Transaction> transactions){
        long totalRewards = 0;
        for(Transaction t:transactions){
            double currentTransactionAmount = t.getTransactionAmount();
            totalRewards+=rewardsCalculation(currentTransactionAmount);
        }
        return totalRewards;
    }
    public long rewardsCalculation(double transactionAmount){
        if(transactionAmount>RewardsConstant.firstRewardLevel&&transactionAmount<=RewardsConstant.secondRewardLevel){
            return Math.round((transactionAmount-RewardsConstant.firstRewardLevel)*RewardsConstant.firstRewardRatio);
        }else if(transactionAmount>RewardsConstant.secondRewardLevel){
            return Math.round((transactionAmount-RewardsConstant.secondRewardLevel)*RewardsConstant.secondRewardRatio+RewardsConstant.firstRewardLevel*RewardsConstant.firstRewardRatio);
        }
        return 0;
    }
    public Rewards rewardsUtil(Integer customerId,long lastMonthRewards,long lastSecondMonthRewards,long lastThirdMonthRewards ){
        long totalRewards = lastMonthRewards+lastSecondMonthRewards+lastThirdMonthRewards;
        return new Rewards(customerId,lastMonthRewards,lastSecondMonthRewards,lastThirdMonthRewards,totalRewards);
    }
    List<Transaction> getTransactionByCustomerIdAndDate(Integer customerId,Timestamp startDate,Timestamp endDate){
       return  transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId,
                startDate,endDate);
    }
    public static Timestamp daysToTimestamp(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }
}
