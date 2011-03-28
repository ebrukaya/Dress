package com.dress.client;

import com.reveregroup.gwt.imagepreloader.FitImage;
import com.reveregroup.gwt.imagepreloader.FitImageLoadEvent;
import com.reveregroup.gwt.imagepreloader.FitImageLoadHandler;
import org.vaadin.gwtgraphics.client.Image;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;


public class MyImage extends Image 
{
	 Dress handler;
	
	MyImage me;
    int w = 0;
    int h = 0;
	
	public MyImage(int x, int y, int width, int height, String href,
			 Dress handler) {
		super(x, y, width, height, href);

        FitImage image = new FitImage();
        image.addFitImageLoadHandler(new FitImageLoadHandler() {
        public void imageLoaded(FitImageLoadEvent event) {
    //        if (event.isLoadFailed())
    //            Window.alert("Image " + event.getFitImage().getUrl() + " failed to load.");
    //        else
             w= event.getFitImage().getOriginalWidth();
            h = event.getFitImage().getOriginalHeight();
            setWidth(w);
            setHeight(h);
    //            Window.alert("Image " + event.getFitImage().getUrl() + " loaded ("
    //                + event.getFitImage.getOriginalWidth() + " x "
    //                + event.getFitImage.getOriginalHeight() + ")");
        }
    });

        image.setUrl(href);

//        w = image.getOriginalWidth();
//        h = image.getOriginalHeight();


		this.handler = handler;
		me = this;
		getStyleElement().setAttribute("opacity", "1");		
		getStyleElement().setAttribute("background-color", "transparent");
		
	}

    public void autoResize()
    {
        setWidth(w);
        setHeight(h);
    }

	public void addImageListener()
	{
		ImageListener listener = new ImageListener(this);
		addMouseDownHandler(listener);
		
	}

	public void setBackground(String bg)
	{
		getStyleElement().setAttribute("background-color", bg);
	}
	
	public class ImageListener implements MouseDownHandler
	{
		Image img;
		public ImageListener(Image image)
		{
			this.img = image;
		}
		
		public void onMouseDown(MouseDownEvent event) {
			if (handler.getImage() != null)
			{
				((MyImage)handler.getImage()).setBackground("#FFFFFF");				
			}

            handler.getImage().getElement().getStyle().setOpacity(1);

			handler.setImage(me);		
            handler.getNewRectResize();


            handler.getRectResize().setWidth(img.getWidth()+2);
			handler.getRectResize().setHeight(img.getHeight()+2);
			handler.getRectResize().setX(img.getX()-1);
			handler.getRectResize().setY(img.getY()-1);
			handler.getRectResize().setVisible(true);
			handler.getRectResize().setRotation(me.getRotation());

			handler.getDrawingArea().bringToFront(handler.getRectResize());
			setBackground("transparent");
            me.getElement().getStyle().setOpacity(0.5);
			
		}
		
	}
}
