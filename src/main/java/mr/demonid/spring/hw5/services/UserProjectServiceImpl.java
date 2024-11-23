package mr.demonid.spring.hw5.services;

import lombok.AllArgsConstructor;
import mr.demonid.spring.hw5.domain.Project;
import mr.demonid.spring.hw5.domain.User;
import mr.demonid.spring.hw5.domain.UsersProject;
import mr.demonid.spring.hw5.exceptions.ElementNotFoundException;
import mr.demonid.spring.hw5.exceptions.ProjectAlreadyException;
import mr.demonid.spring.hw5.repositories.ProjectRepository;
import mr.demonid.spring.hw5.repositories.UserRepository;
import mr.demonid.spring.hw5.repositories.UsersProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class UserProjectServiceImpl implements UserProjectService {

    UsersProjectRepository usersProjectRepository;
    ProjectRepository projectRepository;
    UserRepository userRepository;


    /**
     * Возвращает список всех пользователей, связанных с заданным проектом
     * @param projectId Идентификатор проекта.
     */
    @Override
    public List<User> getUsersByProjectId(Long projectId) {
        List<User> res = new ArrayList<>();
        try {
            usersProjectRepository.findUserByProjectId(projectId)
                    .forEach(l -> res.add(userRepository.findById(l).orElseThrow(RuntimeException::new)));
            return res;
        } catch (Exception e) {
            throw new ElementNotFoundException("Нарушена целостность данных!",
                    "В связной таблице присутствует идентификатор несуществующего пользователя!");
        }
    }

    /**
     * Возвращает список проектов, связанных с заданным пользователем.
     * @param userId Идентификатор пользователя.
     */
    @Override
    public List<Project> getProjectsByUserId(Long userId) {
        List<Project> res = new ArrayList<>();
        try {
            usersProjectRepository.findProjectByUserId(userId)
                    .forEach(l -> res.add(projectRepository.findById(l).orElseThrow(RuntimeException::new)));
            return res;
        } catch (Exception e) {
            throw new ElementNotFoundException("Нарушена целостность данных!",
                    "В связной таблице присутствует идентификатор несуществующего проекта!");
        }
    }

    /**
     * Добавляем пользователя к проекту.
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     */
    @Override
    public void addUserToProject(Long userId, Long projectId) {
        List<UsersProject> chk = usersProjectRepository.findAllByUserIdAndProjectId(userId, projectId);
        if (chk != null && !chk.isEmpty()) {
            throw new ProjectAlreadyException("Нарушена целостность данных!",
                    "В базе данных уже есть связь пользователя с этим проектом!");
        }
        try {
            // Проверяем есть ли данные на пользователя и проект в БД?
            User user = userRepository.findById(userId).orElseThrow(() ->
                    new ElementNotFoundException("Пользователь не найден!", "Пользователь с ID " + userId + " не существует."));
            Project project = projectRepository.findById(projectId).orElseThrow(() ->
                    new ElementNotFoundException("Проект не найден!", "Проект с ID " + projectId + " не существует."));
            // Добавляем связь.
            UsersProject u = new UsersProject();
            u.setUserId(userId);
            u.setProjectId(projectId);
            usersProjectRepository.save(u);
        } catch (Exception e) {
            throw new ProjectAlreadyException("Непредвиденная ошибка!", e.getMessage());
        }
    }

    @Override
    public void removeUserFromProject(Long userId, Long projectId) {
        usersProjectRepository.deleteAll(usersProjectRepository.findAllByUserIdAndProjectId(userId, projectId));
    }


}
