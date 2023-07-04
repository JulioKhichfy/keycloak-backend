package com.tutorial.keycloakbackend;

import com.tutorial.keycloakbackend.model.Foo;
import com.tutorial.keycloakbackend.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class KeycloakBackendApplication implements CommandLineRunner {
	@Autowired
	private FooService service;
	public static void main(String[] args) {
		SpringApplication.run(KeycloakBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Foo> foos = new ArrayList<>();
		Foo f1 = new Foo(null, "Foo 1");
		Foo f2 = new Foo(null, "Foo 2");
		Foo f3 = new Foo(null, "Foo 3");
		Foo f4 = new Foo(null, "Foo 4");
		Foo f5 = new Foo(null, "Foo 5");
		Foo f6 = new Foo(null, "Foo 6");
		Foo f7 = new Foo(null, "Foo 7");
		Foo f8 = new Foo(null, "Foo 8");
		Foo f9 = new Foo(null, "Foo 9");
		Foo f10 = new Foo(null, "Foo 10");
		foos.add(f1);
		foos.add(f2);
		foos.add(f3);
		foos.add(f4);
		foos.add(f5);
		foos.add(f6);
		foos.add(f7);
		foos.add(f8);
		foos.add(f9);
		foos.add(f10);
		if(service.saveAll(foos))
			System.out.println("Foos criado no banco H2");
		else
			System.out.println("Erro ao salvar Foos no banco H2");

	}

}
