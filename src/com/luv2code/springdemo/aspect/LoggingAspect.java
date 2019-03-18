package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.luv2code.springdemo.entity.Customer;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

	@Pointcut("execution(com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
	}

	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		LOGGER.info(() -> "====>> In @Before: calling method: " + joinPoint.getSignature().toShortString());

		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof Customer) {
				Customer customer = (Customer) arg;
				LOGGER.info(customer::toString);
			}
		}
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		LOGGER.info(() -> "====>> In @AfterReturning: calling method: " + joinPoint.getSignature().toShortString());
		LOGGER.info(() -> "=======>> result: {0}" + result);
	}

}
