/*
 * Copyright © 2009-2018 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */
package org.apromore.plugin.portal.bpmneditor;

import java.io.*;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apromore.plugin.editor.EditorPlugin;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class PluginsHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(PluginsHandlerServlet.class);
	
	@Autowired
	private ApplicationContext appContext;
	
	private ServletContext servletContext;

	public void init(ServletConfig config) throws ServletException {
		//Workaround to get the Spring applicationContext injected properly
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		this.servletContext = config.getServletContext();
	}

	/**
	 * Returns a plugins configuration xml file that fits to the current user's license.
	 * @throws Exception
	 */
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse res) throws IOException, ServletException {
  		File pluginConf = new File(this.servletContext.getRealPath("") + "/WEB-INF/xml/editor/plugins.xml");

		@SuppressWarnings("unchecked")
		List<EditorPlugin> editorPlugins = (List<EditorPlugin>) appContext.getBean("editorPlugins");
		StringBuilder additionalPlugins = new StringBuilder();
		for (EditorPlugin plugin: editorPlugins) {
			additionalPlugins.append("<plugin source=\""+plugin.getJavaScriptURI()+"\" name=\""+plugin.getJavaScriptPackage()+"\"/>");
		}

		if(pluginConf.exists()) {
  			res.setStatus(200);
  	  		res.setContentType("text/xml");

			try {
				String pluginDef = IOUtils.toString(new FileReader(pluginConf));
				// Insert the Editor plug-ins replacing the placeholder
				//TODO switch to a real XML reader/writer based implementation
				pluginDef = pluginDef.replace("<?EDITOR-PLUGINS?>", additionalPlugins.toString());
				res.getWriter().append(pluginDef);
			} catch (Exception e) {
				try {
	                LOGGER.error("Plugin file reading failed: " + e.toString(), e);
	                JSONObject json = new JSONObject();
	                json.put("errors", e.toString());
	                res.setStatus(500);
	                res.setContentType("text/plain; charset=UTF-8");
	            } catch (Exception e1) {
	                LOGGER.error("Unable to report servlet error to JSON: " + e1.toString(), e1);
	            }
			}
  		} else {
  			try {
                LOGGER.error("Plugin file plugins.xml not exists");
                JSONObject json = new JSONObject();
                json.put("errors", "Plugin file plugins.xml not exists");
                res.setStatus(500);
                res.setContentType("text/plain; charset=UTF-8");
            } catch (Exception e1) {
                LOGGER.error("Unable to report servlet error to JSON: " + e1.toString(), e1);
            }
  		}
    }

}
