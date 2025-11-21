package br.com.gabriella.plantei.model;

import java.time.LocalDateTime;

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
@Table(name = "garden")
public class Garden {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "garden_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "admin_user_id")
  private User admin;

  @CreationTimestamp 
  @Column(updatable = false)
  private LocalDateTime createdAt;
}
