package ls223qx_lab1;
import java.util.ArrayList;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
public class SinMain {
	public static void main(String[] args) {
	    XYChart chart = new XYChartBuilder().width(800).height(600).build(); // default resolution
	    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line); // changed graph type to line
	    chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
	    chart.getStyler().setMarkerSize(1); // decreased marker size
	    
	    ArrayList<Double> xData = new ArrayList<>(); // holding arrays for x and y data
	    ArrayList<Double> yData = new ArrayList<>();
	    
	    for (double x = 0; x < 2*Math.PI; x+=0.001d) { // x+= can be adjusted for different resolution, range used is 0 < 2pi.
	    	yData.add((1 + x/Math.PI)*Math.cos(x)*Math.cos(40*x)); // calculate y data at point x
	    	xData.add(x); // keeps track of at which x value the respective y value was calculated
	    }
	    
	    chart.addSeries("Sine", xData, yData); // Add wave to chart
	    new SwingWrapper<>(chart).displayChart(); // Create the visual element of the chart
	}

}
