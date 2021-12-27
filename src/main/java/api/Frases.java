package api;

import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Frases {

	@SerializedName("docs")
	@Expose
	private List<Frase> docs = null;
	@SerializedName("total")
	@Expose
	private Integer total;
	@SerializedName("limit")
	@Expose
	private Integer limit;
	@SerializedName("offset")
	@Expose
	private Integer offset;

	public List<Frase> getDocs() {
		return docs;
	}

	public void setDocs(List<Frase> docs) {
		this.docs = docs;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

}