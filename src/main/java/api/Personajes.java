package api;

import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Personajes {

	@SerializedName("docs")
	@Expose
	private List<Personaje> docs = null;
	@SerializedName("total")
	@Expose
	private Integer total;
	@SerializedName("limit")
	@Expose
	private Integer limit;
	@SerializedName("offset")
	@Expose
	private Integer offset;
	@SerializedName("page")
	@Expose
	private Integer page;
	@SerializedName("pages")
	@Expose
	private Integer pages;

	public List<Personaje> getDocs() {
		return docs;
	}

	public void setDocs(List<Personaje> docs) {
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

}