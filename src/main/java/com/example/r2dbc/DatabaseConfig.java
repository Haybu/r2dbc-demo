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

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

/**
 * @author Haytham Mohamed
 **/

@Configuration
public class DatabaseConfig {

	private final R2dbcConnectionProperties properties;

	public DatabaseConfig(R2dbcConnectionProperties properties) {
		this.properties = properties;
	}

	@Bean
	public EmployeeRepository employeeRepository(R2dbcRepositoryFactory r2dbcRepositoryFactory) {
		return r2dbcRepositoryFactory.getRepository(EmployeeRepository.class);
	}

	@Bean
	public R2dbcRepositoryFactory r2dbcRepositoryFactory(DatabaseClient databaseClient) {
		RelationalMappingContext context = new RelationalMappingContext();
		context.afterPropertiesSet();
		return new R2dbcRepositoryFactory(databaseClient, context);
	}

	@Bean
	public DatabaseClient databaseClient(ConnectionFactory factory) {
		return DatabaseClient.builder().connectionFactory(factory).build();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		PostgresqlConnectionConfiguration postgres = PostgresqlConnectionConfiguration.builder()
				.host(properties.getHost())
				.port(properties.getPort())
				.database(properties.getDatabase())
				.username(properties.getUsername())
				.password(properties.getPassword())
				.build();
		return new PostgresqlConnectionFactory(postgres);
	}
}
