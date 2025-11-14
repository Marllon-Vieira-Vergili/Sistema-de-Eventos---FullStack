package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;



/**Camada DTO para enviar os tipos de dados por JSON**/
public record PartyOptionsRequest(

        @NotBlank(message = "O nome do serviço da festa é obrigatório")

        String name,

                @NotBlank(message = "A descrição é obrigatória")

String description,

@NotNull(message = "O preço do serviço da festa é obrigatório")

@DecimalMin(value = "0.01", message = "O preço deve ser positivo")
@DecimalMax(value= "100000.00", message = "O preço deve ser no máximo R$ 100.000")
BigDecimal price,

@NotBlank(message = "A imagem é obrigatória")


String image
) {
}
