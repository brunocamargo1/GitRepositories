package br.com.topInformation.gitRepositories.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.topInformation.gitRepositories.GitRepositories;

@Path("/gitRepositories")
public class GitRepositoriesService {
	@GET
	@Path("/getStarredRepositories/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStarredRepositories(@PathParam("page") String page) {
		JSONArray jsonItems = null;
		String url = "https://api.github.com/search/repositories?q=language:apex&sort=stars&page=" + page;
		JSONObject jsonObj = GitRepositories.getInstance().readUrl(url);
		if (jsonObj.getJSONArray("items") != null) {
			jsonItems = GitRepositories.getInstance().parseJSON(jsonObj.getJSONArray("items"));
		} else {
			return Response.status(Status.NO_CONTENT)
					.entity("Não foi possível acessar a API. Por favor, contate o administrador.").build();
		}
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("totalItems", jsonObj.getInt("total_count"));
		jsonData.put("items", jsonItems);

		return Response.ok(jsonData.toString()).build();
	}
}
