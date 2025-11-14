package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response;

import java.math.BigDecimal;

public record PartyOptionsResponse(

        Long id,
        String name,
        String description,
        BigDecimal price,
        String image
) {
}


