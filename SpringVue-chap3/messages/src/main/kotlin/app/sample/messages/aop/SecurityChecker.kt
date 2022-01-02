package app.sample.messages.aop

import org.apache.logging.log4j.LogManager
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class SecurityChecker{
    private val logger = LogManager.getLogger(SecurityChecker::class.java)

    @Pointcut("@annotation(app.sample.messages.aop.SecurityCheck)")
    fun checkMethodSecurity() {}

    @Around("checkMethodSecurity()")
    fun checkSecurity(joinPoint: ProceedingJoinPoint): Any{
        logger.info("checking security before join point")
        val result = joinPoint.proceed()
        logger.info("checking security after join point")
        return result
    }
}
