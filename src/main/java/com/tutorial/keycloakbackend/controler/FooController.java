package com.tutorial.keycloakbackend.controler;


import com.tutorial.keycloakbackend.dto.ResponseMessage;
import com.tutorial.keycloakbackend.model.Foo;
import com.tutorial.keycloakbackend.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/foo")
@CrossOrigin
public class FooController {

    @Autowired
    private FooService service;

    /*List<Foo> foos =
            Stream.of(new Foo(1, "foo1"),
                    new Foo(2,"foo2"),
                    new Foo(3,"foo3")).collect(Collectors.toList());*/

    @GetMapping("/list")
    @RolesAllowed("backend-user")
    public ResponseEntity<List<Foo>> list(){
        List<Foo> foos = this.service.list();
        return new ResponseEntity<>(foos, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @RolesAllowed("backend-user")
    public ResponseEntity<Foo> detail(@PathVariable("id") Integer id){
        //Foo foo = foos.stream().filter(f -> f.getId() == id ).findFirst().orElse(null);
        Foo foo = this.service.detail(id);
        return new ResponseEntity<>(foo, HttpStatus.OK);
    }

    @PostMapping("/create")
    @RolesAllowed("backend-admin")
    public ResponseEntity<?> create(@RequestBody Foo foo){
        //int maxIndex = foos.stream().max(Comparator.comparing(m -> m.getId())).get().getId();
        //foo.setId(maxIndex + 1);
        //foos.add(foo);
        Foo fooSaved = this.service.create(foo);
        if(fooSaved != null)
            return new ResponseEntity(new ResponseMessage("Criado"), HttpStatus.CREATED);
        return new ResponseEntity(new ResponseMessage("Erro ao criar Foo"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update/{id}")
    @RolesAllowed("backend-admin")
    public ResponseEntity<List<Foo>> update(@PathVariable("id") Integer id, @RequestBody Foo foo){
        //Foo fooUpdate = foos.stream().filter(f -> f.getId() == id ).findFirst().orElse(null);
        //fooUpdate.setName(foo.getName());
        Foo fooUpdated = service.update(id, foo);
        if(fooUpdated != null)
            return new ResponseEntity(new ResponseMessage("Atualizado"), HttpStatus.OK);
        return new ResponseEntity(new ResponseMessage("Erro ao atualizar Foo"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed("backend-admin")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        //Foo fooDelete = foos.stream().filter(f -> f.getId() == id ).findFirst().orElse(null);
        //foos.remove(fooDelete);
        if(service.delete(id))
            return new ResponseEntity(new ResponseMessage("Removido"), HttpStatus.OK);
        return new ResponseEntity(new ResponseMessage("Erro ao remover Foo"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
