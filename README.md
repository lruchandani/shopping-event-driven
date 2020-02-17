# shopping-event-driven
CQRS with kafka , along with the demo of Rsocket

* Adding an [Order](http://localhost:8080/orders) in OrderService fires OrderCreatedEvent into Kafka which is processed by the ProductService to update the inventory.
* Also contains an RSocket demo with the client NumberController in the OrderService  calling and endpoint on ProductService.


