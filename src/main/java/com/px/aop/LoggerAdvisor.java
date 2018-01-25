package com.px.aop;

import com.px.entity.Customer;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAdvisor {
    private Logger logger = Logger.getLogger(LoggerAdvisor.class);

    @Pointcut("execution(* com.px.service.*Service.find(..))")
    private void aspectjMethod(){}

    @Around(value = "aspectjMethod()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
        logger.info("before method");
        Object returnValue = pjp.proceed();
        logger.info("after method");
        logger.info("returnClass: --->" + returnValue.getClass().toString());
        Customer customer = (Customer) returnValue;
        logger.info(customer.toString());
        customer.setName("Armourr");
        return customer;
    }


}
