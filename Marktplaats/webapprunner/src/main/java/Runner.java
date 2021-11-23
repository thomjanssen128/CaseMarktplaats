// import org.apache.catalina.LifecycleException;
// import org.apache.catalina.WebResourceRoot;
// import org.apache.catalina.core.StandardContext;
// import org.apache.catalina.startup.Tomcat;
// import org.apache.catalina.webresources.DirResourceSet;
// import org.apache.catalina.webresources.StandardRoot;
//
// import javax.servlet.ServletException;
// import java.io.File;
//
// public class Runner {
//     public static void main(String[] args) throws LifecycleException, ServletException {
//         String projectRoot = "javawebapp/";
//         String webappLocation = projectRoot + "src/main/webapp/";
//         String classesLocation = projectRoot + "target/classes";
//
//         System.out.println("Configuring app with basedir: " + new File(projectRoot).getAbsolutePath());
//
//         Tomcat tomcat = new Tomcat();
//         tomcat.setPort(8080);
//         System.out.println(tomcat);
//
//         // Add some roles and users for testing the secured part of the app:
//         // tomcat.addRole("bram", "user");
//         // tomcat.addRole("bram", "admin");
//         tomcat.addUser("thom", "hallo");
//         System.out.println(tomcat);
//
//         // This is equivalent to adding a web application to Tomcat's webapps directory:
//         StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(webappLocation).getAbsolutePath());
//
//         // Map the WEB-INF/classes to target/classes
//         WebResourceRoot resources = new StandardRoot(ctx);
//         resources.addPreResources(new DirResourceSet(resources,
//                 "/WEB-INF/classes",
//                 new File(classesLocation).getAbsolutePath(),
//                 "/"));
//         ctx.setResources(resources);
//
//         // Start tomcat and wait until it is finished.
//         tomcat.start();
//         System.out.println("x");
//         tomcat.getServer().await();
//     }
// }
//
