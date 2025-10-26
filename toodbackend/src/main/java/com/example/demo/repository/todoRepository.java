package com.example.demo.repository;

import com.example.demo.models.todo;
import org.springframework.data.jpa.repository.JpaRepository;

/*
@Component
public class todoRepository {
   public  String getAlltools(){
        return "ToDos";
    }
}*/

public interface todoRepository extends JpaRepository<todo,Long> {


}
