package com.testcreation.router.graphql;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.router.bean.Attempt;
import com.testcreation.router.service.AttemptRouterService;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class AttemptGraphQLService {

	@Autowired
	AttemptRouterService attemptRouterService;
	
	@Value("classpath:attempts.graphql")
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
                        .dataFetcher("allAttempts",(DataFetcher<List<Attempt>>)(environment)-> attemptRouterService.getAllAttempts())
                        .dataFetcher("attemptsByResult", (DataFetcher<List<Attempt>>)(environment)->attemptRouterService.getAttemptsByResultId(environment.getArgument("resultId")))
                        .dataFetcher("attemptByResultAndQuestion", (DataFetcher<Attempt>)(environment)->attemptRouterService.getAttemptByResultIdAndQuestionId(environment.getArgument("resultId"), environment.getArgument("questionId")))
                 )
                .build();
    }

	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}
