package SoundwavesProject.Soundwaves.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import SoundwavesProject.Soundwaves.model.Category;

public interface categoriesRepository extends JpaRepository<Category, Integer> {
    
}
