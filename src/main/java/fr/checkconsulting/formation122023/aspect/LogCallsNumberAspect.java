package fr.checkconsulting.formation122023.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogCallsNumberAspect {

    static Map<String, Integer> callsNumber = new HashMap<>();

    @Around("execution(* *(..)) && @annotation(LogCallsNumber)")
    public Object logCallsNumberAspect(ProceedingJoinPoint joinPoint) throws Throwable {


        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Object result = joinPoint.proceed();

        if (callsNumber.get(signature.getMethod().getName()) != null){
            callsNumber.put(signature.getMethod().getName(), callsNumber.get(signature.getMethod().getName()) + 1);
        }
        else {
            callsNumber.put(signature.getMethod().getName(), 1);
        }

        log.info("le nombre d'appels à la méthode {} = {} fois", signature.getMethod().getName(), callsNumber.get(signature.getMethod().getName()));


        return result;

    }
}
