package pl.fg.futurum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Table
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Pole nie może być puste")
    private String name;
    @NotNull(message = "Pole nie może być puste")
    @Min(value = 10)
    private double bid;
    @NotNull(message = "Pole nie może być puste")
    private boolean status;
    @NotNull(message = "Pole nie może być puste")
    private Town town;
    @NotNull(message = "Pole nie może być puste")
    @Min(value = 1)
    private double radius;
    @NotEmpty(message = "Pole nie może być puste")
    private String keywords;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User seller;


    public Campaign() {
        this.keywords="Slowa kluczowe";
    }

    public Campaign(String name, double bid, boolean status, Town town, double radius, User seller) {
        this.name = name;
        this.bid = bid;
        this.status = status;
        this.town = town;
        this.radius = radius;
        this.seller = seller;
        this.keywords="Slowa kluczowe";
    }
    public Campaign(String name, double bid, boolean status, Town town, double radius) {
        this.name = name;
        this.bid = bid;
        this.status = status;
        this.town = town;
        this.radius = radius;
        this.keywords="Slowa kluczowe";
    }
}
