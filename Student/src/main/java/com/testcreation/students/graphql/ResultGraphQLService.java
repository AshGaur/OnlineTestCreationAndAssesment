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

import com.testcreation.students.bean.Result;
import com.testcreation.students.service.ResultService;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class ResultGraphQLService {

	@Autowired
	ResultService service;
	
	@Value("classpath:results.graphql")
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
                        .dataFetcher("allResults",(DataFetcher<Iterable<Result>>)(environment)-> service.getAllResults())
                        .dataFetcher("result",(DataFetcher<Optional<Result>>)(environment)-> service.getResultById(environment.getArgument("id")))
                        .dataFetcher("results",(DataFetcher<List<Result>>)(environment)-> service.getResultsByStudentId(environment.getArgument("studentId")))
                        .dataFetcher("resultByStudentAndTest",(DataFetcher<Result>)(environment)-> service.getResultByStudentIdAndTestId(environment.getArgument("resultId"),environment.getArgument("studentId")))
                 )
                .build();
    }
	
	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}
