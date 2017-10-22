package br.com.topInformation.gitRepositories.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;

import br.com.topInformation.gitRepositories.GitRepositories;

@Path("/gitRepositories")
public class GitRepositoriesService {
	@GET
	@Path("/getStarredRepositories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStarredRepositories() {
		JSONArray jsonItems = null;
		String url = "https://api.github.com/search/repositories?q=language:apex&sort=stars";
		JSONArray jsonObj = GitRepositories.getInstance().readUrl(url);
		if (jsonObj != null) {
			jsonItems = GitRepositories.getInstance().parseJSON(jsonObj);
		} else {
			return Response.status(Status.NO_CONTENT)
					.entity("Não foi possível acessar a API. Por favor, contate o administrador.").build();
		}

		return Response.ok(jsonItems.toString()).build();
	}
}
