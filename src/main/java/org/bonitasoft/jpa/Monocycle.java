package org.bonitasoft.jpa;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="MONOCYCLE")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Monocycle implements Serializable{

        @Id
        @GeneratedValue
        private Long persistenceId;
        
        private int size;
        
        @Embedded
        private Wheel wheel;
    
}
