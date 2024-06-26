package tech.starvingdevelopers.smnbackend.services;

import org.springframework.cache.annotation.Cacheable;
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

    public void createProfile(String username, String nickname) {
        this.profileRepository.save(new Profile(username, nickname));
    }

    @Cacheable(value = "profile", key = "#username")
    public Profile getProfile(String username) {
        Optional<Profile> profile = this.profileRepository.findByUsername(username);
        if (profile.isEmpty())
            throw new ProfileNotFound("Profile not found! (" + username + ")");

        return profile.get();
    }

    @Cacheable(value = "profile", key = "#updateProfileDTO.username()")
    public Profile updateProfile(UpdateProfileDTO updateProfileDTO) {
        Optional<Profile> profile = this.profileRepository.findByUsername(updateProfileDTO.username());
        if (profile.isEmpty())
            throw new ProfileNotFound("Profile not found! (" + updateProfileDTO.username() + ")");

        if (updateProfileDTO.nickname() != null)
            profile.get().setNickname(updateProfileDTO.nickname());

        if (updateProfileDTO.description() != null)
            profile.get().setDescription(updateProfileDTO.description());

        if (updateProfileDTO.profileImage() != null)
            profile.get().setProfileImage(updateProfileDTO.profileImage());

        return this.profileRepository.save(profile.get());
    }
}
