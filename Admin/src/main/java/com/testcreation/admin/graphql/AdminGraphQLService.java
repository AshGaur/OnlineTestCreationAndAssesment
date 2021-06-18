package com.testcreation.admin.graphql;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.testcreation.admin.bean.Admin;
import com.testcreation.admin.service.AdminService;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class AdminGraphQLService {

	@Autowired
	AdminService service;
	
	@Value("classpath:admins.graphql")
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
//                        .dataFetcher("allAdmins", new DataFetcher<List<Admin>>() {
//							@Override
//							public List<Admin> get(DataFetchingEnvironment environment) {
//								return service.getAllAdmins();
//							}
//						})
                        .dataFetcher("allAdmins",(DataFetcher<Iterable<Admin>>)(environment)-> service.getAllAdmins())
                        .dataFetcher("admin", (DataFetcher<Optional<Admin>>)(environment)->service.getAdminById(environment.getArgument("id")))
                 )
                .build();
    }

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
