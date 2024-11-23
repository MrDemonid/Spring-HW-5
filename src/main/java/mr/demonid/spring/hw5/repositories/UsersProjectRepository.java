package mr.demonid.spring.hw5.repositories;

import mr.demonid.spring.hw5.domain.UsersProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {

    /**
     * Выборка всех задействованных в проекте пользователей.
     * @param projectId Идентификатор проекта
     * @return Список Id пользователей.
     */
    @Query("SELECT u.userId FROM UsersProject u WHERE u.projectId = :projectId")
    List<Long> findUserByProjectId(Long projectId);

    /**
     * Выборка всех проектов, в которых участвует пользователь.
     * @param userId Идентификатор пользователя
     * @return Список Id проектов пользователя.
     */
    @Query("SELECT u.projectId FROM UsersProject u WHERE u.userId = :userId")
    List<Long> findProjectByUserId(Long userId);

    /**
     * Возвращает записи проектов пользователей.
     * @param userId
     * @param projectId
     * @return
     */
    @Query("SELECT u FROM UsersProject u WHERE u.userId = :userId AND u.projectId = :projectId")
    List<UsersProject> findAllByUserIdAndProjectId(Long userId, Long projectId);

}
