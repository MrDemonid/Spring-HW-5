package mr.demonid.spring.hw5.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users_projects")
public class UsersProject extends EntityWithRelation {

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}

/*
Создайте сущность "пользователи проекта" (UsersProject), которая наследуется
от класса "сущность с связью" и будет содержать следующие поля:
- Идентификатор проекта (projectId) типа Long
- Идентификатор пользователя (userId) типа Long

 */