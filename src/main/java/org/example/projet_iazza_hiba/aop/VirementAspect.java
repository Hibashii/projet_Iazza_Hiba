package org.example.projet_iazza_hiba.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class VirementAspect {

    private static final org.slf4j.Logger VIREMENT_LOGGER =
            org.slf4j.LoggerFactory.getLogger("VIREMENT_LOGGER");

    @AfterReturning(
            value = "execution(* org.example.projet_iazza_hiba.service.CompteService.virement(..)) && args(fromId, toId, amount)",
            argNames = "joinPoint,fromId,toId,amount")
    public void logVirement(JoinPoint joinPoint, Long fromId, Long toId, double amount) {

        String message = "VIREMENT | FROM=" + fromId +
                " | TO=" + toId +
                " | AMOUNT=" + amount;

        VIREMENT_LOGGER.info(message);
    }
}

