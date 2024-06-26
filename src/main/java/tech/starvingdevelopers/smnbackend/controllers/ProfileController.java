package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.profile.input.UpdateProfileDTO;
import tech.starvingdevelopers.smnbackend.models.dto.profile.output.GetProfileDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;
import tech.starvingdevelopers.smnbackend.services.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetProfileDTO> getProfile(@PathVariable String username) {
        Profile profile = this.profileService.getProfile(username);
        return ResponseEntity.ok(GetProfileDTO.toGetProfileDTO(profile));
    }

    @PostMapping("/update")
    public ResponseEntity<GetProfileDTO> updateProfile(@RequestBody @Validated UpdateProfileDTO updateProfileDTO) {
        Profile profile = this.profileService.updateProfile(updateProfileDTO);
        System.out.println(profile.toString());
        return ResponseEntity.ok(GetProfileDTO.toGetProfileDTO(profile));
    }
}
