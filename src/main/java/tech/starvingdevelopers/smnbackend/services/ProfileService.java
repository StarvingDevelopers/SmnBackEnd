package tech.starvingdevelopers.smnbackend.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.profile.ProfileNotFound;
import tech.starvingdevelopers.smnbackend.models.dto.profile.input.UpdateProfileDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;
import tech.starvingdevelopers.smnbackend.models.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void createProfile(String username, String nickname, String searchableName) {
        this.profileRepository.save(new Profile(username, nickname, searchableName));
    }

    public Profile getProfile(String username) {
        Optional<Profile> profile = this.profileRepository.findByUsername(username);
        if (profile.isEmpty())
            throw new ProfileNotFound("Profile not found! (" + username + ")");

        return profile.get();
    }

    @Transactional
    public Profile updateProfile(UpdateProfileDTO updateProfileDTO) {
        Optional<Profile> profile = this.profileRepository.findByUsername(updateProfileDTO.username());
        if (profile.isEmpty())
            throw new ProfileNotFound("Profile not found! (" + updateProfileDTO.username() + ")");

        if (updateProfileDTO.nickname() != null && !updateProfileDTO.nickname().isEmpty())
            profile.get().setNickname(updateProfileDTO.nickname());

        if (updateProfileDTO.description() != null && !updateProfileDTO.description().isEmpty())
            profile.get().setDescription(updateProfileDTO.description());

        if (updateProfileDTO.profileImage() != null &&!updateProfileDTO.profileImage().isEmpty())
            profile.get().setProfileImage(updateProfileDTO.profileImage());

        return this.profileRepository.save(profile.get());
    }
}
