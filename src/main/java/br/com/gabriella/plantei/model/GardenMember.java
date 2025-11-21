package br.com.gabriella.plantei.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "garden_member")
public class GardenMember {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "garden_member_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "garden_id")
  private Garden garden;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @CreationTimestamp 
  @Column(name = "entry_date", updatable = false)
  private LocalDate entryDate;
}
