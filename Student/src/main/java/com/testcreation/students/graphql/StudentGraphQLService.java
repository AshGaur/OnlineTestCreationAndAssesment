package com.testcreation.students.graphql;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.students.bean.Student;
import com.testcreation.students.service.StudentService;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class StudentGraphQLService {

	@Autowired
	StudentService service;
	
	@Value("classpath:students.graphql")
	Resource resource;
	
	private GraphQL graphQL;
	
	@PostConstruct
	private void loadSchema() throws IOException {
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allStudents",(DataFetcher<Iterable<Student>>)(environment)-> service.getAllStudents())
                        .dataFetcher("student", (DataFetcher<Optional<Student>>)(environment)->service.getStudentById(environment.getArgument("id")))
                        .dataFetcher("students", (DataFetcher<List<Student>>)(environment)->service.getStudentsBySubscriptionId(environment.getArgument("subId")))
                 )
                .build();
    }

	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}
