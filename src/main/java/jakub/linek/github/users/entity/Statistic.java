package jakub.linek.github.users.entity;

import lombok.Data;

import javax.persistence.*;

@Entity()
@Data
public class Statistic {
    @Id
    @Column(unique = true)
    private String login;

    @Column(name = "request_count")
    private Long requestCount;
}
