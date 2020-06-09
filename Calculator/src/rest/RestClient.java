package rest;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import expression.Expression;
import expression.Result;
import model.Model;

public class RestClient {
	
	private Client client;
	private URI uri;
	private WebTarget webTarget;

	public RestClient() {
		this.client = ClientBuilder.newClient();
		this.uri = URI.create("http://route-webcalc.apps.us-east-1.starter.openshift-online.com");
		this.webTarget = client.target(uri);
		this.webTarget = webTarget.path("webCalc").path("rest").path("calc");
	}
	
	public double goRest(Expression expression) {
		Model.checkIfLegal(expression);
		Result response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(expression), Result.class);
		return response.getRes();
	}
	
}
