package coeait.g333.orlov.lab08_gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class myimg extends SurfaceView {
    Paint p;
    int w, h;
    int[] d;
    int iw, ih;
    float sx, sy;
    int n;

    public myimg(Context context) {
        super(context);
        init();
    }

    public myimg(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public myimg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        p = new Paint();
        setWillNotDraw(false);
    }

    public void set_data(int nw, int nh, String dat) {
        w = nw;
        h = nh;
        d = new int[w * h];
        for (int i = 0; i < dat.length(); i++) {
            d[i] = Integer.parseInt(dat.substring(i, i + 1));
        }
        invalidate();
    }

    public String get_data() {
        StringBuilder res = new StringBuilder();
        if (d != null) {
            for (int j : d) res.append(j);
        }
        return res.toString();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (d == null || sx == 0 || sy == 0) return true;
            float x = event.getX();
            float y = event.getY();
            int i = (int) (x / sx);
            int j = (int) (y / sy);
            if (i >= 0 && i < w && j >= 0 && j < h) {
                d[j * w + i] = 1 - d[j * w + i];
                invalidate();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (d == null || w == 0 || h == 0) return;
        iw = canvas.getWidth();
        ih = canvas.getHeight();
        sx = (float) iw / w;
        sy = (float) ih / h;
        canvas.drawColor(Color.GRAY);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (d[y * w + x] == 1) p.setColor(Color.WHITE);
                else p.setColor(Color.BLACK);
                float x0 = x * sx;
                float y0 = y * sy;
                canvas.drawRect(x0, y0, x0 + sx - 1, y0 + sy - 1, p);
            }
        }
    }
}
