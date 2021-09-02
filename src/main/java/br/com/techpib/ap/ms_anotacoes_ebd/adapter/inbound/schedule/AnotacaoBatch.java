package br.com.techpib.ap.ms_anotacoes_ebd.adapter.inbound.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class AnotacaoBatch {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    //Schedule
    //1 - Todos os dias: as 01:00, muda as anotações EXCLUIDA para A_EXPURGAR
    //2 - Toda sexta-feira as 02:00, exclui de fato o que for A_EXPURGAR de ATÉ uma semana atrás

    @Scheduled(cron = "0 0 1 ? * *", zone = TIME_ZONE)
    public void criarAnotacaoAExpurgar() {
        System.out.println(LocalDateTime.now());
        // TODO
    }

    @Scheduled(cron = "0 0 2 ? * THU", zone = TIME_ZONE)
    public void excluirAnotacoes() {
        System.out.println(LocalDateTime.now());
        // TODO
    }

}
