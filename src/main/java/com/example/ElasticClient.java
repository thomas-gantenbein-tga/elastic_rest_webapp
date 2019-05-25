package com.example;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

@WebListener
@Singleton
public class ElasticClient implements ServletContextListener {

	private static RestHighLevelClient client = null;
	static RestHighLevelClient getElasticClient() {
		if (client == null) {
			final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials("elastic", "jzTKYvfuYAtbu9XkYKHzRcTy"));

			RestClientBuilder builder = RestClient.builder(
					new HttpHost("a905d31dfa9849039bad0130ed983a5f.eu-central-1.aws.cloud.es.io", 9243, "https"))
					.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
						public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
							return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
						}
					});
			client = new RestHighLevelClient(builder);
		}
		return client;

	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			client.close();
			Logger.getGlobal().info("closed client - yessssssssssssssssssssssssss");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Logger.getGlobal().info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
	}
	
	
}
