package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import org.example.hexlet.model.course.Course;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.courses.CoursePage;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        List<Course> courses = List.of(new Course(1, "js", "js best"), new Course(2, "java", "java best"), new Course(3, "php", "php best"));

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var course = courses.get((int) id - 1);
            var page = new CoursePage(course);
            ctx.render("course/index.jte");
        });

        app.get("/courses", ctx -> {
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });


        app.start(7070);
    }
}