package com.briteitservices.week4.development;

import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;
import javax.servlet.ServletContext;

/**
 * @author Stuart Douglas
 */
public class DevelopmentExtension implements ServletExtension {

    @Override
    public void handleDeployment(DeploymentInfo deploymentInfo, ServletContext servletContext) {
        String path = System.getProperty("development.path");
        if (path != null) {
            deploymentInfo.setResourceManager(new DevelopmentResourceManager(path, deploymentInfo.getResourceManager()));
        }
    }
}
