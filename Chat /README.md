# WebSockets - Chat Application

### Create the Config class
WebSocketConfig in the config package

**ChatController**
**`@Payload` work like `@RequestBody` in REST APIs**

| Annotation     | Layer             | What it does                                                              |
| -------------- | ----------------- | ------------------------------------------------------------------------- |
| `@RequestBody` | REST              | Extracts JSON from the HTTP body and maps it to a Java object             |
| `@Payload`     | WebSocket (STOMP) | Extracts the message body from a STOMP frame and maps it to a Java object |

