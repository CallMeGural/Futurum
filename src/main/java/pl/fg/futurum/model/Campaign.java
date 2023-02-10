package pl.fg.futurum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @Min(value = 20000)
    private double bid;
    @NotNull
    private boolean status;
    @NotNull
    private Town town;
    @NotNull
    private double radius;
    @NotNull
    private String keywords;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @PrePersist
    void keywords() {this.keywords=this.name;}
}
