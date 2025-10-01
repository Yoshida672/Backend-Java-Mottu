package br.com.fiap.monitoramentomottu.dto.request;

import br.com.fiap.monitoramentomottu.dto.anotacao.ExistsId;
import br.com.fiap.monitoramentomottu.entity.Filial;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PatioRequest(
        Long id,

        @Positive(message = "A área do pátio deve ser um valor positivo")
        int area,

        @Positive(message = "A capacidade máxima deve ser um valor positivo")
        int capacidadeMax,

        @Positive(message = "O ID da filial deve ser um número positivo")
        @ExistsId(entity = Filial.class, message = "Filial não existe")
        Long filialId

        ) {

}
