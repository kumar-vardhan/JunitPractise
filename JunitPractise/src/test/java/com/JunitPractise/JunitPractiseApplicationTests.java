package com.JunitPractise;

import com.JunitPractise.entity.Products;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JunitPractiseApplicationTests {

	@LocalServerPort
	private int port;
	private String baseUrl="http://localhost";
	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repository h2Repository;

	@BeforeAll
	public static void init(){
		restTemplate= new RestTemplate();
	}

	@BeforeEach
	public void setUp(){
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/products");
	}

	@Test
	public void testAddProduct(){
		Products products = new Products("headset",	3000.00);
		Products response = restTemplate.postForObject(baseUrl+"/product",products,Products.class);
		assertEquals("headset",response.getName());
		assertEquals(1,h2Repository.findAll().size());
	}

	@Test
	@Sql(statements = "insert into products(name,price) values('ac',25000.00)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from products where name='ac'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetProducts(){
		List<Products> products = restTemplate.getForObject(baseUrl+"/all",List.class);
		assertEquals(1,products.size());
		assertEquals(1,h2Repository.findAll().size());
	}

	@Test
	@Sql(statements = "insert into products(name,price) values('car',250000.00)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from products where name='ac'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testProductsById(){
		Products products = restTemplate.getForObject(baseUrl+"/{id}", Products.class,1);
		assertAll(
				()->assertNotNull(products),
				()->assertEquals(1,products.getId()),
				()->assertEquals("car",products.getName())
		);
	}

	@Test
	@Sql(statements = "insert into products(name,price) values('smartwatch',5000.00)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from products where name='smartwatch'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateById(){
		Products product = new Products("smartwatch",7000.00);
		restTemplate.put(baseUrl+"/update/{id}",product,1);
		Products products1 = h2Repository.findById(1).get();
		assertAll(
				()->assertNotNull(product),
				()->assertEquals(7000,product.getPrice())
		);
	}

	@Test
	@Sql(statements = "insert into products(name,price) values('smartwatch',5000.00)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteProductById(){
		int recordCount=h2Repository.findAll().size();
		assertEquals(1,recordCount);
		restTemplate.delete(baseUrl+"/delete/{id}",1);
		assertEquals(0,h2Repository.findAll().size());
	}

}
