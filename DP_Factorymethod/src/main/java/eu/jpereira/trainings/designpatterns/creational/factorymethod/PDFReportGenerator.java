package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class PDFReportGenerator extends ReportGenerator {

	@Override
	protected Report intantiateReport() {
		// TODO Auto-generated method stub
		return new PDFReport();
	}

}
