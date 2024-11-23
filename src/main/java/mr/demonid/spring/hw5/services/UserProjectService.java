package mr.demonid.spring.hw5.services;

import mr.demonid.spring.hw5.domain.Project;
import mr.demonid.spring.hw5.domain.User;

import java.util.List;


public interface UserProjectService {

    /**
     * Возвращает список пользователей, связанных с определенным проектом.
     * @param projectId Идентификатор проекта.
     */
    List<User> getUsersByProjectId(Long projectId);

    /**
     * Возвращает список проектов, в которых задействован пользователь.
     * @param userId Идентификатор пользователя.
     */
    List<Project> getProjectsByUserId(Long userId);

    /**
     * Добавляет пользователя к проекту.
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     */
    void addUserToProject(Long userId, Long projectId);

    /**
     * Удаляет пользователя из проекта.
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     */
    void removeUserFromProject(Long userId, Long projectId);


}
