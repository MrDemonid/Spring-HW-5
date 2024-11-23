package mr.demonid.spring.hw5.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class EntityWithRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long relationEntityId;
}

/*
Создайте абстрактный класс "сущность с связью" (EntityWithRelation),
который будет содержать следующие поля:
- Идентификатор (id) типа Long, генерируемый автоматически при создании записи
- Идентификатор связанной сущности (relatedEntityId) типа Long

 */