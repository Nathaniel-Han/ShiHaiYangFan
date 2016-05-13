package com.shihai;


public class Poem {
	@com.google.gson.annotations.SerializedName("id")
	private String mId;
	
	@com.google.gson.annotations.SerializedName("title")
	private String mTitle;
	
	@com.google.gson.annotations.SerializedName("auther")
	private String mAuther;
	
	@com.google.gson.annotations.SerializedName("content")
	private String mContent;
	
	@com.google.gson.annotations.SerializedName("comment")
	private String mComment;
	
	@com.google.gson.annotations.SerializedName("analysis")
	private String mAnalysis;
	
	public Poem(){
		
	}
	
	public Poem(String id,String title,String auther,String content,String comment,String analysis){
		this.setId(id);
		this.setTitle(title);
		this.setAuther(auther);
		this.setContent(content);
		this.setComment(comment);
		this.setAnalysis(analysis);
	}
	
	public final void setId(String id){
		mId = id;
	}
	
	public final void setTitle(String title){
		mTitle = title;
	}
	
	public final void setAuther(String auther){
		mAuther = auther;
	}
	
	public final void setContent(String content){
		mContent = content;
	}
	
	public final void setComment(String comment){
		mComment = comment;
	}
	
	public final void setAnalysis(String analysis){
		mAnalysis = analysis;
	}
	
	
	public String getId(){
		return mId;
	}
	
	public String getAuther(){
		return mAuther;
	}
	
	public String getTitle(){
		return mTitle;
	}
	
	public String getContent(){
		return mContent;
	}
	
	public String getComment(){
		return mComment;
	}
	public String getAnalisis(){
		return mAnalysis;
	}
	
	
	@Override
	public boolean equals(Object o){
		return o instanceof Poem && ((Poem) o).mId == mId;
	}
	
}
