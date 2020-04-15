package com.eliz.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	//set up pointcut declaration
	
	@Pointcut("execution(* com.eliz.springdemo.controller.*.*(..))")
	private void forControllerPackage(){}
	
	@Pointcut("execution(* com.eliz.springdemo.service.*.*(..))")
	private void forServicePackage(){}

	@Pointcut("execution(* com.eliz.springdemo.dao.*.*(..))")
	private void forDAOPackage(){}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAllApp(){}
	
	@Before("forAllApp()")
	private void beforeCallingMethod(JoinPoint theJoinPoint){
		
		String theMethod = theJoinPoint.getSignature().toShortString();
		
		logger.info("\n=======> in @Before: calling method:"+theMethod);
		Object[] args = theJoinPoint.getArgs();
		for(Object tmpArgs: args){
			logger.info("\n========> The Arguments:"+tmpArgs);
		}
		
	}
	
	@AfterReturning(pointcut="forAllApp()",
					returning="result")
	private void afterReturningAdvice(JoinPoint theJoinPoint, Object result){
		
		String theMethod = theJoinPoint.getSignature().toShortString();
		
		logger.info("\n===============> in @AfterReturning: calling method:"+theMethod);
		
		//display data returned
		logger.info("\n=================> Result:"+ result);
	}


}
