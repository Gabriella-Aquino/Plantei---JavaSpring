package br.com.gabriella.plantei.model;

import java.time.LocalDateTime;

import br.com.gabriella.plantei.model.enums.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "event_plant")
@Data
public class EventPlant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_plant_id")
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "plant_user_id")
  private PlantUser plantUser;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(name = "event_type")
  private EventType type;

  @Column(columnDefinition = "text")
  private String description;

  @Column(name = "event_date")
  private LocalDateTime eventDate;
}
