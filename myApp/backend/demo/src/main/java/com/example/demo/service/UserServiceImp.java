package com.example.demo.service;
import com.example.demo.entities.Project;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository,ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
		this.projectRepository = projectRepository;
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User retrieveUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User retrieveUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    @Override
    public List <User> findUserByroleandusername(String username, String roleName) {
        return userRepository.findByUsernameAndRoleName(username, roleName);
    }
    

    
    @Override
    public List<Role> retrieveAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role retrieveRole(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
    @Override
    public void addRoleToUser(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        if (user != null && role != null) {
            user.setRole(role);
             userRepository.save(user);
             role.getUsers().add(user);
             roleRepository.save(role);
        }

        
    }
    @Override
    public void deleteRoleFromUser(Integer userId,Integer roleId)
    {
    	User user = userRepository.findById(userId).orElse(null);
    	Role role = roleRepository.findById(roleId).orElse(null);
    	if (user != null && role != null) {
            user.setRole(null);
             userRepository.save(user);
             role.getUsers().remove(user);
             roleRepository.save(role);
        }
    	
    }
   

    
    @Override
    public Role retrieveRoleFromUser(Integer userId) {
    	
    	User user = userRepository.findById(userId).orElse(null);
    	if (user != null ) {
    		return user.getRole();
    	}
    	return null;
    }
    
    @Override
    public List<Project> retrieveAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project retrieveProject(Integer id) {
        return projectRepository.findById(id).orElse(null);
    }
    @Override
    public void addProjectToUser(Integer userId, Integer projectId) {
        User user = userRepository.findById(userId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);
        if (user != null && project != null) {
            user.getProjects().add(project);
            userRepository.save(user);
            project.setUser(user);
            projectRepository.save(project);
        }
    }
    @Override
    public void deleteProjectFromUser(Integer userId, Integer projectId) {
        User user = userRepository.findById(userId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);
        if (user != null && project != null) {
            user.getProjects().remove(project);
            userRepository.save(user);
            project.setUser(null);
            projectRepository.save(project);
        }
    }


   
}