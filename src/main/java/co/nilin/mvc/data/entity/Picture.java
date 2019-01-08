package co.nilin.mvc.data.entity;


import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name="PICTURES")

public class Picture implements Serializable {

	
	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long picId;
	@Column
	private byte[] image;
	@Column
	private String comment;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="albumId",nullable=false)
	private Album picAlbum;

	
	
	public Picture() {
		super();
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Album getPicAlbum() {
		return picAlbum;
	}

	public void setPicAlbum(Album picAlbum) {
		this.picAlbum = picAlbum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((picAlbum == null) ? 0 : picAlbum.hashCode());
		result = prime * result + ((picId == null) ? 0 : picId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Picture other = (Picture) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (picAlbum == null) {
			if (other.picAlbum != null)
				return false;
		} else if (!picAlbum.equals(other.picAlbum))
			return false;
		if (picId == null) {
			if (other.picId != null)
				return false;
		} else if (!picId.equals(other.picId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Picture{" +
				"picId=" + picId +
				", image=" + Arrays.toString(image) +
				", comment='" + comment + '\'' +
				", picAlbum=" + picAlbum +
				'}';
	}
}
