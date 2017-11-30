package com.briteitservices.week4;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.interceptor.InvocationContext;
import org.apache.deltaspike.jpa.spi.transaction.TransactionStrategy;

/**
 * We leave transaction management up to the EJB layer
 */
@Dependent
@Alternative
public class NoOpTransactionStrategy implements TransactionStrategy {

    @Override
    public Object execute(InvocationContext invocationContext) throws Exception {
        return invocationContext.proceed();
    }
}
