package se.freefarm.fruit.rest;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Report {

    public static void main(String args[]) {
        new Report().createReport();
    }
    public void createReport() {
        File reportOutputDirectory = new File("target/cucumber-report");
        List<String> jsonFiles = new ArrayList<String>();

        URL url=getClass().getResource("/");
        String path=url.getPath();
        String fullPath=path+ "/../cucumber-report/cucumber.json";
        File f = new File(fullPath);
        if (f.exists()) {
            jsonFiles.add(fullPath);
        }
        else {
            System.out.println("No report file found. Should have been "+f.getAbsolutePath());
            return;
        }


        String buildNumber = "1";
        String projectName = "cucumberProject";
        boolean runWithJenkins = false;
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration
        configuration.setParallelTesting(parallelTesting);
        configuration.setRunWithJenkins(runWithJenkins);
        configuration.setBuildNumber(buildNumber);
        // addidtional metadata presented on main page
        configuration.addClassifications("Branch", "release/1.0");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
        System.out.println("open ./target/cucumber-report/cucumber-html-reports/overview-features.html");

    }
}
