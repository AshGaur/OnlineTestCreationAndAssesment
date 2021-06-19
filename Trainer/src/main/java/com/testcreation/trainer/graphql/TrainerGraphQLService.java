package com.testcreation.trainer.graphql;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.trainer.bean.Trainer;
import com.testcreation.trainer.service.TrainerService;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class TrainerGraphQLService {

	@Autowired
	TrainerService service;
	
	@Value("classpath:trainers.graphql")
	Resource resource;
	
	private GraphQL graphQL;
	
	private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allTrainers",(DataFetcher<Iterable<Trainer>>)(environment)-> service.getAllTrainers())
                        .dataFetcher("trainer", (DataFetcher<Optional<Trainer>>)(environment)->service.getTrainerById(environment.getArgument("id")))
                        .dataFetcher("trainers",(DataFetcher<List<Trainer>>)(environment)-> service.getTrainersBySubscriptionId(environment.getArgument("subId")))
                )
                .build();
    }
	
	@PostConstruct
	private void loadSchema() throws IOException {
		File schemaFile = resource.getFile();
		
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}
