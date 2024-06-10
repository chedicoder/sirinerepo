package com.example.demo.controllers;


import com.example.demo.dto.RegisterDto;
import com.example.demo.entities.Project;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JWTGenerator;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
	
	 private UserRepository userRepository;
	    private RoleRepository roleRepository;
	    private  ProjectRepository projectRepository;
	    private PasswordEncoder passwordEncoder;
	    private final UserService userService;

    @Autowired
    public UserController(UserService userService,UserRepository userRepository,RoleRepository roleRepository,
    		PasswordEncoder passwordEncoder,JWTGenerator jwtGenerator,ProjectRepository projectRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
        this.projectRepository=projectRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.retrieveUser(id);
    }
    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return userService.retrieveUser(username);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return userService.retrieveAllRoles();
    }

    @PostMapping("/roles")
    public Role addRole(@RequestBody Role role) {
        return userService.addRole(role);
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable Integer id) {
        userService.deleteRole(id);
    }

    @PutMapping("/roles")
    public Role updateRole(@RequestBody Role role) {
        return userService.updateRole(role);
    }

    @GetMapping("/roles/{id}")
    public Role getRole(@PathVariable Integer id) {
        return userService.retrieveRole(id);
    }

    @PostMapping("/test/{userId}/roles/{roleId}")
    public void addRoleToUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
         userService.addRoleToUser(userId, roleId);
    }
    @DeleteMapping("/{userId}/deleteRole/{roleId}")
    public void deleteRoleFromUser(@PathVariable Integer userId, @PathVariable Integer roleId) 
    {
    	userService.deleteRoleFromUser(userId, roleId);
    }
    @GetMapping("/{userId}/roles")
    public Role getRoleuser(@PathVariable Integer userId)
    {
    	return userService.retrieveRole(userId);
    }
    @GetMapping("/users/{username}/{roleName}")
    public List <User> getAllusersbyrole(@PathVariable String username, @PathVariable String roleName) {
        return userService.findUserByroleandusername(username, roleName);
    }

    
    //add user with crypted password
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email is used!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setFirst_Name(registerDto.getFirst_Name());
        user.setLast_Name(registerDto.getLast_Name());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setPhone_Number(registerDto.getPhone_Number());
        user.setIsactive(true);
        user.setIsapproved(true);
        user.setCreated_at(new java.sql.Date(System.currentTimeMillis()));

        if ("Client".equals(registerDto.getRole())) {
        	
            Role role = roleRepository.findById(3).orElse(null);
                user.setRole(role);
                 userRepository.save(user);
                 role.getUsers().add(user);
                 roleRepository.save(role);
                 return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
            
        } else if ("Consultant".equals(registerDto.getRole())) {
        	Role role = roleRepository.findById(2).orElse(null);
            user.setRole(role);
             userRepository.save(user);
             role.getUsers().add(user);
             roleRepository.save(role);
             return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        
        } else if ("Administrator".equals(registerDto.getRole())) {
        	Role role = roleRepository.findById(1).orElse(null);
            user.setRole(role);
             userRepository.save(user);
             role.getUsers().add(user);
             roleRepository.save(role);
             return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
        
        }


        return new ResponseEntity<>("User not registred!", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/projects") 
    public List<Project> getAllProjects() {
        return userService.retrieveAllProjects(); 
    }

    @PostMapping("/projects") 
    public Project addProject(@RequestBody Project project) {
        return userService.addProject(project);
    }

    @DeleteMapping("/projects/{id}") 
    public void deleteProject(@PathVariable Integer id) {
        userService.deleteProject(id); 
    }

    @PutMapping("/projects") 
    public Project updateProject(@RequestBody Project project) {
        return userService.updateProject(project); 
    }
    
    @PostMapping("/{userId}/addProject/{projectId}")
    public void addProjectToUser(@PathVariable Integer userId, @PathVariable Integer projectId) {
        userService.addProjectToUser(userId, projectId); 
    }

    @DeleteMapping("/{userId}/deleteProject/{projectId}")
    public void deleteProjectFromUser(@PathVariable Integer userId, @PathVariable Integer projectId) {
        userService.deleteProjectFromUser(userId, projectId); 
       
    }
    
}  
