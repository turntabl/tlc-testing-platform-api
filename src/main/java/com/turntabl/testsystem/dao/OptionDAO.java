package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.model.Option;
import com.turntabl.testsystem.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class OptionDAO {
    @Autowired
    private OptionRepository optionRepository;

    public OptionDAO(){}

    public OptionDAO(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public Option get(Long id){
        return optionRepository.findById(id).get();
    }
    public List<Option> addAll(List<Option> t){
        return optionRepository.saveAll(t);
    }
    public List<Option> getAll() {
        return optionRepository.findAll();
    }
    public Option add(Option option) {
        return optionRepository.save(option);
    }
}
