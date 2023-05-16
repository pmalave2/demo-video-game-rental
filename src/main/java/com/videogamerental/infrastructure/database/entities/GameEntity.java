package com.videogamerental.infrastructure.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@NamedEntityGraph(name = "Game.properties", attributeNodes = @NamedAttributeNode("properties"))
public class GameEntity {
  @Id private UUID id;
  private String name;
  @OneToOne private GamePropertiesEntity properties;
}
