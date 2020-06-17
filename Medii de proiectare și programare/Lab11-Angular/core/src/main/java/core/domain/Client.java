package core.domain;


import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long>{
    private int id2;
    private String name;
    private long phoneNumber;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales = new ArrayList<>();

}
