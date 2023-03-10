package fontys.s3.carspacebackend.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="s3carspace_auction_image")
public class ImageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="img_url", nullable = false)
    private String imgUrl;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="auction_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private AuctionEntity auction;
}
