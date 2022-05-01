package pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="resume")
public class Resume {
    @Id
    private Long id;
}