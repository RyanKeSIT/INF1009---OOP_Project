package io.github.INF1009OOP_Project.Entities.Components;

import io.github.INF1009OOP_Project.UI.ClickEvent;

public class Clickable implements Component{
	private ClickEvent clickEvent;
	private Transform tr;
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
		clickEvent.onClick();
	}
}
