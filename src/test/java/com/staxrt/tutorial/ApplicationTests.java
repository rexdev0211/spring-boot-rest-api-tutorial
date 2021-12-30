package com.staxrt.tutorial;

import com.staxrt.tutorial.model.Article;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testArticlePageable() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		int page = 1;
		int size = 10;
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/articles?page=" + page + "&size=" + size,
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testCreateArticle() {
		Article article = new Article();
		article.setTitle("Test Title");
		article.setAuthor("Test Author");
		article.setContent("Test Content");

		ResponseEntity<Article> postResponse = restTemplate.postForEntity(getRootUrl() + "/articles", article, Article.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testGetCountOfPublishedArticles() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String startDate = "2021-12-21";
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/articles/statistics?startDate=" + startDate,
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

}
