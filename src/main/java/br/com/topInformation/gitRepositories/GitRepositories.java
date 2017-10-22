package br.com.topInformation.gitRepositories;

import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class GitRepositories
{
	static public GitRepositories getInstance()
	{
		return new GitRepositories();
	}

	public JSONArray parseJSON(JSONArray jsonArr)
	{
		JSONArray jsonRepositories = new JSONArray();
		for (int i = 0; i < jsonArr.length(); i++)
		{
			JSONObject item = jsonArr.getJSONObject(i);
			JSONObject jsonOwner = item.getJSONObject("owner");

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("photoUrl", jsonOwner.get("avatar_url"));
			jsonObj.put("repositoryOwner", jsonOwner.get("login"));
			jsonObj.put("repositoryName", item.get("name"));
			jsonObj.put("stars", item.get("stargazers_count"));
			jsonObj.put("forks", item.get("forks"));

			jsonRepositories.put(jsonObj);
		}

		return jsonRepositories;
	}

	public JSONObject readUrl(String url)
	{
		JSONObject jsonObj = null;
		try
		{
			jsonObj = new JSONObject(IOUtils.toString(new URL(url).openStream()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return jsonObj;
	}
}
