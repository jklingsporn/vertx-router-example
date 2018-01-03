package de.jotschi.vertx;

import io.vertx.core.Vertx;

public class Server {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new FooVerticle());
        vertx.deployVerticle(new BarVerticle());
	}

}
