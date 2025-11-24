package buildlogic;

import org.gradle.api.Project;
import org.gradle.language.jvm.tasks.ProcessResources;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String getArchiveName(Project project, Project rootProject) {
        if(rootProject == project) {
            return project.getName();
        } else {
            StringBuilder name = new StringBuilder(project.getName());
            var currentParent = project.getParent();
            while(currentParent != rootProject) {
                if(currentParent == null) continue;

                name.insert(0, currentParent.getName() + "-");
                currentParent = currentParent.getParent();
            }
            return rootProject.getName() + "-" + name;
        }
    }

    public static void setupResources(Project project, Project rootProject, String filePattern) {
        setupResources(project, rootProject, filePattern, null);
    }

    public static void setupResources(Project project, Project rootProject, String filePattern, Map<String, String> values) {

        Map<String, String> res = new HashMap<>();
        res.put("id", rootProject.getName());
        res.put("version", project.getVersion().toString());

        if(values != null) res.putAll(values);

        project.getTasks().withType(ProcessResources.class, task ->
                task.filesMatching(filePattern, dt ->
                        dt.expand(res)
                )
        );

    }
}
