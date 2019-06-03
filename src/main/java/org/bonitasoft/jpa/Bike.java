package org.bonitasoft.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BIKE")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bike implements Serializable {

    @Id
    @GeneratedValue
    private Long persistenceId;
    
    @ElementCollection(fetch=FetchType.LAZY)
    private List<Wheel> wheels;
    
}
