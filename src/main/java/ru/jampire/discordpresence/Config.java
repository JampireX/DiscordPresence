package ru.jampire.discordpresence;

import com.google.gson.GsonBuilder;

public class Config {

	private String clientId;
	private String state;
	private String details;
	private Image bigImage;
	private Image smallImage;

	public String getClientId() {
		return this.clientId;
	}

	public String getState() {
		return this.state;
	}

	public String getDetails() {
		return this.details;
	}

	public Image getBigImage() {
		return this.bigImage;
	}

	public Image getSmallImage() {
		return this.smallImage;
	}

	@Override
	public String toString() {
		return new GsonBuilder().create().toJson(this);
	}

	public class Image {

		private String key;
		private String text;

		public String getKey() {
			return this.key;
		}

		public String getText() {
			return this.text;
		}

		@Override
		public String toString() {
			return new GsonBuilder().create().toJson(this);
		}
	}
}