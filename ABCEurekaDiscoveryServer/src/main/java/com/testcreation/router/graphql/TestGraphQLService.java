package com.testcreation.router.graphql;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.router.bean.Test;
import com.testcreation.router.service.TestRouterService;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class TestGraphQLService {

	@Autowired
	TestRouterService service;
	
	@Value("classpath:tests.graphql")
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
                        .dataFetcher("allTests",(DataFetcher<List<Test>>)(environment)-> service.getAllTests())
                        .dataFetcher("test", (DataFetcher<Test>)(environment)->service.getTestById(environment.getArgument("id")))
                        .dataFetcher("tests", (DataFetcher<List<Test>>)(environment)->service.getTestsByCategoryName(environment.getArgument("category")))
                        .dataFetcher("testsByTrainer", (DataFetcher<List<Test>>)(environment)->service.getTestsByTrainerId(environment.getArgument("id")))
                 )
                .build();
    }

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
