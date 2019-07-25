package lib.listeners;

import java.io.IOException;

import com.aventstack.extentreports.TestListener;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.Test;

public class ExtentReportsListener implements TestListener {

	@Override
	public void onTestStarted(Test test) {
		System.out.println(test.getDescription());
		
	}

	@Override
	public void onNodeStarted(Test node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLogAdded(Test test, Log log) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCategoryAssigned(Test test, Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAuthorAssigned(Test test, Author author) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScreencastAdded(Test test, Screencast screencast) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
