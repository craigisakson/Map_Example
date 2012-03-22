package com.runninghusky.spacetracker.map.example;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

/**
 * The Class StartActivity which is the activity that is started when the user
 * opens the application.
 */
public class StartActivity extends MapActivity {

	/** The map over lays. */
	private List<Overlay> mapOverLays;

	/** The projection. */
	private Projection projection;

	/** The map view. */
	MapView mapView;

	/** The map controller. */
	MapController mapC;

	/** The geopoint. */
	GeoPoint p;

	/** The location name. */
	String locationName;

	/** The points. */
	private List<Coordinate> points = new ArrayList<Coordinate>();

	/**
	 * Prevents activity from restarting on configuration change
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/**
	 * Called when activity is first created
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//load the coordinates to draw
		loadPoints();

		mapView = (MapView) findViewById(R.id.maps);
		mapView.setBuiltInZoomControls(true);

		mapOverLays = mapView.getOverlays();
		projection = mapView.getProjection();
		mapOverLays.add(new MyOverlay());
		mapC = mapView.getController();

		// The point to use to set as the center of the map
		p = new GeoPoint((int) (40.847061 * 1E6), (int) (-98.384768 * 1E6));
		try {
			//animates map to center
			mapC.animateTo(p);
			//sets the zoom level of the map
			mapC.setZoom(5);
		} catch (Exception e) {
			Log.d("exc", String.valueOf(e));
		}
	}

	/**
	 * Is route displayed
	 * @return 
	 */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	/**
	 * The Class MapsOverlay.
	 */
	class MapsOverlay extends com.google.android.maps.Overlay {

		/**
		 * The draw method 
		 * @param canvas
		 *				the canvas
		 * @param mapv
		 *				the mapview
		 * @param shadow
		 *				the shadow
		 * @param when
		 * 				when to draw
		 */
		public boolean draw(Canvas canvas, MapView mapview, boolean shadow,
				long when) {
			super.draw(canvas, mapview, shadow);
			Point screenPts = new Point();
			// converting geoPoint to screen co-ordinates
			mapview.getProjection().toPixels(p, screenPts);

			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.bubble);
			canvas.drawBitmap(bmp, screenPts.x - 12, screenPts.y - 34, null);
			return true;
		}
	}

	/**
	 * The Class MyOverlay.
	 */
	class MyOverlay extends Overlay {
		/**
		 * Instantiates a new my overlay.
		 */
		public MyOverlay() {
		}

		/**
		 * This method is where the drawing actually occurs.  
		 * @param canvas
		 *				the canvas
		 * @param mapv
		 *				the mapview
		 * @param shadow
		 *				the shadow
		 * 
		 */
		public void draw(Canvas canvas, MapView mapv, boolean shadow) {
			if (!shadow) {
				super.draw(canvas, mapv, shadow);
			}

			//Set the properties for the paint object
			Paint mPaint = new Paint();
			mPaint.setDither(true);
			mPaint.setColor(Color.RED);
			mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(4);
			Path path = new Path();

			//Loop through the points for drawing
			//Because we work with two points (i and i+1),
			//we loop while i < points - 1.
			for (int i = 0; i < points.size() - 1; i++) {
				//Checks to see if the point is zero, if it is
				//we will skip it and draw the next points.  This allows
				//breaks in the lines we are drawing
				if ((points.get(i + 1).getLatitude()) != 0) {
					GeoPoint gP1 = new GeoPoint((int) (points.get(i)
							.getLatitude() * 1E6), (int) (points.get(i)
							.getLongitude() * 1E6));
					GeoPoint gP2 = new GeoPoint((int) (points.get(i + 1)
							.getLatitude() * 1E6), (int) (points.get(i + 1)
							.getLongitude() * 1E6));

					Point p1 = new Point();
					Point p2 = new Point();
					path = new Path();

					projection.toPixels(gP1, p1);
					projection.toPixels(gP2, p2);

					path.moveTo(p2.x, p2.y);
					path.lineTo(p1.x, p1.y);
					canvas.drawPath(path, mPaint);
				} else {
					i++;
				}
			}
		}
	}

	/**
	 * Loads the coordinates that we are going to draw.
	 */
	private void loadPoints() {
		points.add(new Coordinate(-118.817375, 38.168270));
		points.add(new Coordinate(-116.092766, 43.484035));
		points.add(new Coordinate(-113.807610, 38.202812));
		points.add(new Coordinate(-114.950188, 40.746445));
		points.add(new Coordinate(-117.586906, 40.779728));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-112.921844, 38.293411));
		points.add(new Coordinate(-112.921844, 40.633987));
		points.add(new Coordinate(-110.197235, 40.600632));
		points.add(new Coordinate(-110.197235, 38.396809));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-108.703094, 43.344379));
		points.add(new Coordinate(-108.659149, 38.603161));
		points.add(new Coordinate(-104.572235, 38.671810));
		points.add(new Coordinate(-105.143524, 43.152325));
		points.add(new Coordinate(-108.790985, 43.408264));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-101.056610, 38.979935));
		points.add(new Coordinate(-103.473602, 39.014088));
		points.add(new Coordinate(-103.649384, 41.429569));
		points.add(new Coordinate(-100.265594, 41.396614));
		points.add(new Coordinate(-100.485321, 40.165466));
		points.add(new Coordinate(-103.473602, 40.299664));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-99.474579, 41.297642));
		points.add(new Coordinate(-98.288055, 38.843155));
		points.add(new Coordinate(-96.881805, 41.132355));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-93.102509, 42.475361));
		points.add(new Coordinate(-95.958954, 42.669537));
		points.add(new Coordinate(-96.002899, 38.945763));
		points.add(new Coordinate(-92.707001, 38.843155));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-91.959930, 41.099247));
		points.add(new Coordinate(-92.003876, 38.911575));
		points.add(new Coordinate(-89.498993, 38.877373));
		points.add(new Coordinate(-89.367157, 41.066124));
		points.add(new Coordinate(-91.872040, 41.099247));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-88.664032, 38.808918));
		points.add(new Coordinate(-88.620087, 40.966648));
		points.add(new Coordinate(-86.115204, 40.966648));
		points.add(new Coordinate(-86.071259, 38.671810));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-84.862762, 42.475361));
		points.add(new Coordinate(-84.511200, 38.671810));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-83.544403, 42.280582));
		points.add(new Coordinate(-82.929169, 38.603161));
		points.add(new Coordinate(0, 0));
		points.add(new Coordinate(-82.313934, 42.085201));
		points.add(new Coordinate(-81.259247, 38.603161));
	}

	/**
	 * Creates the options menu
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 2, 0, "ChangeView");
		return true;
	}

	/**
	 * When the menu item is clicked
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		final MapController mc = mapView.getController();
		switch (item.getItemId()) {
		case 2:
			//Generate a new dialog box to switch map views
			AlertDialog dialog = new AlertDialog.Builder(StartActivity.this)
					.setItems(R.array.selectMapView,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {
										mapView.setSatellite(true);
									} else if (which == 1) {
										mapView.setSatellite(false);
									}
								}

							}).create();
			dialog.setTitle("Select MapView");
			dialog.show();
			break;
		}
		return true;
	}

}