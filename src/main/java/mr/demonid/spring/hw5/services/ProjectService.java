package mr.demonid.spring.hw5.services;

import mr.demonid.spring.hw5.domain.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProjectById(Long id);

    Project addProject(Project project);

    void deleteProject(Long id);
}
