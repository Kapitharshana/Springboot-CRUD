package com.example.demo.service;

import com.example.demo.models.todo;
import com.example.demo.repository.todoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class todoService {

    @Autowired
    private static todoRepository todorepo;


   public todo craetetodo(todo td){
       return todorepo.save(td);
   }

   public todo getbyid(Long id){
       return todorepo.findById(id).orElseThrow(()-> new RuntimeException("Todo not found"));
   }

   public Page<todo> pagination(int page, int size){
       Pageable pag= PageRequest.of(page,size);
       return todorepo.findAll(pag);
   }

   public List<todo> giveall(){
       return todorepo.findAll();
   }

   public todo updatetodo(todo td){
       return todorepo.save(td);
   }

    //delete by id
   public void deletetodobyid(Long id){
       todorepo.delete(getbyid(id));
   }


    public void deletetodo(){
        todorepo.deleteAll();
    }



   }
