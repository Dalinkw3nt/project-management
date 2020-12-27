package com.chasetheblack.pma.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.chasetheblack.pma.controllers..*)")
	public void definePackagePointcuts() {
		//empty method just to name the location specified in the PointCut
	}
	
	@Before("definePackagePointcuts()")
	public void log() {
		log.debug(" ----------------------------------- ");
	}
	
}
