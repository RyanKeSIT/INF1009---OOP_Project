package io.github.INF1009OOP_Project.Engine.Entities.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Renderable;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
//made minimal changes to implement new entity system
public class Button extends Entity{
	public static Texture white1x1 = new Texture("white1x1.png");
	public static Texture gray1x1 = new Texture("gray1x1.png");
	private boolean isOutline = false;
	
	protected Texture background = white1x1;
	
	private Text text;
	private boolean hidden = false;
	
	public Button(float x, float y, float w, float h,String t, float fs, BitmapFont f, ClickEvent e){
		super();
        this.add(new Transform(x, y, w, h));
        this.add(new Clickable(e, this.get(Transform.class)));
        this.add(new Renderable(this, null) {
        	@Override
        	public void draw(SpriteBatch sb) {
        		
        		if(!hidden) {
        			Transform tr = get(Transform.class);
        			sb.draw(background, tr.getX(), tr.getY(), tr.getWidth(), tr.getHeight());
        			text.draw(sb);
        		}
        	}
        });
        this.text = new Text(x,y,w,h,t,fs,Color.BLACK,f);
		this.setText(t);
		this.setFont(f);
		this.setFontSize(fs);
		
	}
	
	public void draw(SpriteBatch sb) {
		if(!hidden) {
			Transform tr = get(Transform.class);
			sb.draw(background, tr.getX(), tr.getY(), tr.getWidth(), tr.getHeight());
			text.draw(sb);
		}
	}
	
	
	public void show() {
		hidden = false;
		this.get(Clickable.class).enable();
	}
	public void hide() {
		hidden = true;
		this.get(Clickable.class).disable();
	}
	
	public boolean isOutline() {
		return isOutline;
	}	
	public void setOutline(boolean isOutline) {
		this.isOutline = isOutline;
	}
	public String getText() {
		return text.getText();
	}
	public void setText(String text) {
		this.text.setText(text);;
	}
	public Texture getBackground() {
		return background;
	}
	public void setBackground(Texture background) {
		this.background = background;
	}
	public Color getTextColor() {
		return text.getTextColor();
	}
	public void setTextColor(Color textColor) {
		this.text.setTextColor(textColor);;
	}
	public BitmapFont getFont() {
		return text.getFont();
	}
	public void setFont(BitmapFont font) {
		this.text.setFont(font);;
	}
	public float getFontSize() {
		return this.text.getFontSize();
	}
	public void setFontSize(float fontSize) {
		this.text.setFontSize(fontSize);
	}
}
