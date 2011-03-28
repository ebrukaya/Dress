package com.dress.client;


import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.DragStartEvent;
import com.smartgwt.client.widgets.events.DragStartHandler;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;

public class MenuImageDragListener implements DragStartHandler, DragStopHandler{

	private  Dress handler;
	
	
	
	public MenuImageDragListener( Dress handler) {
		super();
		this.handler = handler;
	}

	public void onDragStart(DragStartEvent event) {
		handler.setTarget(EventHandler.getDragTarget());
	}

    public void onDragStop(DragStopEvent event) {
		String uri = null;
		if (handler.getTarget()!= null)
		{
			uri = handler.getTarget().getID();
			if (handler.getTarget() instanceof Img)
			{
				Img img_ = ((Img) handler.getTarget());
				uri = img_.getSrc();
                uri = uri.replaceFirst("s\\[1\\]","orig.masked\\[1\\]");
                uri = uri.substring(0,uri.lastIndexOf(".")) + ".png";

				MyImage img = new MyImage(handler.getAreaX(), handler.getAreaY(), 
						img_.getWidth()*2, img_.getHeight()*2, "images/dress/" + uri, handler);

//                img.autoResize();

				handler.setImage(img);
				if (img instanceof MyImage)
					((MyImage)img).addImageListener();
				handler.getGroup().add(img);
				
			}
			handler.setTarget(null);
			//image = img;
		}
		
	}
	
}
