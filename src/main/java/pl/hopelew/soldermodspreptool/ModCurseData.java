
package pl.hopelew.soldermodspreptool;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "game", "type", "files", "title", "members", "description" })
public class ModCurseData {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("game")
    public String game;
    @JsonProperty("type")
    public String type;
	@JsonProperty("title")
	public String title;
	@JsonProperty("description")
	public String description;
    @JsonProperty("files")
	public List<ModCurseFile> files = null;
    @JsonProperty("members")
	public List<ModMember> members = null;
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

	public void verify() throws Exception {
		if (StringUtils.isEmpty(game) || !game.equals("minecraft")) {
			throw new Exception("'game' is not 'minecraft' but '" + game + "'");
		}
		if (StringUtils.isEmpty(type) || !type.equals("Mods")) {
			throw new Exception("'type' is not 'Mods' but '" + type + "'");
		}
		if (files == null || files.size() == 0) {
			throw new Exception("No files given by cf");
		}
	}

	public List<ModCurseFile> getFilesForVersion(String version) {
		return files.stream().filter(p -> p.version.equals(version) || p.versions.contains(version))
				.sorted(Comparator.comparing(ModCurseFile::getDate).reversed()).collect(Collectors.toList());
	}

	public List<ModCurseFile> getBetaFilesForVersion(String version) {
		return getFilesForVersion(version).stream().filter(f -> "beta".equals(f.type)).collect(Collectors.toList());
	}

	public List<ModCurseFile> getReleaseFilesForVersion(String version) {
		return getFilesForVersion(version).stream().filter(f -> "release".equals(f.type)).collect(Collectors.toList());
	}

}
