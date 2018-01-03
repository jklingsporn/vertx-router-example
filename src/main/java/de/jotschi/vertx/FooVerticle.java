package de.jotschi.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class FooVerticle extends AbstractVerticle {

	private static final Logger log = LoggerFactory.getLogger(FooVerticle.class);

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		log.info("Starting verticle {" + this + "}");
		HttpServer server = vertx.createHttpServer();

		RouterStorage storage = new RouterStorage(vertx);

		// Test endpoint which returns some content
		storage.getSubRouter().route("/foo").handler(rc -> {
			rc.response().putHeader("ContentType", "text/html")
					.end("<html><body><strong>foo</strong></body></html>");
		});

		// start server
		server.requestHandler(storage.getRouter()::accept).listen(8888, lh -> {
			if (lh.failed()) {
				startFuture.fail(lh.cause());
			} else {
				startFuture.complete();
			}
		});

	}

}
