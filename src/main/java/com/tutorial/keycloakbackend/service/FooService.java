package com.tutorial.keycloakbackend.service;

import com.tutorial.keycloakbackend.model.Foo;
import com.tutorial.keycloakbackend.repositories.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FooService {

    @Autowired
    private FooRepository repository;

    public List<Foo> list(){
        return repository.findAll();
    }

    public Foo detail(Integer id){
        Foo foo = repository.findById(id).orElse(null);
        return foo;
    }

    public Foo create(Foo foo){
        Foo fooSaved = null;
        try {
            fooSaved = repository.save(foo);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            return fooSaved;
        }
    }

    public Foo update(Integer id, Foo foo){
        Foo fooOriginal = this.detail(id);
        Foo fooUpdated = null;
        if(fooOriginal != null) {
            fooOriginal.setName(foo.getName());
            fooUpdated = this.create(fooOriginal);
        }
        return fooUpdated;
    }

    public boolean delete(Integer id){
        Foo foo = this.detail(id);
        if(foo != null) {
            try {
                repository.delete(foo);
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    public boolean saveAll(List<Foo> foos){
       List<Foo> all = repository.saveAll(foos);
       if(all != null && all.size() > 0){
           return true;
       }
       return false;
    }
}
