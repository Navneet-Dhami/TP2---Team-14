package SoundwavesProject.Soundwaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SoundwavesProject.Soundwaves.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {
}
