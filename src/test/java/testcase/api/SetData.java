package testcase.api;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import payload.api.TestData;
import urlandhttprequest.api.HttpRequestClass;
@Listeners(utility.api.ExtentReportWithListener.class)

public class SetData {

	TestData td;
	Faker fk;

	@BeforeTest
	public void setUp() {
		fk = new Faker();
		td = new TestData();
		td.setId(fk.idNumber().hashCode());
		td.setFirst_name(fk.name().firstName());
		td.setLast_name(fk.name().lastName());
		td.setUsername(fk.name().username());
		td.setEmail(fk.internet().safeEmailAddress());
		td.setPassword(fk.internet().password(5, 10));
		td.setPhone(fk.phoneNumber().cellPhone());

	}

	@Test(priority = 1)
	public void postTest() {

		Response r = HttpRequestClass.createUser(td);
		r.then().log().all();
		Assert.assertEquals(r.statusCode(), 200, "Incorrect Status code");
				
	}

	@Test(priority = 2)
	public void getTest() {

		Response r = HttpRequestClass.getUser(this.td.getUsername());
		r.then().log().all();
		Assert.assertEquals(r.statusCode(), 200, "Incorrect Status code");
				
	}
	
	@Test(priority = 3)
	public void putTest() {

		td.setUsername(fk.name().username());
		Response r = HttpRequestClass.putUser(this.td.getUsername(), td);
		r.then().log().all();
		Assert.assertEquals(r.statusCode(), 200, "Incorrect Status code");
				
		System.out.println("After Update");
		
		Response r1 = HttpRequestClass.getUser(this.td.getUsername());
		r1.then().log().all();
		Assert.assertEquals(r1.statusCode(), 200, "Incorrect Status code");
		
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		
		Response r = HttpRequestClass.deleteUser(this.td.getUsername(), td);
		r.then().log().all();
		Assert.assertEquals(r.statusCode(), 200, "Incorrect Status code");
		
	}
}
