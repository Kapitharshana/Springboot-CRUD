package com.example.demo.controller;

import com.example.demo.models.todo;
import com.example.demo.repository.todoRepository;
import com.example.demo.service.todoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("c")
@Slf4j

public class todocontroller {

    @Autowired
    private todoService todoser;


    @PostMapping("create")
    ResponseEntity<todo> createuser(@RequestBody todo td){
       return new ResponseEntity<>(todoser.craetetodo(td), HttpStatus.CREATED) ;
    }


    //get all details fro postgre sql
    @GetMapping("/page")
    ResponseEntity<Page <todo>> gettodopage(@RequestParam("page") int page,@RequestParam("size") int size ) {
        return new ResponseEntity<>(todoser.pagination(page,size), HttpStatus.OK) ;

    }
    /*
    @GetMapping("/{id}") from h2-console
   // todo gettablebyid(@PathVariable Long id) {
        todo td=todoser.getbyid(id);
        return td;
    }*/

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description="todo retrieved successfully"),
            @ApiResponse(responseCode = "404",  description="todo not retrived")

    })
    @GetMapping("/{id}")
    ResponseEntity<todo> gettablebyid(@PathVariable Long id) {

        try{
            todo td=todoser.getbyid(id);
            return new ResponseEntity<>(td,HttpStatus.OK );
        }

        catch(RuntimeException e){
            log.info("error");
            log.warn("");
            log.error("" , e);
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND );
        }

    }

    @GetMapping("getall")
    ResponseEntity<List <todo>> getall(){
        return new ResponseEntity<>(todoser.giveall(), HttpStatus.OK);
    }


    @DeleteMapping("/d/{id}")
    void deletebyid(@PathVariable Long id){
        todoser.deletetodobyid(id);
    }

    //delete all from the table
    @DeleteMapping("/dall")
    public ResponseEntity<Void> deletealltodos() {
        todoser.deletetodo();
        return new ResponseEntity<>( HttpStatus.NO_CONTENT) ;

    }

    @GetMapping("/get")
    String gettodo() {
        //todoser.printTodos();
        return "todo";
    }

    @GetMapping
    String gettodoidbyparam(@RequestParam("todoid") int id) {
        return "get by id " + id;
    }

    @GetMapping("up")
    String namepass(@RequestParam String username, @RequestParam int password){
        return "User name: " + username+ " Password: " + password;
    }


    /*@PutMapping("/{id}")
    String updateid(@PathVariable int id) {
        return "update todo with id " + id;
    }
*/

    @PutMapping("/update")
    String updateid(@RequestParam int id) {
       return "update todo with id " + id;
    }

/*
     @DeleteMapping("/{id}")
    String deleteid(@PathVariable int id){
        return "Delete the id : " + id;
    }
*/
}
