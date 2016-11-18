package eu.jpereira.trainings.designpatterns.creational.builder.json;

import eu.jpereira.trainings.designpatterns.creational.builder.ReportBodyBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

public class JSONReportBodyBuilder implements ReportBodyBuilder{
	private JSONReportBody json = new JSONReportBody();
	
	@Override
	public ReportBodyBuilder setCustomerName(String customerName) {
		json.addContent("sale:{customer:{");
		json.addContent("name:\"");
		json.addContent(customerName);
		return this;
	}

	@Override
	public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
		json.addContent("\",phone:\"");
		json.addContent(phoneNumber);
		json.addContent("\"}");
		return this;
	}

	@Override
	public ReportBodyBuilder withItems() {
		json.addContent(",items:[");
		return this;
	}

	@Override
	public ReportBodyBuilder newItem(String name, int quantity, double price) {
		json.addContent("{name:\"");
		json.addContent(name);
		json.addContent("\",quantity:");
		json.addContent(""+quantity);
		json.addContent(",price:");
		json.addContent(""+price);
		json.addContent("},");
		return this;
	}

	@Override
	public ReportBody getReportBody() {
		json.deleteLastChar();
		json.addContent("]}");
		return json;
	}

}
