package RestAssuredSpotify.Spotify;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SpotifyApi {

	String token = "";
	String userId = "";

	@BeforeTest
	public void setup() {
		token = "Bearer BQD-ysqy60zZXjBTEPtZPfniO3VqfAhpQ4Vds0QYBHo9Syn-cKK9OJWOQV2SP62hEe3NS4X_N-JuDuGHniS_NfstA4pT-CGqw8Z9MyZP9MBVdZkh_b7trVXBWCf_nmSEu6BoolJizSAgVDXAqL5U9DkB7jMbAfa_WAwsIeFba8-O9FE83FuiDczCjiT5plUBUi69Md3OH4meCHJTgwtZKmpDsc8BZfOoFN03clnbUXGrtaNwsVi3ZiP9GnbO3NJQLb1fs3aw5YsMW4UEMeoIpdXa_F7cJd8IX5ySWRg5TA";
	}

	@Test
	public void testGET_CurrentUserProfile_ShoulReturn_StatusCode200() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).get("https://api.spotify.com/v1/me");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		System.out.println("statusCode is :" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testGET_CurrentUserProfileId_ShouldReturn_StatusCode200() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token).get("https://api.spotify.com/v1/me");
		userId = response.then().extract().path("id");
		System.out.println("user Id:" + userId);
		response.prettyPrint();
		response.then().statusCode(200);
	}

	@Test
	public void testPOST_when_CreateAPlaylist_ShouldReturnStatusCode201() {
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", "My Playlist");
		requestParam.put("description", "Second playlist description");
		requestParam.put("public", "false");
		httpRequest.header("Authorization", token);
		httpRequest.body(requestParam.toString());
		Response response = httpRequest.request(Method.POST,
				"https://api.spotify.com/v1/users/31ep2dmuq3sdwzzli644w6gm2r6e/playlists");
		String responseBody = response.getBody().asString();
		System.out.println("response body is" + responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("status code is" + statusCode);
		Assert.assertEquals(statusCode, 201);
	}

	@Test
	public void testPUT_When_UpdatePlaylist_ShouldReturnStatusCode200() {
		RequestSpecification httpRequest = RestAssured.given().contentType(ContentType.JSON);
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", "Updated Playlist");
		requestParam.put("description", "Updated playlist description");
		httpRequest.header("Authorization", token);
		httpRequest.body(requestParam.toString());
		Response response = httpRequest.request(Method.PUT,
				"https://api.spotify.com/v1/playlists/6ImPC9wyNY3O6KUF6xGooo");
		String responseBody = response.getBody().asString();
		System.out.println("response body is" + responseBody);
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testPUT_When_ChangePlaylistDetailst_ShouldReturnStatusCode200() {
		RequestSpecification httpRequest = RestAssured.given().contentType(ContentType.JSON);
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", "Manisha Playlist");
		requestParam.put("description", "Updated playlist description");
		httpRequest.header("Authorization", token);
		httpRequest.body(requestParam.toString());
		Response response = httpRequest.request(Method.PUT,
				"https://api.spotify.com/v1/playlists/6ImPC9wyNY3O6KUF6xGooo");
		String responseBody = response.getBody().asString();
		System.out.println("response body is" + responseBody);
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void testDELETE_When_RemoveTracksforCurrentUser_ShouldReturnStatusCode200() {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", token)
				.delete("https://api.spotify.com/v1/playlists/6ImPC9wyNY3O6KUF6xGooo/tracks");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		System.out.println("statusCode is :" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
}
