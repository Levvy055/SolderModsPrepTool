
package pl.hopelew.soldermodspreptool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "url", "name", "type", "version", "filesize", "versions", "downloads", "uploaded_at" })
public class ModCurseFile {

	private static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@JsonProperty("id")
	public Integer id;
	@JsonProperty("url")
	public String url;
	@JsonProperty("name")
	public String name;
	@JsonProperty("type")
	public String type;
	@JsonProperty("version")
	public String version;
	@JsonProperty("filesize")
	public String filesize;
	@JsonProperty("versions")
	public List<String> versions = null;
	@JsonProperty("downloads")
	public Integer downloads;
	@JsonProperty("uploaded_at")
	public String uploadedAt;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Date getDate() {
		try {
			return f.parse(uploadedAt);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return "ModFile [name=" + name + ", type=" + type + ", version=" + version + ", getDate()="
				+ getDate().toString() + "]";
	}

}
