package io.github.INF1009OOP_Project.Engine.Entities.Components;

import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;

public class Clickable implements Component{
	private ClickEvent clickEvent;
	private Transform tr;
	private boolean enabled = true;
	
	public Clickable(ClickEvent ce, Transform tr) {
		this.clickEvent = ce;
		this.tr = tr;
	}
	public boolean isHover(float x, float y) {
		if(
				x > tr.getX() && 
				x < tr.getX() + tr.getWidth() &&
				y > tr.getY() &&
				y < tr.getY() + tr.getHeight()
		) {
			return true;
		}
		
		return false;
	}
	public void onClick() {
		if(enabled) {
			clickEvent.onClick();			
		}
	}
	
	public void enable() {
		enabled = true;
	}
	public void disable() {
		enabled = false;
	}
}
