package SoundwavesProject.Soundwaves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoundwavesProject.Soundwaves.model.Updates;


@Repository
public interface UpdatesRepository extends JpaRepository<Updates, Long> {

    List<Updates> findTop10ByOrderByIdDesc();
    
}
