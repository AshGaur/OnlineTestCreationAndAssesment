package com.testcreation.router.graphql;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.router.bean.Question;
import com.testcreation.router.service.QuestionRouterService;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class QuestionGraphQLService {

	@Autowired
	QuestionRouterService service;
	
	@Value("classpath:questions.graphql")
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
                        .dataFetcher("allQuestions",(DataFetcher<List<Question>>)(environment)-> service.getAllQuestions())
                        .dataFetcher("question",(DataFetcher<Question>)(environment)-> service.getQuestionById(environment.getArgument("id")))
                        .dataFetcher("questions",(DataFetcher<List<Question>>)(environment)-> service.getQuestionsByTestId(environment.getArgument("testId")))
                 )
                .build();
    }
	
	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}
