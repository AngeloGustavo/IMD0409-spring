package com.jeanlima.mvcappdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeanlima.mvcappdatajpa.model.Curso;
import com.jeanlima.mvcappdatajpa.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;


    @RequestMapping("/showForm")
    public String showFormCurso(Model model){

        model.addAttribute("curso", new Curso());
        return "curso/formCurso";
    }

    @RequestMapping("/addCurso")
    public String showFormCurso(@ModelAttribute("curso") Curso curso,  Model model){

        Curso novoCurso = cursoService.salvarCurso(curso);


        model.addAttribute("curso", novoCurso);
        return "curso/paginaCurso";
    }

    @RequestMapping("/getListaCursos")
    public String showListaCurso(Model model){

        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos",cursos);
        return "curso/listaCursos";

    }

    @GetMapping("/getCursoById/{id}")
    public String getCursoById( @PathVariable Integer id, Model model){

        Curso curso = cursoService.getCursoById(id);
        model.addAttribute("curso",curso);
        return "curso/paginaCurso";
    }
  
    @RequestMapping(value = "/deletar/{id}")
    public String deleteCurso(@PathVariable String id, Model model){
        int paramId = Integer.parseInt(id);
        Curso curso = cursoService.getCursoById(paramId);
        cursoService.deletarCurso(curso);
        model.addAttribute("curso",curso);
        return "curso/confirmDeletion";
    }
}
