## One on One Chat Application

## WebSocketConfig class
2 annotations-`@Configuration` and `@EnableWebSocketMessageBroker`

**registerStompEndpoints()**
```declarative
@Override
public void registerStompEndpoints(StompEndpointRegistry registry) {
registry.addEndpoint("/ws").withSockJS();
}

```

What it does:

* Registers a STOMP endpoint at /ws.

        This is the HTTP URL that clients (like browsers or JavaScript apps) will connect to initially to perform the WebSocket handshake.

        It is the “entry point” for WebSocket communication.

* Adds SockJS fallback with .withSockJS()

        SockJS is a library that provides fallback options in case WebSocket is not supported by the browser or blocked by firewalls.

        It falls back to techniques like long polling or streaming so your app still works even if WebSockets can’t be used.

        In modern apps, you can sometimes remove withSockJS() if you only want native WebSockets, but leaving it improves compatibility.

**configureMessageConverters()**
This method allows you to customize how WebSocket messages are converted from Java objects to JSON and back.

You use this if you:

    Want to change the default behavior.

    Need a specific ObjectMapper or content type.

    Are working with complex payloads that Spring doesn’t handle well by default.

**configureMessageBroker()**
This sets up the routing rules for STOMP messages

| Line                                        | What It Means                                                                                                                     |
| ------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| `enableSimpleBroker("/user")`               | Enables Spring's in-memory broker. It will handle messages sent to `/user/**` destinations.                                       |
| `setApplicationDestinationPrefixes("/app")` | Client messages sent to `/app/**` are routed to your controller methods (annotated with `@MessageMapping`).                       |
| `setUserDestinationPrefix("/user")`         | Enables **private messaging**. If you send a message to `/user/{username}/queue`, Spring will route it to the right user session. |


