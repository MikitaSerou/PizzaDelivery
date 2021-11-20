package org.study.PizzaDelivery;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.io.File;


public class Application {
    public static void main(String[] args) throws Exception {

        String webAppDirLocation = "src/main/";
        Tomcat tomcat = new Tomcat();

        //Set Port #
        tomcat.setPort(8080);

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webAppDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}

