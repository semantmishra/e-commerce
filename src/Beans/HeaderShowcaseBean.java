package Beans;

import java.io.InputStream;

public class HeaderShowcaseBean {
	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String title_size ,title_image ,title_color,subtitle_size,subtitle_color,v_align,	h_align,title_text,	subtitle_text,buttons,file_data;
	public String getTitle_image() {
		return title_image;
	}

	public void setTitle_image(String title_image) {
		this.title_image = title_image;
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getTitle_size() {
		return title_size;
	}

	public void setTitle_size(String title_size) {
		this.title_size = title_size;
	}

	public String getTitle_color() {
		return title_color;
	}

	public void setTitle_color(String title_color) {
		this.title_color = title_color;
	}

	public String getSubtitle_size() {
		return subtitle_size;
	}

	public void setSubtitle_size(String subtitle_size) {
		this.subtitle_size = subtitle_size;
	}

	public String getSubtitle_color() {
		return subtitle_color;
	}

	public void setSubtitle_color(String subtitle_color) {
		this.subtitle_color = subtitle_color;
	}

	public String getV_align() {
		return v_align;
	}

	public void setV_align(String v_align) {
		this.v_align = v_align;
	}

	public String getH_align() {
		return h_align;
	}

	public void setH_align(String h_align) {
		this.h_align = h_align;
	}

	public String getTitle_text() {
		return title_text;
	}

	public void setTitle_text(String title_text) {
		this.title_text = title_text;
	}

	public String getSubtitle_text() {
		return subtitle_text;
	}

	public void setSubtitle_text(String subtitle_text) {
		this.subtitle_text = subtitle_text;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public String getFile_data() {
		return file_data;
	}

	public void setFile_data(String file_data) {
		this.file_data = file_data;
	}

	@Override
	public String toString() {
		return "HeaderShowcaseBean [title_size=" + title_size + ", title_color=" + title_color + ", subtitle_size="
				+ subtitle_size + ", subtitle_color=" + subtitle_color + ", v_align=" + v_align + ", h_align=" + h_align
				+ ", title_text=" + title_text + ", subtitle_text=" + subtitle_text + ", buttons=" + buttons
				+ ", file_data=" + file_data + "]";
	}	

	
}
