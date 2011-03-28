package com.dress.client;


import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.FlowLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Dress implements EntryPoint {
	private DrawingArea drawingArea;
	private Image image;
	private Rectangle rect;
	private Group group;
	
	private Rectangle rectResize;
	private  VLayout vLayout;
	private Canvas target;
	
	private int areaX;
	private int areaY;

	RootPanel rootPanel;
     Label listingLabel ;
	
	
	
	public DrawingArea getDrawingArea() {
		return drawingArea;
	}


	public void setDrawingArea(DrawingArea drawingArea) {
		this.drawingArea = drawingArea;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(MyImage image) {
		this.image = image;
	}


	public Rectangle getRect() {
		return rect;
	}


	public void setRect(Rectangle rect) {
		this.rect = rect;
	}


	public Group getGroup() {
		return group;
	}


	public void setGroup(Group group) {
		this.group = group;
	}


	public Rectangle getRectResize() {
		return rectResize;
	}
	
	public RootPanel getPanel()
	{
		return rootPanel;
	}


	public void setRectResize(Rectangle rectResize) {
		this.rectResize = rectResize;
	}
	

	public Canvas getTarget() {
		return target;
	}


	public void setTarget(Canvas target) {
		this.target = target;
	}


	public int getAreaX() {
		return areaX;
	}


	public void setAreaX(int areaX) {
		this.areaX = areaX;
	}


	public int getAreaY() {
		return areaY;
	}


	public void setAreaY(int areaY) {
		this.areaY = areaY;
	}


	public void onModuleLoad() {
		rootPanel = RootPanel.get();
		HLayout mainLayout = new HLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();
				
		
	    Canvas canvas = getDressMenu();
//	    canvas.setWidth("50%");
	    canvas.setShowResizeBar(true);







	    mainLayout.addMember(canvas);

	    vLayout = new VLayout();
	    vLayout.setWidth("90%");
	   
	    listingLabel = new Label();
	    listingLabel.setContents("Listing");
	    listingLabel.setAlign(Alignment.CENTER);
	    listingLabel.setOverflow(Overflow.HIDDEN);
	    listingLabel.setHeight("40");
	    listingLabel.setShowResizeBar(true);
	    listingLabel.setBorder("1px solid blue");

	    DrawingArea area = getImageEditArea();
	    //area.setHeight(800);
	    area.setWidth(900);
	    
	    

	    vLayout.addMember(getMenuBar());
	    vLayout.addMember(area);

	    mainLayout.addMember(vLayout);
	    
		rootPanel.add(mainLayout);
		
	}


	public Canvas getMenuBar()
    {

        IButton sil = new IButton("Sil");
        sil.setIcon("dress/icons/Delete-icon.png");
        IButton kopyala = new IButton("Kopyala");
        kopyala.setIcon("dress/icons/copy-icon.png");
        IButton one = new IButton("Öne getir");
        one.setIcon("dress/icons/next-icon.png");

        IButton arkaya = new IButton("Arkaya Gönder");
        arkaya.setIcon("dress/icons/left-icon.png");

        IButton buyut = new IButton("Büyüt");
        buyut.setIcon("dress/icons/Zoom-In-icon.png");

        IButton kucult = new IButton("Küçült");
        kucult.setIcon("dress/icons/Zoom-Out-icon.png");

        IButton ortala = new IButton("Ortala");
        ortala.setIcon("dress/icons/stop-icon.png");

        IButton sakla= new IButton("Sakla");
        sakla.setIcon("dress/icons/Open-icon.png");
        IButton yayinla= new IButton("Yayınla");
        yayinla.setIcon("dress/icons/Check-icon.png");



        ortala.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                image.setStyleName("flip-horizontal");
            }
        });



        sil.setWidth(80);
        kopyala.setWidth(100);
        one.setWidth(100);
        arkaya.setWidth(120);
        buyut.setWidth(80);
        kucult.setWidth(80);
        ortala.setWidth(100);
        sakla.setWidth(80);
        yayinla.setWidth(100);


        HLayout hLayout = new HLayout();
        hLayout.setHeight(30);
        hLayout.setMembersMargin(5);
        hLayout.addMember(sil);
        hLayout.addMember(kopyala);
        hLayout.addMember(one);
        hLayout.addMember(arkaya);



        VLayout main = new VLayout();

        HLayout layout1 = new HLayout();
        layout1.setHeight(30);
        main.setHeight(60);
        main.addMember(layout1);
        main.addMember(hLayout);


        layout1.addMember(buyut);
        layout1.addMember(kucult);
        layout1.addMember(ortala);
        layout1.addMember(sakla);
        layout1.addMember(yayinla);



        one.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                group.bringToFront(image);
            }
        });


        sil.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                group.remove(image);
                rectResize.setVisible(false);
                image = null;
            }
        });

        return main;
    }



    String dressNames[] = {"thing.30546020.s[1].jpg","thing.31129338.s[1].jpg","thing.31381003.s[1].jpg","thing.31541565.s[1].jpg","thing.31683271.s[1].jpg","thing.31904025.s[1].jpg",
        "thing.30553797.s[1].jpg","thing.31204461.s[1].jpg","thing.31426250.s[1].jpg","thing.31554893.s[1].jpg","thing.31700080.s[1].jpg","thing.31965555.s[1].jpg",
        "thing.31064496.s[1].jpg","thing.31295066.s[1].jpg","thing.31438238.s[1].jpg","thing.31568807.s[1].jpg","thing.31852325.s[1].jpg","thing.31965597.s[1].jpg",
        "thing.31120922.s[1].jpg","thing.31332721.s[1].jpg","thing.31471828.s[1].jpg","thing.31569088.s[1].jpg","thing.31873347.s[1].jpg","thing.31980845.s[1].jpg"};


    String shoeNames[] = {"thing.30154865.s[1].jpg","thing.31064509.s[1].jpg","thing.31426594.s[1].jpg","thing.31528704.s[1].jpg","thing.31570196.s[1].jpg","thing.31705756.s[1].jpg","thing.31939013.s[1].jpg",
        "thing.30653355.s[1].jpg","thing.31335482.s[1].jpg","thing.31460390.s[1].jpg","thing.31530585.s[1].jpg","thing.31632565.s[1].jpg","thing.31834840.s[1].jpg",
        "thing.30740787.s[1].jpg","thing.31338595.s[1].jpg","thing.31464053.s[1].jpg","thing.31550945.s[1].jpg","thing.31643758.s[1].jpg","thing.31852323.s[1].jpg",
        "thing.30952551.s[1].jpg","thing.31397085.s[1].jpg","thing.31472442.s[1].jpg","thing.31569189.s[1].jpg","thing.31648075.s[1].jpg","thing.31880107.s[1].jpg",
        "thing.31026977.s[1].jpg","thing.31413519.s[1].jpg","thing.31484502.s[1].jpg","thing.31569937.s[1].jpg","thing.31665520.s[1].jpg","thing.31926927.s[1].jpg"};

	public Canvas getDressMenu()
	{


        FlowLayout layout = new FlowLayout();


        Canvas canvas = new Canvas();
        FlowLayout canvasShoes = new FlowLayout();

        MenuImageDragListener listener = new MenuImageDragListener(this);


        for (int i = 0; i < dressNames.length; i++) {
            String dressName = dressNames[i];
            String pngName = dressName.replaceFirst("s\\[1\\]","orig.masked\\[1\\]");
             DragPiece d = new DragPiece("elbise/" + dressName, this);
             d.addDragDrop(listener);
             layout.addTile(d);
        }

        for (int i = 0; i < shoeNames.length; i++) {
            String shoeName = shoeNames[i];
            String pngName = shoeName.replaceFirst("s\\[1\\]","orig.masked\\[1\\]");
             DragPiece d = new DragPiece("ayakkabi/" + shoeName, this);
             d.addDragDrop(listener);
             canvasShoes.addTile(d);
        }



//        DragPiece d1 = new DragPiece("elbise/thing.30194127.jpg", this);
//        d1.setWidth(260 / 4);
//        d1.setHeight(400 / 4);
//
//        DragPiece d2 = new DragPiece("elbise/thing.31025051.jpg", this);
//        d2.setWidth(200/4);
//        d2.setHeight(400/4);
//
//        DragPiece d3 = new DragPiece("elbise/thing.31287815.jpg", this);
//        d3.setWidth(192/4);
//        d3.setHeight(345/4);
//
//
//        DragPiece d4 = new DragPiece("elbise/thing.31401482.jpg", this);
//        d4.setWidth(276/4);
//        d4.setHeight(400/4);
//
//        DragPiece d5 = new DragPiece("elbise/thing.31554893.jpg", this);
//        d5.setWidth(215/4);
//        d5.setHeight(400/4);
//
//        DragPiece d6 = new DragPiece("elbise/thing.31569088.jpg", this);
//        d5.setWidth(197/4);
//        d5.setHeight(400/4);
//
//
//		d1.addDragDrop(listener);
//		d2.addDragDrop(listener);
//		d3.addDragDrop(listener);
//        d4.addDragDrop(listener);
//        d5.addDragDrop(listener);
//        d6.addDragDrop(listener);
//
//
//		layout.addTile(d1);
//		layout.addTile(d2);
//		layout.addTile(d3);
//
//        layout.addTile(d4);
//        layout.addTile(d5);
//        layout.addTile(d6);


//
//        DragPiece s1 = new DragPiece("ayakkabi/a1.jpg", this);
//        DragPiece s2 = new DragPiece("ayakkabi/a2.jpg", this);
//        DragPiece s3 = new DragPiece("ayakkabi/a3.jpg", this);
//
//        DragPiece s4 = new DragPiece("ayakkabi/a4.jpg", this);
//        DragPiece s5 = new DragPiece("ayakkabi/a5.jpg", this);
//        DragPiece s6 = new DragPiece("ayakkabi/a6.jpg", this);
//
//		s1.setLeft(10);
//		s1.setTop(50);
//
//		s2.setLeft(10);
//		s2.setTop(110);
//
//		s3.setLeft(10);
//		s3.setTop(180);
//
//		s1.addDragDrop(listener);
//		s2.addDragDrop(listener);
//		s3.addDragDrop(listener);
//        s4.addDragDrop(listener);
//        s5.addDragDrop(listener);
//        s6.addDragDrop(listener);
//
//		canvasShoes.addTile(s1);
//		canvasShoes.addTile(s2);
//		canvasShoes.addTile(s3);
//        canvasShoes.addTile(s4);
//        canvasShoes.addTile(s5);
//        canvasShoes.addTile(s6);


        TabSet tabSet = new TabSet();
        tabSet.setTabBarPosition(Side.TOP);
        tabSet.setTabBarThickness(25);
        tabSet.setTabBarAlign(Side.LEFT);
        tabSet.setWidth(250);
        tabSet.setHeight(800);




        Tab elbiseler = new Tab("Elbiseler");
        elbiseler.setPane(layout);
		tabSet.addTab(elbiseler);

        Tab ayakkabilar = new Tab("Ayakkabılar");
        ayakkabilar.setPane(canvasShoes);
		tabSet.addTab(ayakkabilar);


		
		return tabSet;
	}

    public Rectangle getNewRectResize()
    {
        if (rectResize !=null)
        {
            group.remove(rectResize);
        }
        rectResize = new Rectangle(0, 0, 5, 5);
        rectResize.setStrokeOpacity(0.5);
        rectResize.setVisible(false);
        rectResize.setFillOpacity(0);
        rectResize.setFillColor("#FFFFFF");
        group.add(rectResize);
        return rectResize;
    }
	
	public DrawingArea getImageEditArea()
	{
		drawingArea = new DrawingArea(800, 600);
		
		rect = new Rectangle(0, 0, 0, 0);
		rect.setStrokeColor("#FF0000");
		
		/*image = new Image(0, 0, 50, 50, "http://www.gstatic.com/codesite/ph/images/defaultlogo.png");
		 * group.add(image);
		*/
		group = new Group();
		drawingArea.add(group);
		
		group.add(rect);
		
		rect.setVisible(false);
		rect.setFillOpacity(1);
		
		getNewRectResize();
		
		DrawingAreaListener drawingAreaListener = new DrawingAreaListener(this);
		
		drawingArea.addMouseMoveHandler(drawingAreaListener);		
		drawingArea.addMouseDownHandler(drawingAreaListener);
		drawingArea.addMouseUpHandler(drawingAreaListener);
		
		
		return drawingArea;
	}
	
	
}
