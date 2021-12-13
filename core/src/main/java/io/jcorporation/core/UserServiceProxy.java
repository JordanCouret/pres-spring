package io.jcorporation.core;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UserServiceProxy implements MethodInterceptor {

    private final Log log = LogFactory.getLog(getClass());

    private final UserDAO userDAO;

    public UserServiceProxy(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("init connection bdd");
        this.userDAO.initConnection();
        try {
            Object proceed = invocation.proceed();
            userDAO.commit();
            log.info("commit la transaction");
            return proceed;
        } catch (RuntimeException e) {
            userDAO.rolllback();
            log.info("rollback la transaction");
            throw e;
        }
    }
}
