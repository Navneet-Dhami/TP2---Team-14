package SoundwavesProject.Soundwaves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Category;
import SoundwavesProject.Soundwaves.repository.categoriesRepository;

@Service
public class categoriesService {

    @Autowired
    categoriesRepository categoriesRepository;
     
    public List<Category> getAllCategory(){
        return categoriesRepository.findAll();
    }
   
}