package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class JSONReportGenerator extends ReportGenerator {

	@Override
	protected Report intantiateReport() {
		// TODO Auto-generated method stub
		return new JSONReport();
	}

}
