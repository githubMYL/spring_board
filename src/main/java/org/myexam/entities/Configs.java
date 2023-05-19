package org.myexam.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Configs {
    @Id
<<<<<<< HEAD
     private String code;

    @Lob
    private String value;
=======
    @Column(length=45)
    private String code;

    @Lob
    private String value;

>>>>>>> adminpage
}
