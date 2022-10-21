package com.jeanlima.mvcappdatajpa.service;

import java.util.List;

import com.jeanlima.mvcappdatajpa.model.Curso;

public interface CursoService {
    public Curso salvarCurso(Curso curso);
    public void deletarCurso(Curso curso);
    public Curso getCursoById(Integer id);
    public List<Curso> getListaCurso();
}
