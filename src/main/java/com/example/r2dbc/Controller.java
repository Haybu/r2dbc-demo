/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.r2dbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Haytham Mohamed
 **/
@RestController
@Slf4j
public class Controller {

	private final EmployeeRepository repository;

	public Controller(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Flux<Employee> employees() {
		log.info("getting all employees");
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Employee> employee(@PathVariable Integer id) {
		return repository.findById(id);
	}
}
