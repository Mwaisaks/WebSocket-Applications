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

***
1. Is it okay to use a dot (.) in @MessageMapping URL like /chat.sendMessage?
   
Yes, it's totally fine.

   * STOMP destinations (used in WebSockets with Spring) are just strings, not actual RESTful URLs.

   * You can use /chat.sendMessage, /chat/sendMessage, or even /banana.mango — as long as both client and server agree on the same string.

   * However, for clarity and consistency, many teams prefer using slash-separated paths, like /chat/sendMessage.

   ✅ Best practice tip: Use slashes (/) if you're used to REST-style naming or want better readability.

**Does @Payload work like @RequestBody in REST APIs?**

| Annotation     | Layer             | What it does                                                              |
| -------------- | ----------------- | ------------------------------------------------------------------------- |
| `@RequestBody` | REST              | Extracts JSON from the HTTP body and maps it to a Java object             |
| `@Payload`     | WebSocket (STOMP) | Extracts the message body from a STOMP frame and maps it to a Java object |

`@MessageMapping` in WebSocket controllers is conceptually similar to `@GetMapping`, `@PostMapping`, etc. in REST controllers, 
but for messages over WebSockets, not HTTP requests.

**Comparison**

| WebSocket (STOMP)                                     | REST (HTTP)                           |
| ----------------------------------------------------- | ------------------------------------- |
| `@MessageMapping("/chat.sendMessage")`                | `@PostMapping("/chat/sendMessage")`   |
| Message payload is sent via WebSocket                 | Request body is sent via HTTP         |
| Server responds by broadcasting or replying via topic | Server responds with HTTP response    |
| Clients stay connected                                | Clients connect per request           |
| Used in persistent, real-time comms                   | Used in request-response interactions |


