package eu.jpereira.trainings.designpatterns.creational.builder.xml;

import eu.jpereira.trainings.designpatterns.creational.builder.ReportBodyBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

public class XMLBodyReportBuilder implements ReportBodyBuilder{
	private XMLReportBody xml = new XMLReportBody();
	
	
	@Override
	public ReportBodyBuilder setCustomerName(String customerName) {
		xml.putContent("<sale><customer><name>");
		xml.putContent(customerName);
		return this;
	}

	@Override
	public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
		xml.putContent("</name><phone>");
		xml.putContent(phoneNumber);
		xml.putContent("</phone></customer>");
		return this;
	}

	@Override
	public ReportBodyBuilder withItems() {
		xml.putContent("<items>");
		return this;
	}

	@Override
	public ReportBodyBuilder newItem(String name, int quantity, double price) {
		xml.putContent("<item><name>");
		xml.putContent(name);
		xml.putContent("</name><quantity>");
		xml.putContent(quantity);
		xml.putContent("</quantity><price>");
		xml.putContent(price);
		xml.putContent("</price></item>");
		return this;
	}

	@Override
	public ReportBody getReportBody() {
		xml.putContent("</items></sale>");
		return xml;
	}

}
