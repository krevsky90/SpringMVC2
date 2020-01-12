package app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class LogAspect {
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        System.out.println("Invocation of " + jp.getSignature());
    }

    public Object aroundServiceMethodExecution(ProceedingJoinPoint jp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = jp.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution of method " + jp.getSignature() + " = " + (endTime - startTime) + ".msec");

        return result;
    }
}
