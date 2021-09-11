package br.com.techpib.ap.ms_anotacoes_ebd.adapter.outbound.persistence.entities.enums;

public enum StatusEnum {

    NOVA(1, "NOVA"),
    ALTERADA(2, "ALTERADA"),
    EXCLUIDA(3, "EXCLUIDA"),
    A_EXPURGAR(4, "A EXPURGAR");

    public Integer idStatus;
    public String status;

    StatusEnum(Integer idStatus, String status) {
        this.idStatus = idStatus;
        this.status = status;
    }
}
