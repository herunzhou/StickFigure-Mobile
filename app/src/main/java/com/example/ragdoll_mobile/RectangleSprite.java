package com.example.ragdoll_mobile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

public class RectangleSprite extends Sprite {

    public float roundEdge;

    public RectangleSprite(int xPosition, int yPosition, int width, int height, float roundEdge, boolean oval_or_not, String body) {
        super();
        this.roundEdge = roundEdge;
        this.oval = oval_or_not;
        this.bodyPart = body;
        this.width = width;
        this.height = height;

        if (body == "head") {
            this.pivot.x = width/2;
            this.pivot.y = height;
        } else if (body == "leftfoot") {
            this.pivot.x = width;
            this.pivot.y = height/2;
        } else if (body == "leftupperleg" || body == "rightupperleg"||
                   body == "leftlowerleg" || body == "rightlowerleg") {
            this.pivot.x = width/2;
            this.pivot.y = 0;
        } else if (body == "rightfoot") {
            this.pivot.x = 0;
            this.pivot.y = height/2;
        } else if (body == "leftupperarm") {
            this.pivot.x = width;
            this.pivot.y = height/2;
        } else if (body == "rightupperarm") {
            this.pivot.x = 0;
            this.pivot.y = height/2;
        } else if (body == "leftlowerarm") {
            this.pivot.x = width;
            this.pivot.y = height/2;
        } else if (body == "rightlowerarm") {
            this.pivot.x = 0;
            this.pivot.y = height/2;
        } else if (body == "lefthand") {
            this.pivot.x = width;
            this.pivot.y = height/2;
        } else if (body == "righthand") {
            this.pivot.x = 0;
            this.pivot.y = height/2;
        } else if (body == "dogfrontleg") {
            this.pivot.x = width/2;
            this.pivot.y = 0;
        } else if (body == "dogbackleg") {
            this.pivot.x = width/2;
            this.pivot.y = 0;
        } else if (body == "dogtail") {
            this.pivot.x = 0;
            this.pivot.y = height/2;
        } else if (body == "doghead") {
            this.pivot.x = width;
            this.pivot.y = height / 2;
        }

        this.initialize(xPosition, yPosition, width,height);
    }

    public void initialize(int xPosition, int yPosition, int width, int height) {
        rect = new RectF(xPosition, yPosition, xPosition + width, yPosition + height);
        // rect = new RectF(700, 500, 700+width, 500+height);
    }

    public boolean pointInside(PointF p) {

        Matrix fullTransform = this.getFullTransform();
        Matrix inverseTransform = new Matrix();

        if (fullTransform.invert(inverseTransform)) {
            // Everything is Good
            Log.d("herun: ", "Successfully Inverted");
        } else {
            Log.d("herun: ", "Failed to Invert");
        }

        PointF newPoint = new PointF(p.x, p.y);
        float[] pts = new float[2];
        pts[0] = newPoint.x;
        pts[1] = newPoint.y;
        inverseTransform.mapPoints(pts);
        newPoint.x = pts[0];
        newPoint.y = pts[1];

        if (rect.contains(newPoint.x, newPoint.y)) {
            Log.d("herun: ", "THIS INVERSION WORKEDDDDD!");
        } else {
            Log.d("herun: ", "THIS INVERSION DID NOT WORK!!!");
        }
        return rect.contains(newPoint.x, newPoint.y);
    }

    public void drawSprite(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        if (oval) {
            Log.d("herun: ", "DREW OVALLLLLLL!!!!!!!");
            canvas.drawOval(rect, paint);
        } else {
            canvas.drawRoundRect(rect, roundEdge, roundEdge, paint);
        }

    }

    public String toString() {
        return "RectangleSprite: " + rect;
    }

}
