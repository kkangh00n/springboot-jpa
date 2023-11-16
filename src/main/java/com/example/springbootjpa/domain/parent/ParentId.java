package com.example.springbootjpa.domain.parent;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
public class ParentId implements Serializable {
    private String id1;
    private String id2;

}
