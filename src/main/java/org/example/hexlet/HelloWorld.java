package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // Название параметров мы выбрали произвольно
        app.get("users/{id}/post/{postId}", ctx -> {
            var userId = ctx.pathParam("id");
            var postId =  ctx.pathParam("postId");
            ctx.result("User ID: " + userId + " Post ID: " + postId);
        });

        app.start(7070);
    }
}