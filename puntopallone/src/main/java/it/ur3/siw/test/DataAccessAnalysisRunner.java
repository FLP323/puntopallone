package it.ur3.siw.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Profile("test")
public class DataAccessAnalysisRunner implements CommandLineRunner {

    private final SquadraTestService testService;

    public DataAccessAnalysisRunner(SquadraTestService testService) {
        this.testService = testService;
    }

    @Override
    public void run(String... args) {
        Long squadraId = 1L;
        StopWatch sw = new StopWatch("Analisi accesso dati - Dettaglio Squadra");
        int ripetizioni = 3;

        sw.start("LAZY (default)");
        for (int i = 0; i < ripetizioni; i++) {
            testService.testLazy(squadraId);
        }
        sw.stop();

        sw.start("JOIN FETCH");
        for (int i = 0; i < ripetizioni; i++) {
            testService.testJoinFetch(squadraId);
        }
        sw.stop();

        sw.start("EntityGraph");
        for (int i = 0; i < ripetizioni; i++) {
            testService.testEntityGraph(squadraId);
        }
        sw.stop();

        System.out.println(sw.prettyPrint());
    }
}