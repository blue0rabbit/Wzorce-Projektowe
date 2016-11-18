package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.BookDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerNotificationService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.OrderingService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.WharehouseService;

public class DefaultBookstoreFacade implements BookstoreFacade {
	
	CustomerDBService customerService;
	BookDBService bookService;
	OrderingService orderingService;
	WharehouseService warehouseService;
	CustomerNotificationService customerNotificationService;

	@Override
	public void placeOrder(String customerId, String isbn) {

		Book book = bookService.findBookByISBN(isbn);
		Customer customer = customerService.findCustomerById(customerId);
		Order order = orderingService.createOrder(customer, book);
		customerNotificationService.notifyClient(order);
		DispatchReceipt dummyDispatchReceipt = warehouseService.dispatch(order);
		customerNotificationService.notifyClient(dummyDispatchReceipt);

	}

	public void setCustomerService(CustomerDBService customerService) {
		this.customerService = customerService;

	}


	public void setBookService(BookDBService bookService) {
		this.bookService = bookService;

	}

	public void setBookService(OrderingService orderingService) {

		this.orderingService = orderingService;
	}

	
	public void setWarehouseService(WharehouseService warehouseService) {
		this.warehouseService = warehouseService;

	}


	public void setNotificationService(CustomerNotificationService customerNotificationService) {
		this.customerNotificationService = customerNotificationService;

	}
}
