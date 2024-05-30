package com.example.server.services;

import com.example.server.entity.Departments;
import com.example.server.entity.Position;
import com.example.server.entity.Requirements;
import com.example.server.repository.IDepartmentsRepository;
import com.example.server.repository.IPositionRepository;
import com.example.server.repository.IRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PositionServices {
    @Autowired
    IPositionRepository positionRepository;

    @Autowired
    IDepartmentsRepository departementsRepository;

    @Autowired
    IRequirementsRepository requirementsRepository;

    //This method return all positions
    public ArrayList<Position> getAllPositions(){
        return (ArrayList<Position>) positionRepository.findAll();
    }

    //This method return position by id
    public Optional<Position> getPositionById(Long id){
        return positionRepository.findById(id);
    }

    //This method save a position to the database
    public Position savePosition(String namePosition, Long departmentId) {
        Position position = new Position();
        position.setNamePosition(namePosition);
        position.setDepartment(findDepartments(departmentId));
        return positionRepository.save(position);
    }

    private Departments findDepartments(Long departmentId) {
        return departementsRepository.findById(departmentId).orElseThrow(() ->
                new RuntimeException("Department not found"));
    }

}
