package com.jeanlima.mvcappdatajpa.service;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeanlima.mvcappdatajpa.model.Curso;
import com.jeanlima.mvcappdatajpa.repository.CursoRepository;
@Component
public class CursoServiceImpl implements CursoService{

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public Curso salvarCurso(Curso curso) {
        
        return cursoRepository.save(curso);
    }

    @Override
    public void deletarCurso(Curso curso) {
        cursoRepository.delete(curso);
        
    }

    @Override
    public Curso getCursoById(Integer id) {
        return cursoRepository.findById(id).map(curso -> {
            return curso;
        }).orElseThrow(() -> null);
    }

    @Override
    public List<Curso> getListaCurso() {
        return cursoRepository.findAll();
    }
    
}
    