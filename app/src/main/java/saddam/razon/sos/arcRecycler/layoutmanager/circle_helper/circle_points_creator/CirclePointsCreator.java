package saddam.razon.sos.arcRecycler.layoutmanager.circle_helper.circle_points_creator;



import java.util.Map;

import saddam.razon.sos.arcRecycler.layoutmanager.circle_helper.point.Point;

/**
 * Created by danylo.volokh on 12/4/2015.
 *
 * Implementation should be quadrant-specific and it should "know" in which order point should be created.
 * For example : if we starting to layout views from top to bottom in first quadrant then first point should be (R;0)
 * "R" - radius
 */
public interface CirclePointsCreator {

    void fillCirclePoints(
            Map<Integer, Point> circleIndexPoint,
            Map<Point, Integer> circlePointIndex);
}
