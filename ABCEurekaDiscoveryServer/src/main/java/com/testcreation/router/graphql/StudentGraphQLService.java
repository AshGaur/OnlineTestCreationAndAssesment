package com.testcreation.router.graphql;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.router.bean.Student;
import com.testcreation.router.service.StudentRouterService;

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
	StudentRouterService studentRouterService;
	
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
                        .dataFetcher("allStudents",(DataFetcher<List<Student>>)(environment)-> studentRouterService.getAllStudents())
                        .dataFetcher("student", (DataFetcher<Student>)(environment)->studentRouterService.getStudentById(environment.getArgument("id")))
                        .dataFetcher("students", (DataFetcher<List<Student>>)(environment)->studentRouterService.getStudentsBySubscriptionId(environment.getArgument("subId")))
                 )
                .build();
    }

	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}
