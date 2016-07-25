package io.github.jokoframework.porandu;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "joko.secret")
public class JokoProperties {

	private String file;
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getFile() {
		return this.file;
	}

}