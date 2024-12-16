package com.example.login.controller;

import com.example.login.common.ERole;
import com.example.login.common.JwtUtils;
import com.example.login.dto.JwtResponse;
import com.example.login.dto.LoginRequest;
import com.example.login.dto.MessageResponse;
import com.example.login.dto.SignupRequest;
import com.example.login.model.entity.Role;
import com.example.login.model.entity.User;
import com.example.login.model.repository.RoleRepository;
import com.example.login.model.repository.UserRepository;
import com.example.login.model.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getPhoneNumber(),
                userDetails.getAddress(),
                roles,
                userDetails.getAllowAccess()
        ));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username already taken:"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email already is use!"));
        }

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getPhoneNumber(),
                signupRequest.getAddress(),
                "valid"
        );

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));

    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout() {
        // Không cần thêm logic gì vì JWT không có trạng thái
        return ResponseEntity.ok(new MessageResponse("User logged out successfully"));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update-allow-access")
    public ResponseEntity<MessageResponse> updateAllowAccess(
            @RequestParam("id") Long userId,
            @RequestParam("allowAccess") String newAllowAccess) {

        // Chỉ chấp nhận giá trị hợp lệ
        if (!"Valid".equalsIgnoreCase(newAllowAccess) && !"Unvalid".equalsIgnoreCase(newAllowAccess)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Invalid value for allowAccess. Allowed values: 'true', 'false'."));
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found"));

        user.setAllowAccess(newAllowAccess);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User allowAccess updated successfully"));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId) {
        try {
            // Tìm user theo id
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Error: User not found"));

            // Trả về thông tin user
            return ResponseEntity.ok(user);
        } catch (RuntimeException ex) {
            // Xử lý nếu không tìm thấy user
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + ex.getMessage()));
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @RequestBody User updatedUser) {
        try {
            // Tìm user theo id
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Error: User not found"));

            // Cập nhật thông tin
            if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
                if (!user.getUsername().equals(updatedUser.getUsername())
                        && userRepository.existsByUsername(updatedUser.getUsername())) {
                    return ResponseEntity.badRequest()
                            .body(new MessageResponse("Error: Username already taken!"));
                }
                user.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
                if (!user.getEmail().equals(updatedUser.getEmail())
                        && userRepository.existsByEmail(updatedUser.getEmail())) {
                    return ResponseEntity.badRequest()
                            .body(new MessageResponse("Error: Email already in use!"));
                }
                user.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getFirstName() != null) {
                user.setFirstName(updatedUser.getFirstName());
            }

            if (updatedUser.getLastName() != null) {
                user.setLastName(updatedUser.getLastName());
            }

            if (updatedUser.getPhoneNumber() != null) {
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }

            if (updatedUser.getAddress() != null) {
                user.setAddress(updatedUser.getAddress());
            }

            if (updatedUser.getAllowAccess() != null) {
                user.setAllowAccess(updatedUser.getAllowAccess());
            }

            if (updatedUser.getAvatar() != null) {
                user.setAvatar(updatedUser.getAvatar());
            }

            if (updatedUser.getPublicId() != null) {
                user.setPublicId(updatedUser.getPublicId());
            }

            if (updatedUser.getSignature() != null) {
                user.setSignature(updatedUser.getSignature());
            }

            // Lưu thay đổi
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User updated successfully"));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + ex.getMessage()));
        }
    }

    @PutMapping("/user/{id}/change-password")
    public ResponseEntity<?> changePassword(
            @PathVariable("id") Long userId,
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword) {
        try {
            // Tìm user theo id
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Error: User not found"));

            // Kiểm tra mật khẩu hiện tại
            if (!encoder.matches(currentPassword, user.getPassword())) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("Error: Current password is incorrect"));
            }

            // Đặt mật khẩu mới
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Password updated successfully"));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + ex.getMessage()));
        }
    }



}
