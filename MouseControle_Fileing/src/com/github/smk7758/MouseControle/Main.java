package com.github.smk7758.MouseControle;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws AWTException {
		// Robot robot = new Robot();
		// Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		// robot.mouseMove(screen_size.width / 2, screen_size.height / 2);
		// robot.delay(1000);
		// robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		// robot.delay(100);
		// robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		new Main().shiftMouse();
	}

	public void shiftMouse() throws AWTException {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("ST");
		final Point firstMousePoint = MouseInfo.getPointerInfo().getLocation();

		Robot robot = new Robot();



		Path path = Paths.get("C:\\finger_pencil\\input\\CIMG5896_perspected_points_1570177798840.txt");
		List<Point> points = FileIO.getPoints(path);
		convertPoints(points).forEach(point -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			if(point.x == 0) {
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}else {

				robot.mouseMove(firstMousePoint.x + point.x, firstMousePoint.y + point.y);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//				robot.mouseMove(firstMousePoint.x + point.x, firstMousePoint.y + point.y);
			}
		});
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		Point mousePoint_ = MouseInfo.getPointerInfo().getLocation();
		System.out.println(mousePoint_.x + ", " + mousePoint_.y);
	}

	public List<Point> convertPoints(List<Point> points) {
		List<Point> points_new = new ArrayList<>();
		for (Point point : points) {
			points_new.add(new Point(point.x / 6, point.y / 6));
		}
		return points_new;
	}
}
