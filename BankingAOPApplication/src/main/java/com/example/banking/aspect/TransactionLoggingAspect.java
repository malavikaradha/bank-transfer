package com.example.banking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class TransactionLoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(TransactionLoggingAspect.class.getName());

    @Before("execution(* com.example.banking.service.BankAccountService.deposit(..)) || execution(* com.example.banking.service.BankAccountService.withdraw(..))")
    public void logBeforeTransaction(JoinPoint joinPoint) {
        LOGGER.info(() -> "Transaction started: " + joinPoint.getSignature().getName()
                + " amount=" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.example.banking.service.BankAccountService.deposit(..)) || execution(* com.example.banking.service.BankAccountService.withdraw(..))", returning = "result")
    public void logSuccessfulTransaction(JoinPoint joinPoint, Object result) {
        LOGGER.info(() -> "Transaction successful: " + joinPoint.getSignature().getName()
                + "; resulting balance=" + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.banking.service.BankAccountService.deposit(..)) || execution(* com.example.banking.service.BankAccountService.withdraw(..))", throwing = "exception")
    public void logFailedTransaction(JoinPoint joinPoint, Throwable exception) {
        LOGGER.warning(() -> "Transaction failed: " + joinPoint.getSignature().getName()
                + "; reason=" + exception.getMessage());
    }
}
