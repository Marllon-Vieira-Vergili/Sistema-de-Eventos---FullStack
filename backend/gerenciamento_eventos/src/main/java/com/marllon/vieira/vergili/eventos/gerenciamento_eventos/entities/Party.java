package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "party")

public class Party implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O Título é obrigatório ")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "O autor do evento é obrigatório")
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank(message = "A descrição do evento é obrigatória")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "O valor do orçamento é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser positivo")
    @DecimalMax(value= "100000.00", message = "O preço não pode exceder R$ 100.000")
    @Column(name = "budget", nullable = false)
    private BigDecimal budget;

    @NotBlank(message = "A imagem é obrigatória")
    @Column(name = "image", nullable = false)
    private String image;


    /*Relacionando com a lista de serviços*/
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartyOptions> services = new ArrayList<>();


    //Métodos para adicionar e remover serviços

    public void addServiceParty(PartyOptions service){

        services.add(service);
        service.setParty(this);
    }

    //Remover um serviço da festa
    public void removeServiceParty(PartyOptions service){
        this.services.remove(service);
    }
}
