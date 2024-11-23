package mr.demonid.spring.hw5.services;

import lombok.AllArgsConstructor;
import mr.demonid.spring.hw5.domain.Project;
import mr.demonid.spring.hw5.domain.User;
import mr.demonid.spring.hw5.domain.UsersProject;
import mr.demonid.spring.hw5.exceptions.ElementNotFoundException;
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


    @Override
    public List<User> getUsersByProjectId(Long projectId) {
        List<User> res = new ArrayList<>();
        usersProjectRepository.findUserByProjectId(projectId)
                .forEach(l -> res.add(userRepository.findById(l).orElseThrow(() -> new ElementNotFoundException("Нарушена целостность таблиц!"))));
        return res;
    }

    @Override
    public List<Project> getProjectsByUserId(Long userId) {
        List<Project> res = new ArrayList<>();
        usersProjectRepository.findProjectByUserId(userId)
                .forEach(l -> res.add(projectRepository.findById(l).orElseThrow(() -> new ElementNotFoundException("Нарушена целостность таблиц!"))));
        return res;
    }

    @Override
    public void addUserToProject(Long userId, Long projectId) {
        List<UsersProject> chk = usersProjectRepository.findAllByUserIdAndProjectId(userId, projectId);
        if ( chk == null || chk.isEmpty())
        {
            UsersProject u = new UsersProject();
            u.setUserId(userId);
            u.setProjectId(projectId);
            usersProjectRepository.save(u);
        }
    }

    @Override
    public void removeUserFromProject(Long userId, Long projectId) {
        usersProjectRepository.deleteAll(usersProjectRepository.findAllByUserIdAndProjectId(userId, projectId));
    }


}
