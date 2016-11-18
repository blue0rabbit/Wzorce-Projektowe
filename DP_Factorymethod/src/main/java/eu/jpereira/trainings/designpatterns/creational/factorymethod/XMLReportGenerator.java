package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class XMLReportGenerator extends ReportGenerator {

	@Override
	protected Report intantiateReport() {
		// TODO Auto-generated method stub
		return new XMLReport();
	}

}
