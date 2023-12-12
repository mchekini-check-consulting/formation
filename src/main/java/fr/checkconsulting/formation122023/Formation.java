package fr.checkconsulting.formation122023;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Formation extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            log.info("je suis l'element numéro {}", i);
        }
    }
}
