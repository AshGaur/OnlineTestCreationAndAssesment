package com.testcreation.admin.graphql;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.admin.bean.Subscription;
import com.testcreation.admin.service.SubscriptionService;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class SubscriptionGraphQLService {

	@Autowired
	SubscriptionService service;
		
	@Value("classpath:subscriptions.graphql")
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
                        .dataFetcher("allSubscription",(DataFetcher<Iterable<Subscription>>)(environment)-> service.getAllSubscriptions())
                        .dataFetcher("subscription", (DataFetcher<Optional<Subscription>>)(environment)->service.getSubscriptionById(environment.getArgument("id")))
                        .dataFetcher("subscriptionsByRole", (DataFetcher<List<Subscription>>)(environment)->service.getSubscriptionsByRole(environment.getArgument("role")))
                 )
                .build();
    }

	public GraphQL getGraphQL() {
		return graphQL;
	}
	
}

	

