package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

public class HTMLReportBodyBuilder implements ReportBodyBuilder {

private HTMLReportBody html = new HTMLReportBody();
	
	@Override
	public ReportBodyBuilder setCustomerName(String customerName) {
		html.putContent("<span class=\"customerName\">");
		html.putContent(customerName);
		return this;
	}

	@Override
	public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
		html.putContent("</span><span class=\"customerPhone\">");
		html.putContent(phoneNumber);
		html.putContent("</span>");
		return this;
	}

	@Override
	public ReportBodyBuilder withItems() {
		html.putContent("<items>");
		return this;
	}

	@Override
	public ReportBodyBuilder newItem(String name, int quantity, double price) {
		html.putContent("<item><name>");
		html.putContent(name);
		html.putContent("</name><quantity>");
		html.putContent(""+quantity);
		html.putContent("</quantity><price>");
		html.putContent(""+price);
		html.putContent("</price></item>");
		return this;
	}

	@Override
	public ReportBody getReportBody() {
		html.putContent("</items>");
		return html;
	}

}
