package com.dress.client;


import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class DrawingAreaListener implements
		com.google.gwt.event.dom.client.MouseMoveHandler,
		com.google.gwt.event.dom.client.MouseDownHandler,
		com.google.gwt.event.dom.client.MouseUpHandler {

	private boolean dragStart;

	private int dragX;
	private int dragY;

	private boolean resizeStarted = false;
	private boolean repositionStarted = false;
	private boolean rotationStarted = false;

	
	private  Dress handler;
	

	public DrawingAreaListener( Dress handler) {
		super();
		this.handler = handler;
	}
	
	public void rotate(com.google.gwt.event.dom.client.MouseMoveEvent event)
	{
		int d_w = event.getX() - dragX;
		int degree = 5;
		if (d_w < 0)
		{
			degree = 5;
			if (handler.getImage().getRotation() > 180)
				degree = -5;
		}		
		else 
		{
			degree = -1;
			if (handler.getImage().getRotation() > 180)
				degree = 5;

		}
		degree = handler.getImage().getRotation() + degree; 
		handler.getImage().setRotation(degree);
		handler.getRectResize().setRotation(degree);
	}

	public void resize(com.google.gwt.event.dom.client.MouseMoveEvent event) {
		
		int d_w = event.getX() - dragX;
		int d_h = event.getY() - dragY;
		
		if (d_w != 0)
			d_h = handler.getImage().getHeight() * d_w / handler.getImage().getWidth();
		else if (d_h != 0)
			d_w = handler.getImage().getWidth() * d_h / handler.getImage().getHeight();
		
		int n_width = handler.getImage().getWidth() + d_w;
		int n_height = handler.getImage().getHeight() + d_h;
		
		if (n_width < 30 || n_height < 30)
			return;

		handler.getImage().setWidth(n_width);
		handler.getImage().setHeight(n_height);
		dragY = event.getY();
		dragX = event.getX();

		handler.getRectResize().setWidth(handler.getImage().getWidth() + 2);
		handler.getRectResize().setHeight(handler.getImage().getHeight() + 2);
//		handler.getRectResize().setRotation(handler.getImage().getRotation());
        handler.getGroup().bringToFront(handler.getRectResize());

	}

	public void rePosition(com.google.gwt.event.dom.client.MouseMoveEvent event) {
		handler.setAreaX(event.getX() - handler.getImage().getWidth() / 2);
		handler.setAreaY(event.getY() - handler.getImage().getHeight() / 2);

		handler.getImage().setX(handler.getAreaX());
		handler.getImage().setY(handler.getAreaY());
		handler.getRectResize().setX(handler.getAreaX());
		handler.getRectResize().setY(handler.getAreaY());
//		handler.getRectResize().setRotation(handler.getImage().getRotation());


	}

	public void onMouseMove(com.google.gwt.event.dom.client.MouseMoveEvent event) {


		handler.setAreaX(event.getX());
		handler.setAreaY(event.getY());

        imagine(event);


        if (!dragStart)
			return;
		
		if (handler.getImage() == null)
			return;
		if (!handler.getRectResize().isVisible())
			return;

        if (rotationStarted)
		{
			rotate(event);
			return;
		}

		if (resizeStarted) {			
			resize(event);
			return;
		}
		

		
		if (repositionStarted) {
			rePosition(event);
			return;
		}

	}



	public boolean inRect(int xx, int yy, int x, int y, int w, int h) {

        int x0 = handler.getImage().getX() + handler.getImage().getWidth()/2;
        int y0 = handler.getImage().getY() + handler.getImage().getHeight()/2;

        int r = handler.getImage().getHeight()/2;
        if (handler.getImage().getRotation() != 0)
        {
            x = (int)(x0 + r * Math.sin( (double)(handler.getImage().getRotation())));
            y = (int)(x0 + r * Math.sin( (double)(handler.getImage().getRotation())));
        }

		if (xx > x && xx < x + w && yy > y && yy < y + h)
			return true;
		return false;
	}

    public void imagine(MouseEvent event)
    {
        if (handler.getImage() == null)
            return;

            boolean resizeStarted_ = inRect(event.getX(), event.getY(), handler
					.getImage().getX() + handler.getImage().getWidth() - 10,
					handler.getImage().getY() + handler.getImage().getHeight()
							- 10, 20, 20);

			boolean repositionStarted_ = inRect(event.getX(), event.getY(), handler
					.getImage().getX() + handler.getImage().getWidth() / 4,
					handler.getImage().getY() + handler.getImage().getHeight()
							/ 4, handler.getImage().getWidth() / 2, handler
							.getImage().getHeight() / 2);

			boolean rotationStarted_ = inRect(event.getX(), event.getY(), handler
					.getImage().getX() + handler.getImage().getWidth()/2 - 10,
					handler.getImage().getY() + 5
							, 40, 40);


            if (resizeStarted_)
            {
                handler.getImage().getElement().getStyle().setCursor(Style.Cursor.CROSSHAIR);
                handler.getDrawingArea().getElement().getStyle().setCursor(Style.Cursor.CROSSHAIR);
            }
            else if (rotationStarted_)
            {
                handler.getImage().getElement().getStyle().setCursor(Style.Cursor.HELP);
                handler.getDrawingArea().getElement().getStyle().setCursor(Style.Cursor.HELP);
            }
            else if (repositionStarted_)
            {
                handler.getImage().getElement().getStyle().setCursor(Style.Cursor.MOVE);
                handler.getDrawingArea().getElement().getStyle().setCursor(Style.Cursor.MOVE);
            }
        else
            {
                handler.getImage().getElement().getStyle().setCursor(Style.Cursor.DEFAULT);
                handler.getDrawingArea().getElement().getStyle().setCursor(Style.Cursor.DEFAULT);
            }

    }


	public void onMouseDown(MouseDownEvent event) {
		if (inRect(event.getX(), event.getY(), handler.getRectResize().getX()-10,
				handler.getRectResize().getY()-10, handler.getRectResize()
						.getWidth() + 10,
				handler.getRectResize().getHeight() + 10))

		{
			
			resizeStarted = inRect(event.getX(), event.getY(), handler
					.getImage().getX() + handler.getImage().getWidth() - 10,
					handler.getImage().getY() + handler.getImage().getHeight()
							- 10, 20, 20);

			repositionStarted = inRect(event.getX(), event.getY(), handler
					.getImage().getX() + handler.getImage().getWidth() / 4,
					handler.getImage().getY() + handler.getImage().getHeight()
							/ 4, handler.getImage().getWidth() / 2, handler
							.getImage().getHeight() / 2);

			rotationStarted = inRect(event.getX(), event.getY(), handler
					.getImage().getX() + handler.getImage().getWidth()/2 - 10,
					handler.getImage().getY() + 5
							, 40, 40);
			
			dragStart = true;
			dragX = event.getX();
			dragY = event.getY();

			
			
		} else {
			resizeStarted = false;
			rotationStarted = false;
			handler.getRectResize().setVisible(false);
            handler.getImage().getElement().getStyle().setOpacity(1);
			dragStart = false;
//			Window.alert(handler.getImage().getRotation() + " ");
		}
		
		

	}

	public void onMouseUp(MouseUpEvent event) {
		dragStart = false;

	}
}
