package com.redhat.hotelbooking;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.CorsHandler;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingStateRestAPI extends CacheAccessVerticle {
    public static final String BOOKINGSTATE_GET_API_ENDPOINT = "/getbookingstate/:id";
    public static final String BOOKINGSTATE_SET_SEARCH_API_ENDPOINT = "/bookingstate/setsearch";
    public static final String BOOKINGSTATE_DELETE_API_ENDPOINT = "/deletebookingstate/:id";

    private final Logger logger = Logger.getLogger(BookingStateRestAPI.class.getName());

    @Override
    protected void initSuccess(Future<Void> startFuture) {

        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");

        String host = config().getString("HTTP_HOST", "localhost");
        int port = config().getInteger("HTTP_PORT", 8080);
        logger.info(String.format("Starting BookingRestAPI in %s:%d", host, port));
        Router router = Router.router(vertx);

        router.route().handler(
            CorsHandler.create("*")
            .allowedHeaders(allowedHeaders)
            .allowedMethod(HttpMethod.GET)
            .allowedMethod(HttpMethod.POST)
            .allowedMethod(HttpMethod.OPTIONS));

        router.get("/").handler(rc -> {
            rc.response().putHeader("content-type", "text/html")
            .end("Welcome to Booking Service API Service");
        });

        router.route().handler(BodyHandler.create());
        router.get(BOOKINGSTATE_GET_API_ENDPOINT).handler(this::handleGetorCreate);
        router.post(BOOKINGSTATE_SET_SEARCH_API_ENDPOINT).handler(this::handleSetSearch);
        router.delete(BOOKINGSTATE_DELETE_API_ENDPOINT).handler(this::handleDelete);

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(port, ar -> {
                if (ar.succeeded()) {
                    startFuture.complete();
                } else {
                    startFuture.fail(ar.cause());
                }
        });
    }

    private String createEmptyBookingState (String id){
        JsonObject searchState = new JsonObject()
                .put("city_name", "")
                .put("date_in", "")
                .put("date_out", "");

        JsonObject selectionHotelState = new JsonObject()
                .put("id", "")
                .put("name", "");

        JsonObject selectionRoomState = new JsonObject()
                .put("id", "")
                .put("room_number", "");

        JsonObject selection = new JsonObject()
                .put("hotel", selectionHotelState)
                .put("room", selectionRoomState);

        String bookingState = new JsonObject()
                .put("id", id)
                .put("state", "/")
                .put("search", searchState)
                .put("selection", selection)
                .encode();
        return bookingState;
    }

    private void handleGetorCreate(RoutingContext rc) {
        String id = rc.request().getParam("id");
        logger.info("Get by id Booking State id=" + id);
        defaultCache.getAsync(id)
            .whenComplete((bookingState, t) -> {
                if (t == null) {
                    String response;
                    if (bookingState == null) {
                        bookingState = createEmptyBookingState(id);
                    }
                    rc.response().end(bookingState);
                } else {
                    logger.log(Level.SEVERE, String.format("Failed to find booking state [%s]", id), t);
                    rc.fail(500);
                }
            });
    }

    private void handleSetSearch(RoutingContext rc) {

        logger.info("Set Search");
        HttpServerResponse response = rc.response();
        JsonObject bodyAsJson = rc.getBodyAsJson();
        if (bodyAsJson != null
                && bodyAsJson.containsKey("id")) {

            String id = bodyAsJson.getString("id");

            defaultCache.putAsync(id,bodyAsJson.encode())
                    .whenComplete((s, tput) -> {
                        if (tput == null) {
                            logger.info(String.format("Booking State Updated for [%s]", id));
                            response.setStatusCode(HttpResponseStatus.CREATED.code()).end("Booking State Updated");
                        } else {
                            logger.log(Level.SEVERE, String.format("Failed to update Booking State [%s]", id), tput);
                            rc.fail(500);
                        }
                    });
        } else {
            response.setStatusCode(HttpResponseStatus.BAD_REQUEST.code())
                    .end(String.format("Body is %s. Some parameters should be provided", bodyAsJson));
        }
    }

    private void handleDelete(RoutingContext rc) {
        String id = rc.request().getParam("id");
        logger.info("DeleteBooking State id=" + id);
        HttpServerResponse response = rc.response();

        defaultCache.removeAsync(id)
            .whenComplete((s, tput) -> {
                if (tput == null) {
                    logger.info(String.format("Booking State Updated for [%s]", id));
                    response.setStatusCode(HttpResponseStatus.OK.code()).end("Booking State Deleted");
                } else {
                    logger.log(Level.SEVERE, String.format("Failed to delete Booking State [%s]", id), tput);
                    rc.fail(500);
                }
            });
    }
   @Override
   protected Logger getLogger() {
      return logger;
   }
}
