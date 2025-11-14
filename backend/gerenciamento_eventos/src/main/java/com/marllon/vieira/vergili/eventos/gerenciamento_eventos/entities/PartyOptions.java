package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "party_services")
public class PartyOptions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O nome do serviço da festa é obrigatório")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "O preço do serviço da festa é obrigatório")
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01", message = "O preço deve ser positivo")
    @DecimalMax(value= "100000.00", message = "O preço deve ser no máximo R$ 100.000")
    private BigDecimal price;

    @NotBlank(message = "A imagem é obrigatória")
    @Column(name = "image", nullable = false)
    private String image;


    @ManyToOne
    @JoinColumn(name = "party_id", nullable = true)
    private Party party;

}
