package com.trisoft.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class GraficoMB implements Serializable {

	private LineChartModel lineModel1;
	private LineChartModel lineModel2;

	@PostConstruct
	public void init() {
		createLineModels();
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	private void createLineModels() {
		lineModel1 = initLinearModel();
		lineModel1.setTitle("Linear Chart");
		lineModel1.setLegendPosition("e");
		lineModel1.setAnimate(true);
		lineModel1.setSeriesColors("3A6598,EFA252, 009470, B00000");
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);

		lineModel2 = initCategoryModel();
		lineModel2.setTitle("Category Chart");
		lineModel2.setLegendPosition("e");
		lineModel2.setShowPointLabels(false);
		lineModel2.setAnimate(true);
		lineModel2.setSeriesColors("AB0107, 333333");
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
		yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		LineChartSeries series3 = new LineChartSeries();
		series3.setLabel("Series 4");

		series3.set(1, 2);
		series3.set(2, 3);
		series3.set(3, 2);
		series3.set(4, 5);
		series3.set(5, 1);

		LineChartSeries series4 = new LineChartSeries();
		series4.setLabel("Series 4");

		series4.set(1, 8);
		series4.set(2, 5);
		series4.set(3, 7);
		series4.set(4, 1);
		series4.set(5, 6);

		model.addSeries(series1);
		model.addSeries(series2);
		model.addSeries(series3);
		model.addSeries(series4);

		return model;
	}

	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 90);
		girls.set("2008", 120);

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}

}
