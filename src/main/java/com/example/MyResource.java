package com.example;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;


@Path("/hello")
public class MyResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() throws IOException {
		Timer.takeTime("Start");
		RestHighLevelClient client = ElasticClient.getElasticClient();
		Timer.takeTime("Got a client");
		GetRequest request = new GetRequest("posts", "_iHqymoBxSxTtPpQyHrt");
		String[] includes = new String[]{"message"};
		String[] excludes = Strings.EMPTY_ARRAY;
		FetchSourceContext fetchSourceContext =
		        new FetchSourceContext(true, includes, excludes);
		request.fetchSourceContext(fetchSourceContext);
		Timer.takeTime("Finished assembling request");
		GetResponse getResponse = ElasticClient.getElasticClient().get(request, RequestOptions.DEFAULT);
		Timer.takeTime("Got a response");
		Timer.printTimes();
		return getResponse.getSource().toString();
	}

}
