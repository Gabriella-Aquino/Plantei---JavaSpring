package br.com.gabriella.plantei.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "garden")
public class Garden {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "garden_id")
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "admin_user_id")
  private User admin;

  @OneToMany(mappedBy = "garden")
  private List<PlantUser> plantUsers;

  @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<GardenMember> members;

  @CreationTimestamp 
  @Column(name = "created_at" ,updatable = false)
  private LocalDateTime createdAt;
}
