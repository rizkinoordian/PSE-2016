package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentService;


    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa);
        studentService.addStudent (student);
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentService.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentService.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentService.selectAllStudents ();
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	if(studentService.selectStudent(npm) == null){
    		return "not-found";
    	}
        studentService.deleteStudent (npm);
        return "delete";
    }
    
    @GetMapping("/student/update/{npm}")
    public String updateForm (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentService.selectStudent(npm);
    	
    	if(student == null){
    		model.addAttribute("npm", npm);
    		return "not-found";
    	}
    	model.addAttribute("student", student);
    	return "form-update";
    }
    
    @PostMapping("/student/update")
    public String updateSubmit(@ModelAttribute StudentModel student)
    {
    	studentService.updateStudent(student);
    	
    	return "success-update";
    }
    
//    @RequestMapping("/student/update/{npm}")
//    public String update (Model model, @PathVariable(value = "npm") String npm)
//    {
//    	StudentModel student = studentService.selectStudent(npm);
//    	
//    	if(student == null){
//    		model.addAttribute("npm", npm);
//    		return "not-found";
//    	}
//    	
//        model.addAttribute("student", student);
//        return "form-update";
//    }
//    
//    @RequestMapping("/student/update/submit")
//    public String updateSubmit (@RequestParam(value = "npm") String npm,
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "gpa", required = false) double gpa)
//    {
//    	
//    	StudentModel student = new StudentModel(npm, name, gpa);
//    	studentService.updateStudent(student);
//    	
//        return "success-update";
//    }
}
