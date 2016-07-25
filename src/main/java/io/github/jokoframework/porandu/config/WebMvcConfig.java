package io.github.jokoframework.porandu.config;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(WebMvcConfig.class);
	
	@Autowired
	private Environment environment;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String frontend;

		String workingDirectory = normalizePath(System.getProperty("user.dir"));
		
		if (environment.acceptsProfiles("production")) {
			
			/*
			 * if we are running via gradle bootRun then the working directory is
			 * suffixed with /backend and thus won't find resources correctly
			 */
			if (workingDirectory.endsWith("/backend")) {
				workingDirectory = workingDirectory.substring(0, workingDirectory.lastIndexOf("/backend"));
			}

			/* find front-end build files from the local file system */
			frontend = "file:///" + workingDirectory + "/frontend/";
		} else {
			/* find front-end build files from the classpath */
			frontend = "classpath:/";
		}

		log.info("Agregando ruta al frontend: {}", frontend);
		registry.addResourceHandler("/**").addResourceLocations(frontend, "classpath:/static/", "classpath:/public/");
		
	
	}
	
	private String normalizePath(String path) {
		if (!isWindows()) {
			return path;
		}
		
		return path.replace("\\", "/");
	}
	
	private boolean isWindows() {
        return File.pathSeparatorChar == ';';
    }
}
