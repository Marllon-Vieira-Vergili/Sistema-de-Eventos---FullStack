package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**Camada DTO para enviar os tipos de dados por JSON**/
public record PartyRequest(

        @NotBlank(message = "O Título é obrigatório ")

        String title,

        @NotBlank(message = "O autor do evento é obrigatório")

String author,

@NotBlank(message = "A descrição do evento é obrigatória")

String description,

@NotNull(message = "O valor do orçamento é obrigatório")
@DecimalMin(value = "0.01", message = "O valor deve ser positivo")
@DecimalMax(value= "100000.00", message = "O preço não pode exceder R$ 100.000")

BigDecimal budget,

@NotBlank(message = "A imagem é obrigatória")


String image
) {

}

