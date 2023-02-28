package com.newfood.delivery.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Photo {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId  // esta sendo mapeado atraves do id de produto
    private Product product;

    private String fileName;
    private String description;
    private String contentType;
    private Long size;

}
