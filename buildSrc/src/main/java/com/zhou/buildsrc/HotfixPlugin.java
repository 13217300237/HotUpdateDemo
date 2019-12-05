package com.zhou.buildsrc;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;

import org.gradle.api.Action;
import org.gradle.api.DomainObjectSet;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.io.File;
import java.util.Set;

public class HotfixPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("\n\n\n=================Our hotfixPlugin codes=================");

        // 所谓gradle开发，语法完全可以用java。需要熟悉的只有api。以及思维。
        //

        project.getExtensions().create("HotfixExt", HotfixExt.class);

        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                //找到额外属性
                final HotfixExt hotfixExt = project.getExtensions().findByType(HotfixExt.class);
                // 找到系统属性
                AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);
                DomainObjectSet<ApplicationVariant> applicationVariants = appExtension.getApplicationVariants();
                for (ApplicationVariant var : applicationVariants) {
                    //debug  release 因为任务的名字是release/debug有关,我们要找到确切的切入点，就必须拿到这个值

                    final String variantName = var.getName();
                    final String myTaskName = "transformClassesWithDexBuilderFor" + firstCharUpperCase(variantName);
                    final Task task = project.getTasks().findByName(myTaskName);
                    task.doFirst(new Action<Task>() {
                        @Override
                        public void execute(Task task) {
                            System.out.println("\n\n\n=================task.doFirst=================\n\n\n");
                            Set<File> files = task.getInputs().getFiles().getFiles();
                            for (File f : files) {
                                String filePath = f.getAbsolutePath();
                            }

                            System.out.println("\n\n\n=================task.doFirst   end=================\n\n\n");
                        }
                    });

                }

                System.out.println("=================end=================");
            }
        });


    }

    private String firstCharUpperCase(String input) {
        String s = input.substring(0, 1).toUpperCase();
        String last = input.substring(1);
        return new StringBuilder().append(s).append(last).toString();
    }
}
