//Code Overview (Snippets)
//// === OrderCreatedEvent.java ===
//public record OrderCreatedEvent(String orderId, String userId, double amount) {}
//// === PaymentResponseEvent.java ===
//public record PaymentResponseEvent(String orderId, boolean success, String userId)
//{}
//// === InventoryResponseEvent.java ===
//public record InventoryResponseEvent(String orderId, boolean success) {}
//// === ShippingResponseEvent.java ===
//public record ShippingResponseEvent(String orderId, boolean success) {}
//// === OrderController.java ===
//@RestController
//@RequestMapping("/orders")
//public class OrderController {
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    public OrderController(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    @PostMapping
//    public String createOrder(@RequestBody OrderRequest request) {
//        String orderId = UUID.randomUUID().toString();
//        kafkaTemplate.send("order-created", new OrderCreatedEvent(orderId,
//                request.userId(), request.amount()));
//        return "Order Created with ID: " + orderId;
//    }
//    public record OrderRequest(String userId, double amount) {}
//}
//// === OrderOrchestrator.java ===
//@Component
//public class OrderOrchestrator {
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    public OrderOrchestrator(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    @KafkaListener(topics = "payment-response", groupId = "order")
//    public void handlePaymentResponse(PaymentResponseEvent event) {
//        if (event.success()) {
//            kafkaTemplate.send("reserve-inventory", new
//                    OrderCreatedEvent(event.orderId(), event.userId(), 0));
//        } else {
//            System.out.println("Payment failed for order: " + event.orderId());
//        }
//    }
//    @KafkaListener(topics = "inventory-response", groupId = "order")
//    public void handleInventoryResponse(InventoryResponseEvent event) {
//        if (event.success()) {
//            kafkaTemplate.send("start-shipping", new
//                    OrderCreatedEvent(event.orderId(), "", 0));
//        } else {
//            System.out.println("Inventory reservation failed for order: " +
//                    event.orderId());
//        }
//    }
//    @KafkaListener(topics = "shipping-response", groupId = "order")
//    public void handleShippingResponse(ShippingResponseEvent event) {
//        if (event.success()) {
//            System.out.println("Order shipped successfully: " + event.orderId());
//            kafkaTemplate.send("send-notification", event);
//        } else {
//            System.out.println("Shipping failed for order: " + event.orderId());
//        }
//    }
//}
//// === PaymentService.java ===
//@Component
//public class PaymentService {
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    public PaymentService(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    @KafkaListener(topics = "order-created", groupId = "payment")
//    public void processPayment(OrderCreatedEvent event) {
//        System.out.println("Processing payment for order: " + event.orderId());
//        kafkaTemplate.send("payment-response", new
//                PaymentResponseEvent(event.orderId(), true, event.userId()));
//    }
//}
//// === InventoryService.java ===
//@Component
//public class InventoryService {
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    public InventoryService(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    @KafkaListener(topics = "reserve-inventory", groupId = "inventory")
//    public void reserveInventory(OrderCreatedEvent event) {
//        System.out.println("Reserving inventory for order: " + event.orderId());
//        kafkaTemplate.send("inventory-response", new
//                InventoryResponseEvent(event.orderId(), true));
//    }
//}
//// === ShippingService.java ===
//@Component
//public class ShippingService {
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    public ShippingService(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    @KafkaListener(topics = "start-shipping", groupId = "shipping")
//    public void shipOrder(OrderCreatedEvent event) {
//        System.out.println("Shipping order: " + event.orderId());
//        kafkaTemplate.send("shipping-response", new
//                ShippingResponseEvent(event.orderId(), true));
//    }
//}
//// === NotificationService.java ===
//@Component
//public class NotificationService {
//    public NotificationService() {}
//    @KafkaListener(topics = "send-notification", groupId = "notification")
//    public void sendNotification(ShippingResponseEvent event) {
//        System.out.println("Notifying customer about shipped order: " +
//                event.orderId());
//    }
//}
//// === application.yml ===
//spring:
//kafka:
//bootstrap-servers: localhost:9092
//consumer:
//group-id: default
//auto-offset-reset: earliest
//key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
//value-deserializer:
//org.springframework.kafka.support.serializer.JsonDeserializer
//properties:
//spring.json.trusted.packages: '*'
//producer:
//key-serializer: org.apache.kafka.common.serialization.StringSerializer
//value-serializer: org.springframework.kafka.support.serializer.JsonSerializer