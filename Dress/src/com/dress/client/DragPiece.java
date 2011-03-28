package com.dress.client;

import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.widgets.Img;

public class DragPiece extends Img{
	Dress handler;
	
	public DragPiece(Dress handler) {
		this.handler = handler;
	      setWidth(60);
	      setHeight(90);
	      //setCanDrop(true);
	      setCanDrag(true);
	      setDragAppearance(DragAppearance.OUTLINE);
	      setAppImgDir("dress/");
	    }
	    
	    public DragPiece(String src, Dress handler) {
	      this(handler);
	      super.setSrc(src);	      	      
	    }
	    
	    public void addDragDrop(MenuImageDragListener listener)
	    {
	    	addDragStartHandler(listener);		
			addDragStopHandler(listener);
	    }

}
