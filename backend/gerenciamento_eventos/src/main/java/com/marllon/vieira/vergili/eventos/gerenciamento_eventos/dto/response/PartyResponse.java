package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response;

import java.math.BigDecimal;

public record PartyResponse(

        Long id,
        String title,
        String author,
        String description,
        BigDecimal budget,
        String image

) {
}


