package pl.hopelew.soldermodspreptool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModData {
	@JsonProperty("curse-slug")
	public String curseSlug;
	@JsonProperty("solder-slug")
	public String solderSlug;
	@JsonProperty(value = "pathrepl", required = false)
	public String fileNamePathToBeReplaced;

}
