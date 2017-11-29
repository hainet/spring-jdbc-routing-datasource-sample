package com.hainet.spring.jdbc.routing.datasource.sample.config.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
@Aspect
public class ReadOnlyAspect {

    @After("execution(public * com.hainet.spring.jdbc.routing.datasource.sample.domain.service.*.*(..))")
    public void rollbackAfterReadOnlyTransaction() {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
