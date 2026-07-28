package com.project.rentalcar.controller;

import com.project.rentalcar.common.payload.Response;
import com.project.rentalcar.model.dto.request.ChangeEmailRequest;
import com.project.rentalcar.model.dto.request.ChangePasswordRequest;
import com.project.rentalcar.model.dto.request.ChangePhoneRequest;
import com.project.rentalcar.model.dto.request.UserProfileUpdateRequest;
import com.project.rentalcar.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        return ResponseEntity.ok(Response.ofSucceeded(userService.getProfile(authentication)));
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @Valid @RequestBody UserProfileUpdateRequest request,
            Authentication authentication) {
        return ResponseEntity.ok(Response.ofSucceeded(userService.updateProfile(request, authentication)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(Response.ofSucceeded(userService.getUserById(id)));
    }

    @PutMapping(value = "/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {
        return ResponseEntity.ok(Response.ofSucceeded(userService.updateAvatar(file, authentication)));
    }

    @DeleteMapping("/avatar")
    public ResponseEntity<?> deleteAvatar(Authentication authentication) {
        userService.deleteAvatar(authentication);
        return ResponseEntity.ok(Response.ofSucceeded("Avatar deleted successfully"));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication) {
        userService.changePassword(request, authentication);
        return ResponseEntity.ok(Response.ofSucceeded("Password changed successfully"));
    }

    @PutMapping("/change-email")
    public ResponseEntity<?> changeEmail(
            @Valid @RequestBody ChangeEmailRequest request,
            Authentication authentication) {
        userService.changeEmail(request, authentication);
        return ResponseEntity.ok(Response.ofSucceeded("Email changed successfully"));
    }

    @PutMapping("/change-phone")
    public ResponseEntity<?> changePhone(
            @Valid @RequestBody ChangePhoneRequest request,
            Authentication authentication) {
        userService.changePhone(request, authentication);
        return ResponseEntity.ok(Response.ofSucceeded("Phone number changed successfully"));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount(Authentication authentication) {
        userService.deleteAccount(authentication);
        return ResponseEntity.ok(Response.ofSucceeded("User deleted successfully"));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(Authentication authentication) {
        return ResponseEntity.ok(Response.ofSucceeded(userService.getDashboard(authentication)));
    }
}