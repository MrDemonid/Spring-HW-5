package mr.demonid.spring.hw5.controllers;

import lombok.AllArgsConstructor;
import mr.demonid.spring.hw5.domain.Project;
import mr.demonid.spring.hw5.domain.User;
import mr.demonid.spring.hw5.exceptions.ElementNotFoundException;
import mr.demonid.spring.hw5.services.UserProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserProjectController {

    UserProjectService service;

    /**
     * Возвращает список пользователей, связанных с определенным проектом.
     * @param projectId Идентификатор проекта.
     */
    @GetMapping("/users-by-project/{id}")
    public ResponseEntity<List<User>> getUsersByProjectId(@PathVariable(name = "id") Long projectId) {
        try {
            List<User> res = service.getUsersByProjectId(projectId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ElementNotFoundException e)
        {
            // в связной таблице есть ID не существующих пользователей
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Возвращает список проектов, в которых задействован пользователь.
     * @param userId Идентификатор пользователя.
     */
    @GetMapping("projects-by-user/{id}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable(name = "id") Long userId) {
        try {
            List<Project> res = service.getProjectsByUserId(userId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ElementNotFoundException e)
        {
            // в связной таблице есть ID не существующих пользователей
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Добавление пользователя к проекту.
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     */
    @PostMapping("/add-user-to-project")
    public ResponseEntity addUserToProject(@RequestParam(value = "user") Long userId,
                                           @RequestParam(value = "project") Long projectId) {
        service.addUserToProject(userId, projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удаляет пользователя из проекта.
     * @param userId    Идентификатор пользователя.
     * @param projectId Идентификатор проекта.
     */
    @PostMapping("/remove-user-from-project/{userId}/{projectId}")
    public ResponseEntity removeUserFromProject(@PathVariable Long userId, Long projectId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

